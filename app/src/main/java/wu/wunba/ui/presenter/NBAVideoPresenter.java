package wu.wunba.ui.presenter;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.ModelNBAVideoArticleIds;
import wu.wunba.model.NBAVideo;
import wu.wunba.ui.view.NBAVideoView;

/**
 * 描述：
 * 作者：Wu on 2017/2/22 09:42
 * 邮箱：wuwende@live.cn
 */

public class NBAVideoPresenter implements Presenter {

    public static final int TYPE_VIDEO_HIGHLIGHT = 0;
    public static final int TYPE_VIDEO_BESE = 1;
    public static final int TYPE_VIDEO_TIDBITS = 2;

    private NBAVideoView videoView;
    /**根据返回的articleIDS 取出要请求的数据*/
    private String reqVideoArticleIds="";
    /**每页10条*/
    private final int PAGER_NUM = 10;

    private List<ModelNBAVideoArticleIds.DataBean> articleIds;
    private List<ModelNBAVideoArticleIds.DataBean> articleIdsBuffer;
    private List<NBAVideo> nbaVideolist;

    public NBAVideoPresenter(NBAVideoView view){
        this.videoView= view;
        articleIds = new ArrayList<>();
        articleIdsBuffer = new ArrayList<>();
        nbaVideolist = new ArrayList<>();
    }


    @Override
    public void initialized(int type) {
        videoView.showLoading(true);
        if(type==TYPE_VIDEO_HIGHLIGHT){
            NBAApiRequest.getHighlightVideoArticleIds(new RequestCallBack<ModelNBAVideoArticleIds>() {
                @Override
                public void onSuccess(ModelNBAVideoArticleIds modelNBAVideoArticleIds) {
                    Logger.d("========= " + modelNBAVideoArticleIds.getData().size());
                    articleIds = modelNBAVideoArticleIds.getData();//取得 每个视频的 articleId
                    setArticleIds(1,TYPE_VIDEO_HIGHLIGHT);
                }

                @Override
                public void onFailure(String errorMsg) {
                    videoView.hideLoading(true);
                }
            });
        }else if(type== TYPE_VIDEO_BESE){
            NBAApiRequest.getBestVideoArticleIds(new RequestCallBack<ModelNBAVideoArticleIds>() {
                @Override
                public void onSuccess(ModelNBAVideoArticleIds modelNBAVideoArticleIds) {
//                    Logger.d("========= " + modelNBAVideoArticleIds.getData().size());
                    articleIds = modelNBAVideoArticleIds.getData();//取得 每个视频的 articleId
                    setArticleIds(1,TYPE_VIDEO_BESE);
                }

                @Override
                public void onFailure(String errorMsg) {
                    videoView.hideLoading(true);
                }
            });
        }else if(type==TYPE_VIDEO_TIDBITS){
            NBAApiRequest.getTidbitsVideoArticleIds(new RequestCallBack<ModelNBAVideoArticleIds>() {
                @Override
                public void onSuccess(ModelNBAVideoArticleIds modelNBAVideoArticleIds) {
                    Logger.d("========= " + modelNBAVideoArticleIds.getData().size());
                    articleIds = modelNBAVideoArticleIds.getData();//取得 每个视频的 articleId
                    setArticleIds(1,TYPE_VIDEO_TIDBITS);
                }

                @Override
                public void onFailure(String errorMsg) {
                    videoView.hideLoading(true);
                }
            });
        }
    }

    /**
     * @param itemNum 这个numb 是下拉刷新 上拉加载的时候要显示多少页
     */
    public void setArticleIds(int itemNum,int type){
        videoView.showLoading(false);
        if(itemNum>0){
            if(itemNum*PAGER_NUM>articleIds.size()){
                videoView.showError("-1");
                return;
            }else{
                if(type==TYPE_VIDEO_HIGHLIGHT){
                    NBAApiRequest.getHighlightVideo(getRequestArticleIds(itemNum), new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(String nbaVideo) {
//                        Logger.d("返回视频信息 ：" + nbaVideo);
                            parseNBAVideo(nbaVideo);
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            videoView.showError("获取信息失败");
                            videoView.hideLoading(true);
                        }
                    });
                }else if(type== TYPE_VIDEO_BESE){
                    NBAApiRequest.getBestVideo(getRequestArticleIds(itemNum), new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(String nbaVideo) {
//                        Logger.d("返回视频信息 ：" + nbaVideo);
                            parseNBAVideo(nbaVideo);
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            videoView.showError("获取信息失败");
                            videoView.hideLoading(true);
                        }
                    });
                }else if(type==TYPE_VIDEO_TIDBITS){
                    NBAApiRequest.getTidbitsVideo(getRequestArticleIds(itemNum), new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(String nbaVideo) {
//                        Logger.d("返回视频信息 ：" + nbaVideo);
                            parseNBAVideo(nbaVideo);
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            videoView.showError("获取信息失败");
                            videoView.hideLoading(true);
                        }
                    });
                }
            }
        }else{
            return;
        }
    }


    /**
     * @param result 解析NBAVIDEO 数据
     */
    private void parseNBAVideo(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject videoObject = new JSONObject(jsonObject.getString("data"));
            for (int i=0;i<articleIdsBuffer.size();i++){
                NBAVideo video = JsonParser.parseWithGson(NBAVideo.class,videoObject.getString(articleIdsBuffer.get(i).getId()));
                nbaVideolist.add(video);
            }
            videoView.showMatchVideo(nbaVideolist);
            nbaVideolist.clear();
            videoView.hideLoading(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param num 是要取出第几页的 article ids
     */
    private String getRequestArticleIds(int num) {
        String strIds="";
        int lenght;
        if(articleIds.size()>PAGER_NUM-1){
            if(articleIds.size()%PAGER_NUM!=0){
                if(articleIds.size()<PAGER_NUM*num){
                    lenght = articleIds.size();
                }else{
                    lenght = PAGER_NUM*num;
                }
            }else {
                lenght = PAGER_NUM*num;
            }
        }else{
            lenght = articleIds.size();
        }
        articleIdsBuffer .clear();
        for(int i=PAGER_NUM*(num-1);i<lenght;i++){
            articleIdsBuffer.add(articleIds.get(i));
//            Logger.d("buffer ids = " + articleIds.get(i).getId());
            if(i==lenght-1){
                strIds += articleIds.get(i).getId();
            }else {
                strIds += articleIds.get(i).getId()+ ",";
            }
        }
//        Logger.w(" 取得ID是 == " + strIds);
        return strIds;
    }
}
