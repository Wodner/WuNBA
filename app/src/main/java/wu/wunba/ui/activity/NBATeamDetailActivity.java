package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.NBATeamBaseInfo;
import wu.wunba.ui.presenter.NBATeamBasnInfoPresenter;
import wu.wunba.ui.view.NBATeamBaseInfoView;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 11:44
 * 邮箱：wuwende@live.cn
 */

public class NBATeamDetailActivity extends BaseSwipeBackCompatActivity implements NBATeamBaseInfoView{


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


    private static String teamId;
    private static String teamName;
    private NBATeamBasnInfoPresenter basnInfoPresenter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_team_detail;
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
        tvTitle.setText(teamName);
        basnInfoPresenter = new NBATeamBasnInfoPresenter(mContext,this);
        basnInfoPresenter.getNBATeamInfo(teamId);
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

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoading(boolean isFirst) {

    }

    @Override
    public void showTeamBaseInfo(NBATeamBaseInfo baseInfo) {
        tvTeamName.setText(baseInfo.getData().getBaseInfo().getTeamName());
        tvRecord.setText(baseInfo.getData().getRankInfo().getWins() + "胜" +
                "-" + baseInfo.getData().getRankInfo().getLosses() + "负");
        tvTeamRank.setText(baseInfo.getData().getRankInfo().getConferenceRank());
        tvRecent.setText(baseInfo.getData().getRankInfo().getStreak());
        tvTeamCoach.setText(baseInfo.getData().getBaseInfo().getCoach());
        Xutils3ImageUtils.display(ivTeamIcon,baseInfo.getData().getBaseInfo().getTeamLogo(),
                Xutils3ImageUtils.getImageOptionsDefault());
    }

    @OnClick(R.id.iv_team_detail)
    public void onClick() {
        Toast.makeText(mContext,"球队详情",Toast.LENGTH_SHORT).show();
    }
}
