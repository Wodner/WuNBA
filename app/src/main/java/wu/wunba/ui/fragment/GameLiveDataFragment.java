package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.model.NBAGameLiveDataInfo;
import wu.wunba.ui.adapter.NBAGameLiveDataAdapter;
import wu.wunba.ui.presenter.NBAGameLiveDataPresenter;
import wu.wunba.ui.view.NBAGameLiveDataView;

import static wu.wunba.ui.fragment.GameTextLiveFragment.MATCHPERIOD;

/**
 * 描述：比赛直播数据
 * 作者：Wu on 2017/3/2 10:19
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_nba_game_live_data)
public class GameLiveDataFragment extends BaseFragment implements NBAGameLiveDataView {


    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerView;
    @ViewInject(R.id.iv_no_data)
    ImageView ivNoData;
    @ViewInject(R.id.iv_network_error)
    ImageView ivNetworkError;


    private String mid;
    private String matchPeriod;
    public static final String BUNDLE_MID = "mid";
    private NBAGameLiveDataAdapter liveDataAdapter;
    private NBAGameLiveDataPresenter gameLiveDataPresenter;


    public static Fragment getInstance(String mid,String matchPeriod) {
        GameLiveDataFragment fragment = new GameLiveDataFragment();
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
        gameLiveDataPresenter = new NBAGameLiveDataPresenter(getActivity(),this);
        gameLiveDataPresenter.getNBAGameLiveDataInfo(mid,matchPeriod);
        liveDataAdapter = new NBAGameLiveDataAdapter(getActivity());
        recyclerView .setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(liveDataAdapter);
    }

    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
    }

    @Override
    public void showError(String msg) {
        if (msg.equals("0")) {
            ivNetworkError.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "网络连接异常", Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
        }else{
            ivNetworkError.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        ballLaodingDismiss();
    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(0);
    }

    @Override
    public void showGameLiveData(NBAGameLiveDataInfo dataInfo) {
        ivNetworkError.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        liveDataAdapter.setData(dataInfo);
    }
}
