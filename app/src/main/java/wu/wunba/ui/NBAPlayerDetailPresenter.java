package wu.wunba.ui;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBAPlayerBaseInfo;
import wu.wunba.model.NBAPlayerCareerData;
import wu.wunba.model.NBAPlayerData;
import wu.wunba.model.NBAPlayerSeasonData;
import wu.wunba.ui.view.NBAPlayerDetailView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/28 11:38
 * 邮箱：wuwende@live.cn
 */

public class NBAPlayerDetailPresenter implements Presenter {

    public static final int TYPE_SEASON = 1;
    public static final int TYPE_CAREER = 2;

    private List<NBAPlayerData>  playerDataList;


    private Context mContext;
    private NBAPlayerDetailView playerDetailView;

    public NBAPlayerDetailPresenter(Context mContext, NBAPlayerDetailView playerDetailView) {
        this.mContext = mContext;
        this.playerDetailView = playerDetailView;
        playerDataList = new ArrayList<>();
    }

    @Override
    public void initialized(int type) {

    }

    public void getPlayerBaseInfo(String playerId){
//        playerDetailView.showLoading(true);
        if(MyUtils.isNetworkConnected(mContext)){
            NBAApiRequest.getNBAPlayerBaseInfo(playerId, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    Logger.d("球员信息 === " +s);
                    NBAPlayerBaseInfo baseInfo = JsonParser.parseWithGson(NBAPlayerBaseInfo.class,s);
                    playerDetailView.showPlayerBaseInfo(baseInfo);
//                    playerDetailView.hideLoading(true);
                }

                @Override
                public void onFailure(String errorMsg) {
                    playerDetailView.showError("获取不到数据");
//                    playerDetailView.hideLoading(true);
                }
            });
        }else {
//            playerDetailView.hideLoading(true);
            playerDetailView.showError("0");
        }
    }


    public void getPlayerMatchData(String playerId, final int  type){
        playerDetailView.showLoading(true);
        if(MyUtils.isNetworkConnected(mContext)){
            NBAApiRequest.getNBAPlayerData(playerId,""+type, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    Logger.d("球员详细数据 === " +s);
                    parsePlayerData(s, type);
                }

                @Override
                public void onFailure(String errorMsg) {
                    playerDetailView.hideLoading(true);
                }
            });
        }else{
            playerDetailView.showError("0");
            playerDetailView.hideLoading(true);
        }
    }

    private void parsePlayerData(String s, int type) {
        playerDataList.clear();
        if(type==TYPE_SEASON){
            NBAPlayerSeasonData seasonData =  JsonParser.parseWithGson(NBAPlayerSeasonData.class,s);
            if(seasonData.getData().stats!=null){
                NBAPlayerData nbaPlayerData_0 = new NBAPlayerData();
                nbaPlayerData_0.setHead(seasonData.getData().getStats().getHead());
                nbaPlayerData_0.setRows(seasonData.getData().getStats().getRows());
                nbaPlayerData_0.setDataType("本赛季平均");
                playerDataList.add(nbaPlayerData_0);
            }
            if(seasonData.getData().lastMatches!=null){
                NBAPlayerData nbaPlayerData_1 = new NBAPlayerData();
                nbaPlayerData_1.setHead(seasonData.getData().getLastMatches().getHead());
                nbaPlayerData_1.setRows(seasonData.getData().getLastMatches().getRows());
                nbaPlayerData_1.setDataType("最近五场比赛");
                playerDataList.add(nbaPlayerData_1);
            }
        }else if(type==TYPE_CAREER) {
            NBAPlayerCareerData careerData = JsonParser.parseWithGson(NBAPlayerCareerData.class,s);
            Logger.w("==== " + careerData.getData().getReg().getHead().get(0));
            if(careerData.getData().reg!=null){
                NBAPlayerData nbaPlayerData_0 = new NBAPlayerData();
                if(careerData.getData().getReg().getHead().size()>0){
                    nbaPlayerData_0.setHead(careerData.getData().getReg().getHead());
                    nbaPlayerData_0.setRows(careerData.getData().getReg().getRows());
                    nbaPlayerData_0.setDataType("常规赛平均");
                    playerDataList.add(nbaPlayerData_0);
                }
            }
            if(careerData.getData().playoff != null){//有季后赛数据
                if(careerData.getData().getPlayoff().getHead().size()>0){
                    NBAPlayerData nbaPlayerData_1 = new NBAPlayerData();
                    nbaPlayerData_1.setHead(careerData.getData().getPlayoff().getHead());
                    nbaPlayerData_1.setRows(careerData.getData().getPlayoff().getRows());
                    nbaPlayerData_1.setDataType("季后赛平均");
                    playerDataList.add(nbaPlayerData_1);
                }
            }
        }
        playerDetailView.showPlayerData(playerDataList);
        playerDetailView.hideLoading(true);
    }


}
