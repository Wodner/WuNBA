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
import wu.wunba.model.NBANews;
import wu.wunba.ui.view.NBANewsView;

/**
 * 描述：
 * 作者：Wu on 2017/2/22 21:36
 * 邮箱：wuwende@live.cn
 */

public class NBANewsPresenter implements Presenter{


    public static final int TYPE_NEWS_HEADLINE = 0;
    public static final int TYPE_NEWS_INFORM = 1;
    public static final int TYPE_NEWS_PICTURE = 2;

    private NBANewsView nbaNewsView;
    /**根据返回的articleIDS 取出要请求的数据*/
    private String reqVideoArticleIds="";
    /**每页10条*/
    private final int PAGER_NUM = 10;

    private List<ModelNBAVideoArticleIds.DataBean> articleIds;
    private List<ModelNBAVideoArticleIds.DataBean> articleIdsBuffer;
    private List<NBANews> nbaNewsList;

    public NBANewsPresenter(NBANewsView nbaNewsHeadlineView) {
        this.nbaNewsView = nbaNewsHeadlineView;
        articleIds = new ArrayList<>();
        articleIdsBuffer = new ArrayList<>();
        nbaNewsList = new ArrayList<>();
    }


    @Override
    public void initialized(final int type) {
        nbaNewsView.showLoading(true);
        if(type==TYPE_NEWS_HEADLINE){
            NBAApiRequest.getNBANewsHeadlineArticleIds(new RequestCallBack<ModelNBAVideoArticleIds>() {
                @Override
                public void onSuccess(ModelNBAVideoArticleIds modelNBAVideoArticleIds) {
                    articleIds = modelNBAVideoArticleIds.getData();//取得 每个NBA资讯的 articleId
                    setArticleIds(1,TYPE_NEWS_HEADLINE);
                }

                @Override
                public void onFailure(String errorMsg) {
                    nbaNewsView.hideLoading(true);
                }
            });
        }else if(type==TYPE_NEWS_INFORM){
            NBAApiRequest.getNBANewsInformArticleIds(new RequestCallBack<ModelNBAVideoArticleIds>() {
                @Override
                public void onSuccess(ModelNBAVideoArticleIds modelNBAVideoArticleIds) {
                    articleIds = modelNBAVideoArticleIds.getData();//取得 每个NBA资讯的 articleId
                    setArticleIds(1,TYPE_NEWS_INFORM);
                }

                @Override
                public void onFailure(String errorMsg) {
                    nbaNewsView.hideLoading(true);
                }
            });
        }else if(type==TYPE_NEWS_PICTURE){
            NBAApiRequest.getNBANewsPictureArticleIds(new RequestCallBack<ModelNBAVideoArticleIds>() {
                @Override
                public void onSuccess(ModelNBAVideoArticleIds modelNBAVideoArticleIds) {
                    articleIds = modelNBAVideoArticleIds.getData();//取得 每个NBA资讯的 articleId
                    setArticleIds(1,TYPE_NEWS_PICTURE);
                }

                @Override
                public void onFailure(String errorMsg) {
                    nbaNewsView.hideLoading(true);
                }
            });
        }

    }


    /**
     * @param itemNum 这个numb 是下拉刷新 上拉加载的时候要显示多少页
     */
    public void setArticleIds(int itemNum,int type){
        nbaNewsView.showLoading(false);
        if(itemNum>0){
            if(itemNum*PAGER_NUM>articleIds.size()){
                nbaNewsView.showError("-1");
                return;
            }else{//头条
                if(type==TYPE_NEWS_HEADLINE){
                    NBAApiRequest.getNewsHighlines(getRequestArticleIds(itemNum), new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(String nbaNews) {
                            parseNBANews(nbaNews);
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            nbaNewsView.showError("获取信息失败");
                            nbaNewsView.hideLoading(true);
                        }
                    });
                }else if(type==TYPE_NEWS_INFORM){
                    //资讯
                    NBAApiRequest.getNewsInforms(getRequestArticleIds(itemNum), new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(String nbaInform) {
                            parseNBANews(nbaInform);
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            nbaNewsView.showError("获取信息失败");
                            nbaNewsView.hideLoading(true);
                        }
                    });
                }else if(type==TYPE_NEWS_PICTURE){
                    //图集
                    NBAApiRequest.getNewsPictures(getRequestArticleIds(itemNum), new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(String nbaPic) {
                            Logger.e("图集IDS " + nbaPic);
                            parseNBANews(nbaPic);
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            nbaNewsView.showError("获取信息失败");
                            nbaNewsView.hideLoading(true);
                        }
                    });
                }
            }
        }else{
            return;
        }
    }


    /**
     * @param result 解析NBANrews 数据
     */
    private void parseNBANews(String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject videoObject = new JSONObject(jsonObject.getString("data"));
            for (int i=0;i<articleIdsBuffer.size();i++){
                NBANews news = JsonParser.parseWithGson(NBANews.class,videoObject.getString(articleIdsBuffer.get(i).getId()));
                nbaNewsList.add(news);
            }
            nbaNewsView.showNews(nbaNewsList);
            nbaNewsList.clear();
            nbaNewsView.hideLoading(true);
        } catch (JSONException e) {
            e.printStackTrace();
            nbaNewsView.showError("解析数据失败");
            nbaNewsView.hideLoading(true);
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
            if(i==lenght-1){
                strIds += articleIds.get(i).getId();
            }else {
                strIds += articleIds.get(i).getId()+ ",";
            }
        }
        return strIds;
    }








}
