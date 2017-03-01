package wu.wunba.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.DecorationNameBean;
import wu.wunba.model.NBATeamRank;
import wu.wunba.ui.activity.NBATeamDetailActivity;
import wu.wunba.ui.adapter.NBATeamRankAdapter;
import wu.wunba.ui.presenter.NBATeamRankPresenter;
import wu.wunba.ui.view.NBATeamRankView;
import wu.wunba.ui.widget.BasketballLoading;
import wu.wunba.ui.widget.ItemRecyclerDecoration;

import static wu.wunba.ui.presenter.NBATeamRankPresenter.TYPE_RANK_BY_DIVISION;
import static wu.wunba.ui.presenter.NBATeamRankPresenter.TYPE_RANK_BY_UNION;

/**
 * 描述：球队排名
 * 作者：Wu on 2017/2/20 22:31
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.recyclerview)
public class NbaTeamRankingFragment extends BaseFragment implements NBATeamRankView,NBATeamRankAdapter.OnRecyclerViewItemClickListener,ItemRecyclerDecoration.DecorationCallback{


    @ViewInject(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @ViewInject(R.id.spinner)
    Spinner spinner;

    private Activity mContext;
    private int CURRENT_TYPE = TYPE_RANK_BY_UNION;
    private NBATeamRankPresenter teamRankPresenter;
    private NBATeamRankAdapter nbaTeamRankAdapter;
    public static NbaTeamRankingFragment teamRankingFragment;

    private List<DecorationNameBean> decorationNameBeenList = new ArrayList<>();

    public static NbaTeamRankingFragment getInstance(){
        if(teamRankingFragment == null){
            teamRankingFragment = new NbaTeamRankingFragment();
        }
        return teamRankingFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        initXRecyclerView();
        teamRankPresenter = new NBATeamRankPresenter(mContext,this);
        teamRankPresenter.initialized(0);
        initSpinner();
    }


    private void initSpinner(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ballLaodingDismiss();
                if(pos==0){
                    CURRENT_TYPE = TYPE_RANK_BY_UNION;
                    teamRankPresenter.getNBATeamRank(CURRENT_TYPE);
                }else{
                    CURRENT_TYPE = TYPE_RANK_BY_DIVISION;
                    teamRankPresenter.getNBATeamRank(CURRENT_TYPE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }


    private void initXRecyclerView() {
        nbaTeamRankAdapter = new NBATeamRankAdapter(mContext);
        nbaTeamRankAdapter.setOnRecyclerViewItemClickListener(this);
        mRecyclerView.addItemDecoration(new ItemRecyclerDecoration(mContext,this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(nbaTeamRankAdapter);
        // 设置下拉刷新的圆的颜色
//        swipeRefreshLayout.setColorScheme(
//                getResources().getColor(R.color.red), getResources().getColor(R.color.default_color),
//                getResources().getColor(R.color.green), getResources().getColor(R.color.yellow));


        //绑定视图刷新的监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO
                //重新获取完网络数据刷新Adapter，完成后需要调用onRefreshComplete方法取消滑出来的圆形进度
                teamRankPresenter.getNBATeamRank(CURRENT_TYPE);
            }
        });
    }


    @Override
    public String getGroupFirstLine(int position) {
        if (decorationNameBeenList.get(position).getName()!=null){
            return decorationNameBeenList.get(position).getName();
        }
        return "";
    }

    @Override
    public long getGroupId(int position) {
        if (decorationNameBeenList.get(position).getName()!=null){
            return Character.toUpperCase(decorationNameBeenList.get(position).getName().charAt(0));
        }
        return -1;
    }

    @Override
    public void onItemClick(View v, NBATeamRank nbaTeamRank, int postion) {
        Logger.d("==== " + nbaTeamRank.getName());
        Bundle bundle = new Bundle();
        bundle.putString(Config.TEAM_ID,nbaTeamRank.getTeamId());
        bundle.putString(Config.TEAM_NAME,nbaTeamRank.getName());
        NBATeamDetailActivity.startAction(mContext,bundle);
    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(BasketballLoading.TYPE_QUAN);
    }


    @Override
    public void showError(String msg) {
        ballLaodingDismiss();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRanking(List<NBATeamRank> nbaTeamRankList) {
        ballLaodingDismiss();
        decorationNameBeenList.clear();
        for(NBATeamRank rank:nbaTeamRankList){
            DecorationNameBean nameBean = new DecorationNameBean();
            nameBean.setName(rank.getTitle());
            decorationNameBeenList.add(nameBean);
        }
        nbaTeamRankAdapter.setData(nbaTeamRankList);
    }
}
