package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.NBAGameBaseInfo;
import wu.wunba.ui.presenter.NBAGameDetailPresenter;
import wu.wunba.ui.view.NBAGameBaseInfoView;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：比赛详情
 * 作者：Wu on 2017/3/1 20:23
 * 邮箱：wuwende@live.cn
 */

public class NBAGameDetailActivity extends BaseSwipeBackCompatActivity implements NBAGameBaseInfoView{


    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tv_game_type)
    TextView tvGameType;
    @Bind(R.id.tv_left_team_goal)
    TextView tvLeftTeamGoal;
    @Bind(R.id.tv_right_team_goal)
    TextView tvRightTeamGoal;
    @Bind(R.id.iv_left_team_icon)
    ImageView ivLeftTeamIcon;
    @Bind(R.id.iv_right_team_icon)
    ImageView ivRightTeamIcon;
    @Bind(R.id.tv_game_state)
    TextView tvGameState;
    @Bind(R.id.tv_right_team_record)
    TextView tvRightTeamRecord;
    @Bind(R.id.tv_left_team_record)
    TextView tvLeftTeamRecord;
    @Bind(R.id.tv_nba_game_time_place)
    TextView tvNbaGameTimePlace;


    private static String gameMid;
    private static String matchPeriod;
    private static String title;
    private boolean isGameLive = false;
    private UpdataGameDataThread updataGameDataThread;
    private NBAGameDetailPresenter gameDetailPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_game_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText(title);
        gameDetailPresenter = new NBAGameDetailPresenter(mContext,this);
        gameDetailPresenter.getNBAGameInfo(gameMid);
        updataGameDataThread = new UpdataGameDataThread();
        updataGameDataThread.start();
    }


    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoading(boolean isFirst) {

    }

    @Override
    public void hideLoading(boolean isFirst) {

    }


    @Override
    public void showGameBaseInfo(NBAGameBaseInfo gameBaseInfo) {
        tvGameType.setText(gameBaseInfo.getData().getDesc());
        tvLeftTeamGoal.setText(gameBaseInfo.getData().getLeftGoal());
        tvRightTeamGoal.setText(gameBaseInfo.getData().getRightGoal());
        tvLeftTeamRecord.setText(gameBaseInfo.getData().getLeftWins()+"胜-" + gameBaseInfo.getData().getLeftLosses()+"负");
        tvRightTeamRecord.setText(gameBaseInfo.getData().getRightWins()+"胜-" + gameBaseInfo.getData().getRightLosses()+"负");
        tvNbaGameTimePlace.setText(gameBaseInfo.getData().getStartDate()+ "  " + gameBaseInfo.getData().getStartHour() + "  " + gameBaseInfo.getData().getVenue());
        Xutils3ImageUtils.display(ivLeftTeamIcon,gameBaseInfo.getData().getLeftBadge(),Xutils3ImageUtils.getImageOptionsDefault());
        Xutils3ImageUtils.display(ivRightTeamIcon,gameBaseInfo.getData().getRightBadge(),Xutils3ImageUtils.getImageOptionsDefault());
        String matchPeriod =gameBaseInfo.getData().getMatchPeriod();
        if(matchPeriod.equals("未开始")){//未开始
            tvGameState.setTextColor(getResources().getColor(R.color.gray_cc));
            tvGameState.setText(matchPeriod);
            isGameLive = false;
        }else if(matchPeriod.equals("2")){//已经结束
            tvGameState.setText("已结束");
            isGameLive = false;
            tvGameState.setTextColor(getResources().getColor(R.color.gray_cc));
        }else if(matchPeriod.equals("1")){//直播中
            tvGameState.setText("直播 " + gameBaseInfo.getData().getQuarterDesc());
            tvGameState.setTextColor(Color.RED);
            isGameLive = true;
        }
    }

    /**
     * @param context
     */
    public static void startAction(Activity context, Bundle bundle) {
        Intent intent = new Intent(context, NBAGameDetailActivity.class);
        context.startActivity(intent);
        gameMid = bundle.getString(Config.GAME_MID);
        matchPeriod = bundle.getString(Config.MATCH_PERIOD);
        title = bundle.getString(Config.GAME_VS);
    }

    /**
     * 每隔十秒更新数据
     */
    private class UpdataGameDataThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try{
                    Thread.sleep(10000);
                    if(isGameLive){
                        gameDetailPresenter.getNBAGameInfo(gameMid);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(updataGameDataThread!=null && updataGameDataThread.isAlive()){
            updataGameDataThread.interrupt();
        }
    }
}
