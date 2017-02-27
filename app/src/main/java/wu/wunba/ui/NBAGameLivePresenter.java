package wu.wunba.ui;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import wu.wunba.http.JsonParser;
import wu.wunba.http.NBAApiRequest;
import wu.wunba.http.RequestCallBack;
import wu.wunba.model.NBAMatch;
import wu.wunba.ui.view.NBAGameLiveView;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/27 10:17
 * 邮箱：wuwende@live.cn
 */

public class NBAGameLivePresenter  implements Presenter{

    private Context mContext;
    private NBAGameLiveView gameLiveView;
    private SimpleDateFormat format;
    private Calendar cal;
    private String date;
    private List<NBAMatch> nbaMatchList;


    public NBAGameLivePresenter(Context mContext, NBAGameLiveView gameLiveView) {
        this.mContext = mContext;
        this.gameLiveView = gameLiveView;
        nbaMatchList = new ArrayList<>();
        initCalendar();
    }

    @Override
    public void initialized(int type) {
        gameLiveView.showLoading(true);
        getNBAGameLive(date);
    }

    private void initCalendar() {
        cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        format = new SimpleDateFormat("yyyy/MM/dd");
        date = format.format(cal.getTime());
    }


    /**
     * @param date
     */
    public void getNBAGameLive(String date){
        if(MyUtils.isNetworkConnected(mContext)){
            NBAApiRequest.getNBAGameLive(date, new RequestCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    parseNBAGameTimeData(s);
                }

                @Override
                public void onFailure(String errorMsg) {
                    gameLiveView.hideLoading(true);
                }
            });
        }else {
            gameLiveView.showError("-1");
            gameLiveView.hideLoading(true);
        }
    }


    private void parseNBAGameTimeData(String result) {
        NBAMatch gameMatch = JsonParser.parseWithGson(NBAMatch.class,result);
        if(gameMatch.getData().getMatches().size()>0){
            if (gameMatch.getData().getMatches().get(0).getMatchInfo().getMatchPeriod().equals("1")
                    || gameMatch.getData().getMatches().get(gameMatch.getData().getMatches().size()-1).getMatchInfo().getMatchPeriod().equals("1")){
                gameLiveView.showNBAGameIsLive(true);
            }else {
                gameLiveView.showNBAGameIsLive(false);
            }
            gameLiveView.showNBAGameLive(gameMatch);
            gameLiveView.hideLoading(true);
            gameLiveView.noNBAGame(false);
        }else{
            gameLiveView.noNBAGame(true);
            gameLiveView.hideLoading(true);
        }
    }
}
