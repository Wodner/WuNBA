package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.model.NBAGamePreviewInfo;
import wu.wunba.ui.presenter.GamePreviewPresenter;
import wu.wunba.ui.view.GamePreviewView;

import static wu.wunba.ui.fragment.GameTextLiveFragment.MATCHPERIOD;

/**
 * 描述：比赛前瞻
 * 作者：Wu on 2017/3/2 10:18
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_nba_game_live_data)
public class GamePreviewFragment extends BaseFragment implements GamePreviewView{

    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerView;
    @ViewInject(R.id.iv_no_data)
    ImageView ivNoData;
    @ViewInject(R.id.iv_network_error)
    ImageView ivNetworkError;


    private String mid;
    private String matchPeriod;
    public static final String BUNDLE_MID = "mid";
    private GamePreviewPresenter previewPresenter;




    public static Fragment getInstance(String mid, String matchPeriod) {
        GamePreviewFragment fragment = new GamePreviewFragment();
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
        previewPresenter = new GamePreviewPresenter(getActivity(),this);
        previewPresenter.getGamePreviewInfo(mid);
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
    public void showGamePreview(NBAGamePreviewInfo gamePreviewInfo) {

    }
}
