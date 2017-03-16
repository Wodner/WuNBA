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
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.NBAPlayerBaseInfo;
import wu.wunba.model.NBAPlayerData;
import wu.wunba.ui.adapter.NBAPlayerDataAdapter;
import wu.wunba.ui.presenter.NBAPlayerDetailPresenter;
import wu.wunba.ui.view.NBAPlayerDetailView;
import wu.wunba.ui.widget.BasketballLoading;
import wu.wunba.utils.MyStatusBarUtil;
import wu.wunba.utils.Xutils3ImageUtils;

import static wu.wunba.ui.presenter.NBAPlayerDetailPresenter.TYPE_CAREER;
import static wu.wunba.ui.presenter.NBAPlayerDetailPresenter.TYPE_SEASON;

/**
 * 描述：球员详情
 * 作者：Wu on 2017/2/19 20:24
 * 邮箱：wuwende@live.cn
 */
public class NBAPlayerDetialActivity extends BaseSwipeBackCompatActivity implements NBAPlayerDetailView {

    @Bind(R.id.iv_player_icon)
    ImageView ivPlayerIcon;
    @Bind(R.id.iv_team_icon)
    ImageView ivTeamIcon;
    @Bind(R.id.tv_birth)
    TextView tvBirth;
    @Bind(R.id.tv_draftyear)
    TextView tvDraftyear;
    @Bind(R.id.tv_team_name)
    TextView tvTeamName;
    @Bind(R.id.tv_position)
    TextView tvPosition;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.tv_jsersey_num)
    TextView tvJserseyNum;
    @Bind(R.id.tv_player_name)
    TextView tvPlayerName;
    @Bind(R.id.tv_player_en_name)
    TextView tvPlayerEnName;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_title)
    TextView tvTitle;

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
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    private static String playerId;
    private NBAPlayerDataAdapter playerDataAdapter;
    private NBAPlayerDetailPresenter playerDetailPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_player_detail;
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
        tvTitle.setText("详情");
        initTablayout();
        playerDetailPresenter = new NBAPlayerDetailPresenter(mContext, this);
        playerDetailPresenter.getPlayerBaseInfo(playerId);
        playerDetailPresenter.getPlayerMatchData(playerId,TYPE_SEASON);
        playerDataAdapter = new NBAPlayerDataAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(playerDataAdapter);
    }

    private void initTablayout() {
        tabs.addTab(tabs.newTab().setText("赛季数据"));
        tabs.addTab(tabs.newTab().setText("生涯数据"));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){//赛季
                    playerDetailPresenter.getPlayerMatchData(playerId,TYPE_SEASON);
                }else if(tab.getPosition()==1){//生涯
                    playerDetailPresenter.getPlayerMatchData(playerId,TYPE_CAREER);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }


    @Override
    public void showError(String msg) {
        ballLaodingDismiss();
    }


    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(BasketballLoading.TYPE_QUAN);
    }

    @Override
    public void showPlayerData(List<NBAPlayerData> playerDataList) {
        Logger.w("回调的数据 ： " + playerDataList.size());
        playerDataAdapter.setData(playerDataList);
    }

    @Override
    public void showPlayerBaseInfo(NBAPlayerBaseInfo baseInfo) {
        tvBirth.setText(baseInfo.getData().getBirthDate());
        tvDraftyear.setText(baseInfo.getData().getDraftYear());
        tvHeight.setText(baseInfo.getData().getHeight());
        tvJserseyNum.setText(baseInfo.getData().getJerseyNum());
        tvPlayerEnName.setText(baseInfo.getData().getEnName());
        tvPlayerName.setText(baseInfo.getData().getCnName());
        tvPosition.setText(baseInfo.getData().getPosition());
        tvTeamName.setText(baseInfo.getData().getTeamName());
        tvWeight.setText(baseInfo.getData().getWeight());
        Xutils3ImageUtils.display(ivPlayerIcon, baseInfo.getData().getLogo(), Xutils3ImageUtils.getImageOptionsDefaultNoLoadingFailure());
        Xutils3ImageUtils.display(ivTeamIcon, baseInfo.getData().getTeamLogo(), Xutils3ImageUtils.getImageOptionsDefaultNoLoadingFailure());
        tvPoints.setText(baseInfo.getData().getStats().getPoints());
        tvRebound.setText(baseInfo.getData().getStats().getRebounds());
        tvAssist.setText(baseInfo.getData().getStats().getAssists());
        tvBlock.setText(baseInfo.getData().getStats().getBlocks());
        tvSteal.setText(baseInfo.getData().getStats().getSteals());
    }

    /**
     * @param context
     */
    public static void startAction(Activity context, Bundle bundle) {
        Intent intent = new Intent(context, NBAPlayerDetialActivity.class);
        context.startActivity(intent);
        playerId = bundle.getString(Config.PLAYER_ID);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
