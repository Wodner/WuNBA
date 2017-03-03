package wu.wunba.ui.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBAGameTextLiveIndex;
import wu.wunba.model.NBAGameTextLiveItem;
import wu.wunba.ui.view.NBAGameTextLiveView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/2 11:16
 * 邮箱：wuwende@live.cn
 */

public class NBAGameTextLivePresenter implements Presenter {


    private Context mContext;
    private NBAGameTextLiveView gameTextLiveView;
    private NBAGameTextLiveIndex gameTextLiveIndex;
    private List<String> gameTextLiveIndexListBuffer;
    /**每页10条*/
    private final int PAGER_NUM = 30;
    private List<NBAGameTextLiveItem> textLiveItemList;

    private int lastIdNum;


    public NBAGameTextLivePresenter(Context mContext, NBAGameTextLiveView gameTextLiveView) {
        this.mContext = mContext;
        this.gameTextLiveView = gameTextLiveView;
        gameTextLiveIndex = new NBAGameTextLiveIndex();
        gameTextLiveIndexListBuffer = new ArrayList<>();
        textLiveItemList = new ArrayList<>();
    }

    @Override
    public void initialized(int type) {

    }


    /**
     * @param itemNum ==1 : 下拉刷新 >1 上拉加载
     * @param mid
     */
    public void getNBAGameTextLiveIndex(final int itemNum, final String mid){
        if(MyUtils.isNetworkConnected(mContext)){
            if(itemNum==1){
                gameTextLiveView.showLoading(true);
            }
            NBAApiRequest.getNBAGameTextLiveIndex(mid, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    gameTextLiveIndex = JsonParser.parseWithGson(NBAGameTextLiveIndex.class,s);
                    lastIdNum = gameTextLiveIndex.getData().getIndex().size();
                    if(lastIdNum==0){
                        gameTextLiveView.showError("-1");
                        return;
                    }
                    getNBAGameTextLiveInfo(itemNum,mid);
                }

                @Override
                public void onFailure(String errorMsg) {
                    gameTextLiveView.showError("获取数据异常");
                    gameTextLiveView.hideLoading(true);
                }
            });
        }else{
            gameTextLiveView.showError("0");
            gameTextLiveView.hideLoading(true);
        }
    }

    /**
     * @param mid 直播时候定时更新
     */
    public void getGameTextLiveIndexTimer(final String mid){
        NBAApiRequest.getNBAGameTextLiveIndex(mid, new RequestCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                gameTextLiveIndex = JsonParser.parseWithGson(NBAGameTextLiveIndex.class,s);
                int l = gameTextLiveIndex.getData().getIndex().size()-lastIdNum;
                Logger.d(gameTextLiveIndex.getData().getIndex().size() + "---"+ lastIdNum + "--ids-长度--- " + l);
                lastIdNum = gameTextLiveIndex.getData().getIndex().size();

                if(l >0){
                    NBAApiRequest.getNBAGameTextLiveInfo(mid, getNewIds(l), new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            parseNBAGameText(true,s);
                        }

                        @Override
                        public void onFailure(String errorMsg) {
                            gameTextLiveView.hideLoading(true);
                        }
                    });
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                gameTextLiveView.showError("获取数据异常");
                gameTextLiveView.hideLoading(true);
            }
        });
    }


    /**
     * @param lenght
     * @return
     */
    private String getNewIds(int lenght){
        String strIds="";
        gameTextLiveIndexListBuffer.clear();
        for(int i=0;i<lenght;i++){
            gameTextLiveIndexListBuffer.add(gameTextLiveIndex.getData().getIndex().get(i));
            if(i==lenght-1){
                strIds += gameTextLiveIndex.getData().getIndex().get(i);
            }else {
                strIds += gameTextLiveIndex.getData().getIndex().get(i)+ ",";
            }
        }
        Logger.d("--ids---- " + strIds);
        return strIds;
    }


    /**
     * @param itemNum
     * @param mid
     */
    private void getNBAGameTextLiveInfo(int itemNum,String mid){
        if(itemNum>0){
                NBAApiRequest.getNBAGameTextLiveInfo(mid, getRequestArticleIds(itemNum), new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Logger.w("-----返回最新直播信息：" + s);
                        parseNBAGameText(false,s);
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        gameTextLiveView.hideLoading(true);
                    }
                });
        }else{
            return;
        }
    }


    /**
     * @param num 是要取出第几页的 article ids
     */
    private String getRequestArticleIds(int num) {
        String strIds="";
        int lenght;
        if(gameTextLiveIndex.getData().getIndex().size()>PAGER_NUM-1){
            if(gameTextLiveIndex.getData().getIndex().size()%PAGER_NUM!=0){
                if(gameTextLiveIndex.getData().getIndex().size()<PAGER_NUM*num){
                    lenght = gameTextLiveIndex.getData().getIndex().size();
                }else{
                    lenght = PAGER_NUM*num;
                }
            }else {
                lenght = PAGER_NUM*num;
            }
        }else{
            lenght = gameTextLiveIndex.getData().getIndex().size();
        }
        gameTextLiveIndexListBuffer.clear();
        for(int i=PAGER_NUM*(num-1);i<lenght;i++){
            gameTextLiveIndexListBuffer.add(gameTextLiveIndex.getData().getIndex().get(i));
            if(i==lenght-1){
                strIds += gameTextLiveIndex.getData().getIndex().get(i);
            }else {
                strIds += gameTextLiveIndex.getData().getIndex().get(i)+ ",";
            }
        }
        Logger.d("--ids---- " + strIds);
        return strIds;
    }


    /**
     * @param result 解析NBANrews 数据
     */
    private void parseNBAGameText(boolean isUpdata,String result){
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject dataObject = new JSONObject(jsonObject.getString("data"));
            JSONObject textObject = new JSONObject(dataObject.getString("detail"));
            Logger.w("-----信息数目：" + gameTextLiveIndexListBuffer.size());
            for (int i=0;i<gameTextLiveIndexListBuffer.size();i++){
                JSONObject itemObject = new JSONObject(textObject.getString(gameTextLiveIndexListBuffer.get(i)));
                if(itemObject.getString("ctype").equals("2")){//文字
                    Logger.w("-----返文字类型数目：" + gameTextLiveIndexListBuffer.size());
                    NBAGameTextLiveItem textLiveItem = JsonParser.parseWithGson(NBAGameTextLiveItem.class,textObject.getString(gameTextLiveIndexListBuffer.get(i)));
                    textLiveItemList.add(textLiveItem);
                }else if(itemObject.getString("ctype").equals("1")){//视频

                }else if(itemObject.getString("ctype").equals("3")){//图片

                }
            }
            gameTextLiveView.showGameTextLive(isUpdata,textLiveItemList);
            textLiveItemList.clear();
            gameTextLiveView.hideLoading(true);
        } catch (JSONException e) {
            e.printStackTrace();
            gameTextLiveView.showError("解析数据失败");
            gameTextLiveView.hideLoading(true);
        }
    }
}
