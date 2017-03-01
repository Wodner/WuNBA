package wu.wunba.ui.presenter;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBAPlayer;
import wu.wunba.model.NBATeamBaseInfo;
import wu.wunba.ui.view.NBATeamBaseInfoView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 13:04
 * 邮箱：wuwende@live.cn
 */

public class NBATeamBasnInfoPresenter implements Presenter {


    private Context mContext;
    private List<NBAPlayer> nbaPlayerList;
    private NBATeamBaseInfoView baseInfoView;

    public NBATeamBasnInfoPresenter(Context mContext, NBATeamBaseInfoView baseInfoView) {
        this.mContext = mContext;
        this.baseInfoView = baseInfoView;
        nbaPlayerList = new ArrayList<>();
    }

    @Override
    public void initialized(int type) {

    }


    /**
     * @param teamId
     */
    public void getNBATeamInfo(String teamId){
        if(MyUtils.isNetworkConnected(mContext)){
            baseInfoView.showLoading(true);
            NBAApiRequest.getNBATeamBaseInfo(teamId, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parseBaseInfo(s);
                }

                @Override
                public void onFailure(String errorMsg) {
                    baseInfoView.hideLoading(true);
                    baseInfoView.showError("获取数据失败");
                }
            });
        }else {
            baseInfoView.showError("网络连接错误");
            baseInfoView.hideLoading(true);
        }
    }


    /**
     * @param teamId
     */
    public void getNBATeamPlayers(final String teamId){
        if(MyUtils.isNetworkConnected(mContext)){
            baseInfoView.showLoading(true);
            NBAApiRequest.getNBAPlayers(new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parsePlayerInfo(teamId,s);
                }

                @Override
                public void onFailure(String errorMsg) {
                    baseInfoView.showError("获取信息失败");
                }
            });
        }else {
            baseInfoView.showError("网络连接错误");
            baseInfoView.hideLoading(true);
        }
    }


    public void parseBaseInfo(String result){
        NBATeamBaseInfo teamBaseInfo = JsonParser.parseWithGson(NBATeamBaseInfo.class,result);
        baseInfoView.showTeamBaseInfo(teamBaseInfo);
        baseInfoView.hideLoading(true);
    }


    public void parsePlayerInfo(String teamId,String result){
        nbaPlayerList.clear();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object = new JSONObject(jsonArray.get(i).toString());
                if(object.getString("teamId").equals(teamId)){
                    NBAPlayer nbaPlayer = JsonParser.parseWithGson(NBAPlayer.class,jsonArray.get(i).toString());
                    nbaPlayerList.add(nbaPlayer);
                }
            }
            baseInfoView.hideLoading(true);
            baseInfoView.showTeamPlayers(nbaPlayerList);
        } catch (JSONException e) {
            e.printStackTrace();
            baseInfoView.hideLoading(true);
            baseInfoView.showError("解析数据失败");
        }
    }
}
