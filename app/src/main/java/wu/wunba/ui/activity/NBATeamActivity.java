package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.Bind;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.NBATeam;
import wu.wunba.ui.adapter.NBATeamAdapter;
import wu.wunba.ui.presenter.NBATeamPresenter;
import wu.wunba.ui.view.NBATeamView;
import wu.wunba.ui.widget.BasketballLoading;

/**
 * 描述：
 * 作者：Wu on 2017/3/1 09:37
 * 邮箱：wuwende@live.cn
 */

public class NBATeamActivity extends BaseSwipeBackCompatActivity implements NBATeamView,NBATeamAdapter.OnRecyclerViewItemClickListener{


    @Bind(R.id.recyclerview)
    LRecyclerView recyclerview;
    @Bind(R.id.common_toolbar)
    Toolbar toolbar;

    private NBATeamAdapter teamAdapter;
    private NBATeamPresenter teamPresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_team;
    }


    @Override
    protected void initViewsAndEvents() {
        toolbar.setTitle("球队");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        teamPresenter = new NBATeamPresenter(mContext,this);
        teamPresenter.initialized(0);

        initRecyclerview();
    }

    private void initRecyclerview() {
        teamAdapter = new NBATeamAdapter(mContext);
        teamAdapter.setOnRecyclerViewItemClickListener(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(teamAdapter);
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerview.setAdapter(mLRecyclerViewAdapter);
        recyclerview.setLoadMoreEnabled(false);

        recyclerview.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                teamPresenter.getNBATeamList();
            }
        });
    }


    @Override
    public void onItemClick(View v, NBATeam nbaTeam, int postion) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.TEAM_ID,nbaTeam.getTeamId());
        bundle.putString(Config.TEAM_NAME,nbaTeam.getTeamName());
        NBATeamDetailActivity.startAction(mContext,bundle);
    }

    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
        recyclerview.refreshComplete(15);
    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(BasketballLoading.TYPE_QUAN);
    }

    @Override
    public void showError(String msg) {
        if (msg.equals("0")){
            Toast.makeText(mContext,"网络连接异常",Toast.LENGTH_LONG).show();
        }
        recyclerview.refreshComplete(15);
    }


    @Override
    public void showTeam(List<NBATeam> nbaTeamList) {
        Logger.d("返回数据：" + nbaTeamList.size());
        teamAdapter.setData(nbaTeamList);
    }


    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, NBATeamActivity.class);
        context.startActivity(intent);
    }
}
