package wu.wunba.ui.presenter;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBATeam;
import wu.wunba.ui.view.NBATeamView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 09:48
 * 邮箱：wuwende@live.cn
 */

public class NBATeamPresenter implements Presenter {


    private Context mContext;
    private NBATeamView teamView;


    private List<NBATeam> nbaTeamList;


    public NBATeamPresenter(Context mContext, NBATeamView teamView) {
        this.mContext = mContext;
        this.teamView = teamView;
        nbaTeamList = new ArrayList<>();
    }

    @Override
    public void initialized(int type) {
        getNBATeamList();
    }


    public void getNBATeamList(){
        if(MyUtils.isNetworkConnected(mContext)){
            teamView.showLoading(true);
            NBAApiRequest.getNBATeamList(new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parseTeamData(s);
                }

                @Override
                public void onFailure(String errorMsg) {
                    teamView.hideLoading(true);
                    teamView.showError("获取数据失败");
                }
            });
        }else{
            teamView.showError("网络连接异常");
            teamView.hideLoading(true);
        }
    }


    /**
     * @param result 解析数据
     */
    private void parseTeamData(String result){
        nbaTeamList.clear();
        String data = JsonParser.parseWithJSONObjectKey("data",result);
        try {
            JSONArray westArray = new JSONArray(JsonParser.parseWithJSONObjectKey("west",data).toString());
            JSONArray eastArray = new JSONArray(JsonParser.parseWithJSONObjectKey("east",data).toString());
            for (int i=0;i<westArray.length();i++){
                NBATeam nbaTeam = JsonParser.parseWithGson(NBATeam.class,westArray.get(i).toString());
                nbaTeamList.add(nbaTeam);
            }
            for (int i=0;i<eastArray.length();i++){
                NBATeam nbaTeam = JsonParser.parseWithGson(NBATeam.class,eastArray.get(i).toString());
                nbaTeamList.add(nbaTeam);
            }
            teamView.showTeam(nbaTeamList);
            teamView.hideLoading(true);
        } catch (JSONException e) {
            e.printStackTrace();
            teamView.hideLoading(true);
            teamView.showError("解析数据失败");
        }
    }


}
