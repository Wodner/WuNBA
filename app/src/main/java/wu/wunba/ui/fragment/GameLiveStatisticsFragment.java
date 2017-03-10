package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.model.NBAGameLvieStatisticsItem;
import wu.wunba.ui.adapter.NBAGameLiveStatisticsAdapter;
import wu.wunba.ui.presenter.NBAGameLiveStatisticsPresenter;
import wu.wunba.ui.view.NBAGameLiveStatisticsView;
import wu.wunba.ui.widget.BasketballLoading;

import static wu.wunba.ui.fragment.GameTextLiveFragment.BUNDLE_MID;
import static wu.wunba.ui.fragment.GameTextLiveFragment.MATCHPERIOD;

/**
 * 描述：直播数据统计
 * 作者：Wu on 2017/3/2 10:22
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_nba_game_live_statistics)
public class GameLiveStatisticsFragment extends BaseFragment implements NBAGameLiveStatisticsView{

    @ViewInject(R.id.ll_statistics_left)
    LinearLayout linearLayoutLeftSide;
    @ViewInject(R.id.tabs)
    TabLayout tabs;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerView;
    @ViewInject(R.id.iv_no_data)
    ImageView ivNoData;
    @ViewInject(R.id.iv_network_error)
    ImageView ivNetworkError;
    @ViewInject(R.id.scrollView)
    ScrollView scrollView;


    private static final String LT = "leftTeam";
    private static final String RT = "rightTeam";

    private String mid;
    private String matchPeriod;
    private String leftTeam;
    private String rightTeam;
    private boolean isLeft = true;
    private Handler mHandler = new Handler();
    private NBAGameLiveStatisticsAdapter statisticsAdapter;
    private NBAGameLiveStatisticsPresenter statisticsPresenter;

    public static Fragment getInstance(String mid, String matchPeriod,String lt,String rt) {
        GameLiveStatisticsFragment fragment = new GameLiveStatisticsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_MID, mid);
        bundle.putString(MATCHPERIOD, matchPeriod);
        bundle.putString(LT, lt);
        bundle.putString(RT, rt);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mid = getArguments().getString(BUNDLE_MID);
        matchPeriod = getArguments().getString(MATCHPERIOD);
        leftTeam = getArguments().getString(LT);
        rightTeam = getArguments().getString(RT);
        statisticsPresenter = new NBAGameLiveStatisticsPresenter(getActivity(),this);
        statisticsPresenter.getNBAGameLiveStatistics(true,mid);
        statisticsAdapter = new NBAGameLiveStatisticsAdapter(getActivity());
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(statisticsAdapter);
        tabs.addTab(tabs.newTab().setText(leftTeam));
        tabs.addTab(tabs.newTab().setText(rightTeam));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){//左边球队
                    isLeft = true;
                    statisticsPresenter.getNBAGameLiveStatistics(true,mid);
                }else if(tab.getPosition()==1){//右边球队
                    isLeft = false;
                    statisticsPresenter.getNBAGameLiveStatistics(true,mid);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }


    @Event(type = View.OnClickListener.class,value = R.id.iv_network_error)
    private void requestAggin(View v){
        statisticsPresenter.getNBAGameLiveStatistics(true,mid);
    }

    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
    }

    @Override
    public void showError(String msg) {
        ballLaodingDismiss();
        if(msg.equals("0")){
            scrollView.setVisibility(View.GONE);
            ivNetworkError.setVisibility(View.VISIBLE);
        }else {
            scrollView.setVisibility(View.VISIBLE);
            ivNetworkError.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(BasketballLoading.TYPE_QUAN);
    }



    @Override
    public void showRightStatistics(List<String> rightSide, NBAGameLvieStatisticsItem rightStatistics) {
        scrollView.setVisibility(View.VISIBLE);
        ivNetworkError.setVisibility(View.GONE);
        if(!isLeft){
            showRightTeam(rightSide, rightStatistics);
        }
    }



    @Override
    public void showLefeStatistics(List<String> leftSide, NBAGameLvieStatisticsItem leftStatistics) {
        if(isLeft){
            showLeftTeam(leftSide, leftStatistics);
        }
    }

    /**
     * @param leftSide
     * @param leftStatisticsItems
     */
    private void showLeftTeam(List<String> leftSide, NBAGameLvieStatisticsItem leftStatisticsItems) {
        linearLayoutLeftSide.removeAllViews();
        for (int i=0;i<leftSide.size();i++){
            TextView tvSide = new TextView(getActivity());
            tvSide.setLayoutParams(getLinearLayoutTextViewParms(10, 10, 30, 30));
            tvSide.setText(leftSide.get(i));
            linearLayoutLeftSide.addView(tvSide);
            if(i==0 || i==leftSide.size()-2 ||i==5){
                View view = new View(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.height=1;
                params.width =LinearLayout.LayoutParams.MATCH_PARENT;
                view.setLayoutParams(params);
                view.setBackgroundColor(getResources().getColor(R.color.gray_cc));
                linearLayoutLeftSide.addView(view);
            }
        }
        statisticsAdapter.setData(leftStatisticsItems);
    }

    /**
     * @param rightSide
     * @param rightStatisticsItems
     */
    private void showRightTeam(List<String> rightSide, NBAGameLvieStatisticsItem rightStatisticsItems) {
        linearLayoutLeftSide.removeAllViews();
        for (int i=0;i<rightSide.size();i++){
            TextView tvSide = new TextView(getActivity());
            tvSide.setLayoutParams(getLinearLayoutTextViewParms(10, 10, 30, 30));
            tvSide.setText(rightSide.get(i));
            linearLayoutLeftSide.addView(tvSide);
            if(i==0 || i==rightSide.size()-2 ||i==5){
                View view = new View(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.height=1;
                params.width =LinearLayout.LayoutParams.MATCH_PARENT;
                view.setLayoutParams(params);
                view.setBackgroundColor(getResources().getColor(R.color.gray_cc));
                linearLayoutLeftSide.addView(view);
            }
        }
        statisticsAdapter.setData(rightStatisticsItems);
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mTasks);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mTasks,UPDATA_GAME_DATA_TIME);
    }

    private final int UPDATA_GAME_DATA_TIME = 10000;
    private Runnable mTasks = new Runnable() {
        @Override
        public void run() {
            if (matchPeriod.equals("1")) {
                statisticsPresenter.getNBAGameLiveStatistics(false,mid);
            }
            mHandler.postDelayed(mTasks,UPDATA_GAME_DATA_TIME);
        }
    };


    /**
     * @param t
     * @param b
     * @param l
     * @param r
     * @return
     */
    private LinearLayout.LayoutParams getLinearLayoutTextViewParms(int t, int b, int l, int r) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = t;
        params.bottomMargin = b;
        params.leftMargin = l;
        params.rightMargin = r;
        return params;
    }
}
