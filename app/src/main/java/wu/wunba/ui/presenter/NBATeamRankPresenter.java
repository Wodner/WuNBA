package wu.wunba.ui.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBATeamRank;
import wu.wunba.ui.view.NBATeamRankView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/24 20:43
 * 邮箱：wuwende@live.cn
 */

public class NBATeamRankPresenter implements Presenter{

    public static final int TYPE_RANK_BY_UNION = 0;
    public static final int TYPE_RANK_BY_DIVISION = 1;
    private Context mContext;
    private NBATeamRankView teamRankView;
    private List<NBATeamRank> nbaTeamRankList;


    public NBATeamRankPresenter(Context context,NBATeamRankView teamRankView) {
        this.teamRankView = teamRankView;
        this.mContext = context;
        this.nbaTeamRankList = new ArrayList<>();
    }

    @Override
    public void initialized(final int type) {
        getNBATeamRank(TYPE_RANK_BY_UNION);
    }


    /**
     *get 排名数据
     */
    public void getNBATeamRank(int type) {
        teamRankView.showLoading(true);
        if(MyUtils.isNetworkConnected(mContext)) {
            if(type ==TYPE_RANK_BY_UNION){
                NBAApiRequest.getNBATeamRankingByUnion(new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        parseTeamRankDataByUniou(s);
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        teamRankView.showError("请求数据失败");
                    }
                });
            }else {
                NBAApiRequest.getNBATeamRankingByDivision(new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        parseTeamRankDataByDivision(s);
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        teamRankView.showError("请求数据失败");
                    }
                });
            }
        }else{
            teamRankView.showError("0");
            teamRankView.hideLoading(true);
        }
    }

    /**
     * @param s
     */
    private void parseTeamRankDataByUniou(String s) {
        try {
            JSONArray jsonArray = new JSONArray(new JSONObject(s).getString("data").toString());
            nbaTeamRankList.clear();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject  teamObjece = new JSONObject(jsonArray.get(i).toString());
                JSONArray teamArray =  new JSONArray(teamObjece.getString("rows"));
                for (int n=0;n<teamArray.length();n++){
                    NBATeamRank nbaTeamRank = new NBATeamRank();
                    JSONArray detailArray =  new JSONArray(teamArray.get(n).toString());
                    JSONObject detailObject = new JSONObject(detailArray.get(0).toString());
                    nbaTeamRank.setRankNum(""+(n+1));
                    nbaTeamRank.setTitle(teamObjece.getString("title"));
                    nbaTeamRank.setTeamId(detailObject.getString("teamId"));
                    nbaTeamRank.setName(detailObject.getString("name"));
                    nbaTeamRank.setBadge(detailObject.getString("badge"));
                    nbaTeamRank.setSerial(detailObject.getString("serial"));
                    nbaTeamRank.setColor(detailObject.getString("color"));
                    nbaTeamRank.setDetailUrl(detailObject.getString("detailUrl"));
                    nbaTeamRank.setWin(detailArray.get(1).toString());
                    nbaTeamRank.setLose(detailArray.get(2).toString());
                    nbaTeamRank.setWinProbility(detailArray.get(3).toString());
                    nbaTeamRank.setWinBind(detailArray.get(4).toString());
                    nbaTeamRankList.add(nbaTeamRank);
                }
            }
            teamRankView.hideLoading(true);
            teamRankView.showRanking(nbaTeamRankList);
        } catch (JSONException e) {
            e.printStackTrace();
            Logger.e(e.toString());
            teamRankView.showError("1");
        }
    }

    /**
     * @param s
     */
    private void parseTeamRankDataByDivision(String s) {
        try {
            nbaTeamRankList.clear();
            JSONObject jsonObject = new JSONObject(new JSONObject(s).getString("data").toString());
            JSONArray jsonArray = new JSONArray(jsonObject.getString("list"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object = new JSONObject(jsonArray.get(i).toString());
                JSONArray teamArray = new JSONArray(object.getString("rows"));
                for (int n=0;n<teamArray.length();n++){
                    NBATeamRank nbaTeamRank = new NBATeamRank();
                    JSONArray detailArray =  new JSONArray(teamArray.get(n).toString());
                    JSONObject detailObject = new JSONObject(detailArray.get(0).toString());
                    nbaTeamRank.setRankNum(""+(n+1));
                    nbaTeamRank.setTitle(object.getString("title"));
                    nbaTeamRank.setTeamId(detailObject.getString("teamId"));
                    nbaTeamRank.setName(detailObject.getString("name"));
                    nbaTeamRank.setBadge(detailObject.getString("badge"));
                    nbaTeamRank.setSerial(detailObject.getString("serial"));
                    nbaTeamRank.setDetailUrl(detailObject.getString("detailUrl"));
                    nbaTeamRank.setWin(detailArray.get(1).toString());
                    nbaTeamRank.setLose(detailArray.get(2).toString());
                    nbaTeamRank.setWinProbility(detailArray.get(3).toString());
                    nbaTeamRank.setWinBind(detailArray.get(4).toString());
                    nbaTeamRankList.add(nbaTeamRank);
                }
            }
            teamRankView.hideLoading(true);
            teamRankView.showRanking(nbaTeamRankList);
            Logger.d("----" + nbaTeamRankList.size());
        } catch (JSONException e) {
            e.printStackTrace();
            Logger.e(e.toString());
            teamRankView.showError("1");
        }
    }
}
