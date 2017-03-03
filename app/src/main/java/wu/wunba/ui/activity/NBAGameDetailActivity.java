package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
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

public class NBAGameDetailActivity extends BaseSwipeBackCompatActivity implements NBAGameBaseInfoView {


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
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;


    private static String gameMid;
    private static String matchPeriod;
    private static String title;
    private static String leftTeam;
    private static String rightTeam;
    private static String leftTeamId;
    private static String rightTeamId;
    private boolean isGameLive = false;
    private String currentMatchPeriod; //当前的比赛状态
    private Handler mHandler = new Handler();
    private NBAGameDetailPresenter gameDetailPresenter;
    private List<Fragment> fragmentList = new ArrayList<>();

    private String[] title_1 = {"前瞻", "图文"};
    private String[] title_2 = {"图文", "数据", "统计", "集锦"};

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_game_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        MyStatusBarUtil.setStatusTransparent(mContext, false);
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
        gameDetailPresenter = new NBAGameDetailPresenter(mContext, this);
        gameDetailPresenter.getNBAGameInfo(gameMid);
        currentMatchPeriod = matchPeriod;
        initTablayout();
    }


    private void initTablayout() {
        fragmentList.clear();
        if (currentMatchPeriod.equals("0")) {
            fragmentList.add(GamePreviewFragment.getInstance(gameMid));
            fragmentList.add(GameTextLiveFragment.getInstance(gameMid, matchPeriod));
            ViewPagerFragmentAdatper adatper = new ViewPagerFragmentAdatper(getSupportFragmentManager(), fragmentList, title_1);
            viewPager.setAdapter(adatper);
            tabLayout.setupWithViewPager(viewPager);
        } else {
            fragmentList.add(GameTextLiveFragment.getInstance(gameMid, matchPeriod));
            fragmentList.add(GameLiveDataFragment.getInstance(gameMid));
            fragmentList.add(GameLiveStatisticsFragment.getInstance(gameMid, matchPeriod, leftTeam, rightTeam));
            fragmentList.add(GameLiveHighlightFragment.getInstance(gameMid));
            ViewPagerFragmentAdatper adatper = new ViewPagerFragmentAdatper(getSupportFragmentManager(), fragmentList, title_2);
            viewPager.setAdapter(adatper);
            tabLayout.setupWithViewPager(viewPager);
        }
    }


    @Override
    public void showError(String msg) {
        if (msg.equals("0")) {
            Toast.makeText(mContext, "网络连接异常", Toast.LENGTH_SHORT).show();
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
        Xutils3ImageUtils.display(ivLeftTeamIcon, gameBaseInfo.getData().getLeftBadge(),
                Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_default, R.mipmap.latest_pic_default));
        Xutils3ImageUtils.display(ivRightTeamIcon, gameBaseInfo.getData().getRightBadge(),
                Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_default, R.mipmap.latest_pic_default));
        String mPeriod = gameBaseInfo.getData().getMatchPeriod();
        currentMatchPeriod = mPeriod;
        if (currentMatchPeriod.equals("0")) {//未开始
            tvGameState.setTextColor(getResources().getColor(R.color.gray_cc));
            tvGameState.setText("未开始 " + gameBaseInfo.getData().getStartHour());
            isGameLive = false;
        } else if (currentMatchPeriod.equals("2")) {//已经结束
            tvGameState.setText("已结束");
            isGameLive = false;
            tvGameState.setTextColor(getResources().getColor(R.color.gray_cc));
        } else if (currentMatchPeriod.equals("1")) {//直播中
            tvGameState.setText(gameBaseInfo.getData().getQuarterDesc());
            tvGameState.setTextColor(Color.RED);
            isGameLive = true;
        }
        leftTeamId = gameBaseInfo.getData().getLeftId();
        rightTeamId = gameBaseInfo.getData().getRightId();
        if (!currentMatchPeriod.equals(matchPeriod)) {//比赛又为开始转变到开始直播 重新初始化tablayout
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
        leftTeam = bundle.getString(Config.LEFT_TEAM);
        rightTeam = bundle.getString(Config.RIGHT_TEAM);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mTasks);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mTasks, UPDATA_GAME_DATA_TIME);
    }

    private final int UPDATA_GAME_DATA_TIME = 5000;
    private Runnable mTasks = new Runnable() {
        @Override
        public void run() {
            if (!matchPeriod.equals("2")) {
                gameDetailPresenter.getNBAGameInfo(gameMid);
            }
            mHandler.postDelayed(mTasks, UPDATA_GAME_DATA_TIME);
        }
    };


    @OnClick({R.id.iv_left_team_icon, R.id.iv_right_team_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left_team_icon:
                Bundle bundle_1 = new Bundle();
                bundle_1.putString(Config.TEAM_ID, leftTeamId);
                bundle_1.putString(Config.TEAM_NAME, leftTeam);
                NBATeamDetailActivity.startAction(mContext, bundle_1);
                break;
            case R.id.iv_right_team_icon:
                Bundle bundle = new Bundle();
                bundle.putString(Config.TEAM_ID, rightTeamId);
                bundle.putString(Config.TEAM_NAME, rightTeam);
                NBATeamDetailActivity.startAction(mContext, bundle);
                break;
        }
    }
}
