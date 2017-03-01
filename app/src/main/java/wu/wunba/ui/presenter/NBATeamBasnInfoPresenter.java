package wu.wunba.ui.presenter;

import android.content.Context;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
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
    private NBATeamBaseInfoView baseInfoView;

    public NBATeamBasnInfoPresenter(Context mContext, NBATeamBaseInfoView baseInfoView) {
        this.mContext = mContext;
        this.baseInfoView = baseInfoView;
    }

    @Override
    public void initialized(int type) {

    }



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
            baseInfoView.showError("0");
            baseInfoView.hideLoading(true);
        }
    }


    public void parseBaseInfo(String result){
        NBATeamBaseInfo teamBaseInfo = JsonParser.parseWithGson(NBATeamBaseInfo.class,result);
        baseInfoView.showTeamBaseInfo(teamBaseInfo);
        baseInfoView.hideLoading(true);
    }

}
