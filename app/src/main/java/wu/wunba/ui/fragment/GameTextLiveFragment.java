package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.model.NBAGameTextLiveItem;
import wu.wunba.ui.adapter.NBAGameTextInfoAdapter;
import wu.wunba.ui.presenter.NBAGameTextLivePresenter;
import wu.wunba.ui.view.NBAGameTextLiveView;

/**
 * 描述：图文直播页面
 * 作者：Wu on 2017/3/2 10:14
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.xrecyclerview)
public class GameTextLiveFragment extends BaseFragment implements NBAGameTextLiveView {

    @ViewInject(R.id.recyclerview)
    LRecyclerView recyclerview;
    @ViewInject(R.id.iv_no_data)
    ImageView ivNoData;
    @ViewInject(R.id.iv_network_error)
    ImageView ivNetworkError;

    public static final String BUNDLE_MID = "mid";
    public static final String MATCHPERIOD = "matchPeriod";

    private Handler mHandler = new Handler();

    private String mid;
    private String matchPeriod;
    private int TEXTS_PAGER_NUM = 1;
    private NBAGameTextInfoAdapter gameTextInfoAdapter;
    private NBAGameTextLivePresenter gameTextLivePresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<NBAGameTextLiveItem> infoList = new ArrayList<>();

    public static Fragment getInstance(String mid, String matchPeriod) {
        GameTextLiveFragment fragment = new GameTextLiveFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_MID, mid);
        bundle.putString(MATCHPERIOD, matchPeriod);
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
        gameTextLivePresenter = new NBAGameTextLivePresenter(getActivity(), this);
        gameTextLivePresenter.getNBAGameTextLiveIndex(TEXTS_PAGER_NUM, mid);
        initXRecyclerView();
    }


    private void initXRecyclerView() {
        gameTextInfoAdapter = new NBAGameTextInfoAdapter(getActivity());
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(gameTextInfoAdapter);
        recyclerview.setAdapter(mLRecyclerViewAdapter);
        recyclerview.setLoadMoreEnabled(true);
        recyclerview.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        recyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                TEXTS_PAGER_NUM = 1;
                infoList.clear();
                gameTextLivePresenter.getNBAGameTextLiveIndex(TEXTS_PAGER_NUM, mid);
            }
        });
        recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                TEXTS_PAGER_NUM++;
                gameTextLivePresenter.getNBAGameTextLiveIndex(TEXTS_PAGER_NUM, mid);
            }
        });
    }

    @Event(type = View.OnClickListener.class,value = R.id.iv_network_error)
    private void requestAggin(View v){
        gameTextLivePresenter.getNBAGameTextLiveIndex(TEXTS_PAGER_NUM, mid);
    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(0);
    }

    @Override
    public void showError(String msg) {
        if (msg.equals("0")) {
            ivNetworkError.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "网络连接异常", Toast.LENGTH_SHORT).show();
            recyclerview.setVisibility(View.GONE);
        }else{
            ivNetworkError.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
        }
        if(msg.equals("-1")){
            ivNoData.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);
        }else {
            ivNoData.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
        }
        recyclerview.refreshComplete(10);
        ballLaodingDismiss();
    }

    @Override
    public void hideLoading(boolean isFirst) {
        recyclerview.refreshComplete(10);
        ballLaodingDismiss();
        ivNoData.setVisibility(View.GONE);
        ivNetworkError.setVisibility(View.GONE);
        recyclerview.setVisibility(View.VISIBLE);
    }


    @Override
    public void showGameTextLive(boolean isUpdata,List<NBAGameTextLiveItem> nbaGameTextLiveItemList) {
        recyclerview.refreshComplete(10);
        if(isUpdata){
            for (int i=0;i<nbaGameTextLiveItemList.size();i++){
                infoList.set(i,nbaGameTextLiveItemList.get(i));
            }
        }else {
            infoList.addAll(nbaGameTextLiveItemList);
        }
        gameTextInfoAdapter.setData(infoList);
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

    private final int UPDATA_GAME_DATA_TIME = 3000;
    private Runnable mTasks = new Runnable() {
        @Override
        public void run() {
            if (matchPeriod.equals("1")) {
                gameTextLivePresenter.getGameTextLiveIndexTimer(mid);
            }
            mHandler.postDelayed(mTasks,UPDATA_GAME_DATA_TIME);
        }
    };
}
