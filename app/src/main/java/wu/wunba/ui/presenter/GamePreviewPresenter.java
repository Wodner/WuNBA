package wu.wunba.ui.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBAGamePreviewInfo;
import wu.wunba.ui.view.GamePreviewView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：比赛前瞻
 * 作者：Wu on 2017/3/10 17:10
 * 邮箱：wuwende@live.cn
 */

public class GamePreviewPresenter implements Presenter {

    private Context mContext;
    private GamePreviewView gamePreviewView;

    public GamePreviewPresenter(Context mContext, GamePreviewView gamePreviewView) {
        this.mContext = mContext;
        this.gamePreviewView = gamePreviewView;
    }

    @Override
    public void initialized(int type) {

    }


    /**
     * @param mid
     */
    public void getGamePreviewInfo(String mid){
        if(MyUtils.isNetworkConnected(mContext)){
            NBAApiRequest.getNBAGamePreviewInfo(mid, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parsePreview(s);
                }

                @Override
                public void onFailure(String errorMsg) {

                }
            });
        }else {
            gamePreviewView.showError("0");
        }
    }


    /**
     * @param result 数据解析
     */
    private void parsePreview(String result){

        NBAGamePreviewInfo nbaGamePreviewInfo = new NBAGamePreviewInfo();
        String strData = JsonParser.parseWithJSONObjectKey("data",result);
        String strTeamInfo = JsonParser.parseWithJSONObjectKey("teamInfo",strData);

        Logger.d("----- " + strTeamInfo);
        nbaGamePreviewInfo.teamInfo = JsonParser.parseWithGson(NBAGamePreviewInfo.TeamInfo.class,strTeamInfo);
        String strStats = JsonParser.parseWithJSONObjectKey("stats",strData);

        try {
            JSONArray statsArray = new JSONArray(strStats);

            String strMaxPlayer = statsArray.get(0).toString();//球队数据王
            nbaGamePreviewInfo.maxPlager = JsonParser.parseWithGson(NBAGamePreviewInfo.MaxPlager.class,strMaxPlayer);
            Logger.d("----- " + nbaGamePreviewInfo.getMaxPlager().getText());

            String vsHistory = statsArray.get(1).toString();//历史对阵
            nbaGamePreviewInfo.vsHistory = JsonParser.parseWithGson(NBAGamePreviewInfo.VsHistory.class,vsHistory);
            Logger.d("----- " + nbaGamePreviewInfo.getVsHistory().getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
