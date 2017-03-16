package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.NBAPlayer;
import wu.wunba.model.NBATeamBaseInfo;
import wu.wunba.ui.adapter.NBATeamPlayerAdapter;
import wu.wunba.ui.presenter.NBATeamBasnInfoPresenter;
import wu.wunba.ui.view.NBATeamBaseInfoView;
import wu.wunba.ui.widget.BasketballLoading;
import wu.wunba.utils.MyStatusBarUtil;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 11:44
 * 邮箱：wuwende@live.cn
 */

public class NBATeamDetailActivity extends BaseSwipeBackCompatActivity implements NBATeamBaseInfoView ,NBATeamPlayerAdapter.OnRecyclerViewItemClickListener{


    @Bind(R.id.iv_team_icon)
    ImageView ivTeamIcon;
    @Bind(R.id.tv_team_name)
    TextView tvTeamName;
    @Bind(R.id.iv_team_detail)
    ImageView ivTeamDetail;
    @Bind(R.id.tv_record)
    TextView tvRecord;
    @Bind(R.id.tv_team_rank)
    TextView tvTeamRank;
    @Bind(R.id.tv_recent)
    TextView tvRecent;
    @Bind(R.id.tv_team_coach)
    TextView tvTeamCoach;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.tv_team_introduce)
    TextView tvTeamIntroduce;
    @Bind(R.id.tv_points)
    TextView tvPoints;
    @Bind(R.id.tv_rebound)
    TextView tvRebound;
    @Bind(R.id.tv_assist)
    TextView tvAssist;
    @Bind(R.id.tv_block)
    TextView tvBlock;
    @Bind(R.id.tv_steal)
    TextView tvSteal;

    @Bind(R.id.ll_detail_body)
    LinearLayout llTeamBody;
    @Bind(R.id.tv_points_rank)
    TextView tvPointsRank;
    @Bind(R.id.tv_rebound_rank)
    TextView tvReboundRank;
    @Bind(R.id.tv_assist_rank)
    TextView tvAssistRank;
    @Bind(R.id.tv_block_rank)
    TextView tvBlockRank;
    @Bind(R.id.tv_steal_rank)
    TextView tvStealRank;
    @Bind(R.id.tv_oppPoint)
    TextView tvOppPoint;
    @Bind(R.id.tv_oppPoint_rank)
    TextView tvOppPointRank;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    private static String teamId;
    private static String teamName;
    private NBATeamPlayerAdapter teamPlayerAdapter;
    private NBATeamBasnInfoPresenter basnInfoPresenter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_team_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        MyStatusBarUtil.setStatusTransparent(this,false);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText(teamName);
        basnInfoPresenter = new NBATeamBasnInfoPresenter(mContext, this);
        basnInfoPresenter.getNBATeamInfo(teamId);
        initTablayout();
        teamPlayerAdapter = new NBATeamPlayerAdapter(mContext);
        teamPlayerAdapter.setOnItemClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(teamPlayerAdapter);
    }


    private void initTablayout() {
        tabs.addTab(tabs.newTab().setText("球队详情"));
        tabs.addTab(tabs.newTab().setText("球队阵容"));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {//球队详情
                    basnInfoPresenter.getNBATeamInfo(teamId);
                    recyclerView.setVisibility(View.GONE);
                    llTeamBody.setVisibility(View.VISIBLE);
                } else if (tab.getPosition() == 1) {//球队阵容
                    basnInfoPresenter.getNBATeamPlayers(teamId);
                    llTeamBody.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }


    @Override
    public void onItemClick(View v, NBAPlayer nbaPlayer, int postion) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.PLAYER_ID,nbaPlayer.getId());
        NBAPlayerDetialActivity.startAction(mContext,bundle);
    }

    /**
     * @param context
     */
    public static void startAction(Activity context, Bundle bundle) {
        Intent intent = new Intent(context, NBATeamDetailActivity.class);
        context.startActivity(intent);
        teamId = bundle.getString(Config.TEAM_ID);
        teamName = bundle.getString(Config.TEAM_NAME);
    }


    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
    }

    @Override
    public void showError(String msg) {
        ballLaodingDismiss();
        Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();

    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(BasketballLoading.TYPE_QUAN);
    }



    @Override
    public void showTeamPlayers(List<NBAPlayer> playerList) {
        teamPlayerAdapter.setData(playerList);
    }


    @Override
    public void showTeamBaseInfo(NBATeamBaseInfo baseInfo) {
        tvTeamName.setText(baseInfo.getData().getBaseInfo().getTeamName());
        tvRecord.setText(baseInfo.getData().getRankInfo().getWins() + "胜" +
                "-" + baseInfo.getData().getRankInfo().getLosses() + "负");
        tvTeamRank.setText(baseInfo.getData().getRankInfo().getConferenceRank());
        tvRecent.setText(baseInfo.getData().getRankInfo().getStreak());
        tvTeamCoach.setText(baseInfo.getData().getBaseInfo().getCoach());
        Xutils3ImageUtils.display(ivTeamIcon, baseInfo.getData().getBaseInfo().getTeamLogo(),
                Xutils3ImageUtils.getImageOptionsDefault());
        tvTeamIntroduce.setText("        " + baseInfo.getData().getBaseInfo().getIntroduction());
        tvPoints.setText(baseInfo.getData().getStats().getPoint());
        tvRebound.setText(baseInfo.getData().getStats().getRebound());
        tvAssist.setText(baseInfo.getData().getStats().getAssist());
        tvBlock.setText(baseInfo.getData().getStats().getBlock());
        tvSteal.setText(baseInfo.getData().getStats().getSteal());
        tvOppPoint.setText(baseInfo.getData().getStats().getOppPoint());
        tvPointsRank.setText("联盟第"+baseInfo.getData().getStatsRank().getPoint());
        tvReboundRank.setText("联盟第"+baseInfo.getData().getStatsRank().getRebound());
        tvAssistRank.setText("联盟第"+baseInfo.getData().getStatsRank().getAssist());
        tvBlockRank.setText("联盟第"+baseInfo.getData().getStatsRank().getBlock());
        tvStealRank.setText("联盟第"+baseInfo.getData().getStatsRank().getSteal());
        tvOppPointRank.setText("联盟第"+baseInfo.getData().getStatsRank().getOppPoint());
    }

    @OnClick(R.id.iv_team_detail)
    public void onClick() {
        Toast.makeText(mContext, "球队详情", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
