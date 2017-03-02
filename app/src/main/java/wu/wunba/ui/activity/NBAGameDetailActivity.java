package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.NBAGameBaseInfo;
import wu.wunba.ui.adapter.ViewPagerFragmentAdatper;
import wu.wunba.ui.fragment.GameLiveDataFragment;
import wu.wunba.ui.fragment.GameLiveHighlightFragment;
import wu.wunba.ui.fragment.GameLiveStatisticsFragment;
import wu.wunba.ui.fragment.GamePreviewFragment;
import wu.wunba.ui.fragment.GameTextLiveFragment;
import wu.wunba.ui.presenter.NBAGameDetailPresenter;
import wu.wunba.ui.view.NBAGameBaseInfoView;
import wu.wunba.utils.MyStatusBarUtil;
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
//    @Bind(R.id.tv_right_team_record)
//    TextView tvRightTeamRecord;
//    @Bind(R.id.tv_left_team_record)
//    TextView tvLeftTeamRecord;

//    @Bind(R.id.tv_nba_game_time_place)
//    TextView tvNbaGameTimePlace;

    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;


    private static String gameMid;
    private static String matchPeriod;
    private static String title;
    private boolean isGameLive = false;
    private String currentMatchPeriod; //当前的比赛状态
    private UpdataGameDataThread updataGameDataThread;
    private NBAGameDetailPresenter gameDetailPresenter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPagerFragmentAdatper adatper;

    private String [] title_1 ={"前瞻","图文"};
    private String [] title_2 ={"图文","数据","统计","集锦"};

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_game_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        MyStatusBarUtil.setStatusTransparent(mContext,false);
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
        currentMatchPeriod = matchPeriod;
        initTablayout();
    }


    private void initTablayout(){
        fragmentList.clear();
        if(currentMatchPeriod.equals("0")){
            fragmentList.add( GamePreviewFragment.getInstance(gameMid));
            fragmentList.add( GameTextLiveFragment.getInstance(gameMid,matchPeriod));
            adatper = new ViewPagerFragmentAdatper(getSupportFragmentManager(),fragmentList,title_1);
            viewPager.setAdapter(adatper);
            tabLayout.setupWithViewPager(viewPager);
        }else {
            fragmentList.add( GameTextLiveFragment.getInstance(gameMid,matchPeriod));
            fragmentList.add( GameLiveDataFragment.getInstance(gameMid));
            fragmentList.add( GameLiveStatisticsFragment.getInstance(gameMid));
            fragmentList.add( GameLiveHighlightFragment.getInstance(gameMid));
            adatper = new ViewPagerFragmentAdatper(getSupportFragmentManager(),fragmentList,title_2);
            viewPager.setAdapter(adatper);
            tabLayout.setupWithViewPager(viewPager);
        }
    }



    @Override
    public void showError(String msg) {
        if(msg.equals("0")){
            Toast.makeText(mContext,"网络连接异常",Toast.LENGTH_SHORT).show();
        }
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
        Xutils3ImageUtils.display(ivLeftTeamIcon,gameBaseInfo.getData().getLeftBadge(),Xutils3ImageUtils.getImageOptionsDefault());
        Xutils3ImageUtils.display(ivRightTeamIcon,gameBaseInfo.getData().getRightBadge(),Xutils3ImageUtils.getImageOptionsDefault());
        String mPeriod =gameBaseInfo.getData().getMatchPeriod();
        currentMatchPeriod = mPeriod;
        if(currentMatchPeriod.equals("0")){//未开始
            tvGameState.setTextColor(getResources().getColor(R.color.gray_cc));
            tvGameState.setText("未开始 "+gameBaseInfo.getData().getStartHour());
            isGameLive = false;
        }else if(currentMatchPeriod.equals("2")){//已经结束
            tvGameState.setText("已结束");
            isGameLive = false;
            tvGameState.setTextColor(getResources().getColor(R.color.gray_cc));
        }else if(currentMatchPeriod.equals("1")){//直播中
            tvGameState.setText(gameBaseInfo.getData().getQuarterDesc());
            tvGameState.setTextColor(Color.RED);
            isGameLive = true;
        }
        if(!currentMatchPeriod.equals(matchPeriod)){//比赛又为开始转变到开始直播 重新初始化tablayout
            initTablayout();
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
                    Logger.d("定时器 ： " + isGameLive);
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
