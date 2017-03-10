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
import wu.wunba.model.NBAGameLiveDataInfo;
import wu.wunba.ui.view.NBAGameLiveDataView;
import wu.wunba.utils.MyUtils;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2017/3/9 16:41
 * 邮箱： descriable
 */
public class NBAGameLiveDataPresenter implements Presenter {


    private Context mContext;
    private String matchPeriod;
    private NBAGameLiveDataView gameLiveDataView;

    public NBAGameLiveDataPresenter(Context mContext, NBAGameLiveDataView gameLiveDataView) {
        this.mContext = mContext;
        this.gameLiveDataView = gameLiveDataView;
    }

    @Override
    public void initialized(int type) {

    }


    /**
     * @param mid
     */
    public void getNBAGameLiveDataInfo(String mid,String matchPeriod){
        this.matchPeriod = matchPeriod;
        if (MyUtils.isNetworkConnected(mContext)){
            gameLiveDataView.showLoading(true);
            NBAApiRequest.getNBAGameLiveDataInfo(mid, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parseDataInfo(s);
                }
                @Override
                public void onFailure(String errorMsg) {
                    gameLiveDataView.showError("获取数据失败");
                    gameLiveDataView.hideLoading(true);
                }
            });
        }else{
            gameLiveDataView.showError("0");
        }
    }


    /**
     * @param result
     */
    private void parseDataInfo(String result){

        NBAGameLiveDataInfo gameLiveDataInfo = new NBAGameLiveDataInfo();
        gameLiveDataInfo.setMatchPeriod(matchPeriod);
        String strData = JsonParser.parseWithJSONObjectKey("data",result);
        String strTeamInfo = JsonParser.parseWithJSONObjectKey("teamInfo",strData);
        gameLiveDataInfo.teamInfo = JsonParser.parseWithGson(NBAGameLiveDataInfo.TeamInfo.class,strTeamInfo);
        String strStats = JsonParser.parseWithJSONObjectKey("stats",strData);
        try {
            JSONArray jsonArray = new JSONArray(strStats);
            String strScore = jsonArray.get(0).toString();
            gameLiveDataInfo.score = JsonParser.parseWithGson(NBAGameLiveDataInfo.Score.class,strScore);

            if (matchPeriod.equals("2")){//比赛已结束
                String strMaxPlayer = jsonArray.get(1).toString();
                gameLiveDataInfo.maxPlayer = JsonParser.parseWithGson(NBAGameLiveDataInfo.MaxPlayer.class,strMaxPlayer);

            }else if(matchPeriod.equals("1")){//直播中
                String strPlayerStats = jsonArray.get(1).toString();
                gameLiveDataInfo. playerStats =  gameLiveDataInfo.new PlayerStats();
                String text = JsonParser.parseWithJSONObjectKey("text",strPlayerStats);
                String type = JsonParser.parseWithJSONObjectKey("type",strPlayerStats);
                gameLiveDataInfo.getPlayerStats().setText(text);
                gameLiveDataInfo.getPlayerStats().setType(type);
                String strPlayerDetail = JsonParser.parseWithJSONObjectKey("playerStats",strPlayerStats);

                gameLiveDataInfo.playerStats.left = gameLiveDataInfo.playerStats.new Left();
                String strLeft = JsonParser.parseWithJSONObjectKey("left",strPlayerDetail);
                JSONArray leftArray = new JSONArray(strLeft);
                JSONObject leftHeadObject = new JSONObject(leftArray.get(0).toString());
                String leftHead = leftHeadObject.getString("head");
                JSONArray leftHeadArray = new JSONArray(leftHead);
                List<String> leftHeadList = new ArrayList<>();
                for (int i=0;i<leftHeadArray.length();i++){
                    leftHeadList.add(leftHeadArray.get(i).toString());
                }
                gameLiveDataInfo.getPlayerStats().getLeft().setHead(leftHeadList);

                List<NBAGameLiveDataInfo.PlayerStats.Left.PlayerInfo> leftPlayerInfoList = new ArrayList<>();
                for (int n=1;n<leftArray.length();n++){
                    NBAGameLiveDataInfo.PlayerStats.Left.PlayerInfo leftPlayerInfo = JsonParser.parseWithGson(
                            NBAGameLiveDataInfo.PlayerStats.Left.PlayerInfo.class,leftArray.get(n).toString());
                    leftPlayerInfoList.add(leftPlayerInfo);
                }
                gameLiveDataInfo.getPlayerStats().getLeft().setPlayerInfoList(leftPlayerInfoList);

                gameLiveDataInfo.playerStats.right = gameLiveDataInfo.playerStats.new Right();
                String strRight = JsonParser.parseWithJSONObjectKey("right",strPlayerDetail);
                JSONArray rightArray = new JSONArray(strRight);
                JSONObject rightHeadObject = new JSONObject(rightArray.get(0).toString());
                String rightHead = leftHeadObject.getString("head");
                JSONArray rightHeadArray = new JSONArray(rightHead);

                List<String> rightHeadList = new ArrayList<>();

                for (int i=0;i<rightHeadArray.length();i++){
                    rightHeadList.add(rightHeadArray.get(i).toString());
                }
                gameLiveDataInfo.getPlayerStats().getRight().setHead(rightHeadList);
                List<NBAGameLiveDataInfo.PlayerStats.Right.PlayerInfo> righPlayerInfoList = new ArrayList<>();
                for (int n=1;n<rightArray.length();n++){
                    NBAGameLiveDataInfo.PlayerStats.Right.PlayerInfo rightPlayerInfo = JsonParser.parseWithGson(
                            NBAGameLiveDataInfo.PlayerStats.Right.PlayerInfo.class,rightArray.get(n).toString());
                    righPlayerInfoList.add(rightPlayerInfo);
                }
                gameLiveDataInfo.getPlayerStats().getRight().setPlayerInfoList(righPlayerInfoList);
            }
            String strTeamStats = jsonArray.get(2).toString();
            gameLiveDataInfo.teamStats = JsonParser.parseWithGson(NBAGameLiveDataInfo.TeamStats.class,strTeamStats);
            gameLiveDataView.showGameLiveData(gameLiveDataInfo);
            gameLiveDataView.hideLoading(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
