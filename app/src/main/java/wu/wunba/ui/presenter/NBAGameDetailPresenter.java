package wu.wunba.ui.presenter;

import android.content.Context;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBAGameBaseInfo;
import wu.wunba.ui.view.NBAGameBaseInfoView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 22:46
 * 邮箱：wuwende@live.cn
 */

public class NBAGameDetailPresenter implements Presenter {


    private Context mContext;
    private NBAGameBaseInfoView gameBaseInfoView;

    public NBAGameDetailPresenter(Context mContext, NBAGameBaseInfoView gameBaseInfoView) {
        this.mContext = mContext;
        this.gameBaseInfoView = gameBaseInfoView;
    }

    @Override
    public void initialized(int type) {

    }

    public void getNBAGameInfo(String mid){
        if(MyUtils.isNetworkConnected(mContext)){
            NBAApiRequest.getNBAGameBaseInfo(mid, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parseGameInfo(s);
                }

                @Override
                public void onFailure(String errorMsg) {
                    gameBaseInfoView.showError("获取数据失败");
                }
            });
        }else{
            gameBaseInfoView.showError("没有网络连接");
        }
    }


    public void parseGameInfo(String result){
        NBAGameBaseInfo gameBaseInfo = JsonParser.parseWithGson(NBAGameBaseInfo.class,result);
        gameBaseInfoView.showGameBaseInfo(gameBaseInfo);
    }


}
