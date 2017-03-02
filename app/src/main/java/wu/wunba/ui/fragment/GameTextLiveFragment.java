package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.orhanobut.logger.Logger;

import org.xutils.view.annotation.ContentView;
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
public class GameTextLiveFragment extends BaseFragment implements NBAGameTextLiveView{

    @ViewInject(R.id.recyclerview)
    LRecyclerView recyclerview;

    public static final String BUNDLE_MID = "mid";
    public static final String MATCHPERIOD = "matchPeriod";

    private String mid;
    private String matchPeriod;
    private int TEXTS_PAGER_NUM = 1;
    private NBAGameTextInfoAdapter gameTextInfoAdapter;
    private NBAGameTextLivePresenter gameTextLivePresenter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private UpdataGameDataThread updataGameDataThread;
    private List<NBAGameTextLiveItem> infoList = new ArrayList<>();

    public static Fragment getInstance(String mid,String matchPeriod) {
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
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mid = getArguments().getString(BUNDLE_MID);
        matchPeriod = getArguments().getString(MATCHPERIOD);
        gameTextLivePresenter = new NBAGameTextLivePresenter(getActivity(),this);
        gameTextLivePresenter.getNBAGameTextLiveIndex(TEXTS_PAGER_NUM,mid);
        initXRecyclerView();
        updataGameDataThread = new UpdataGameDataThread();
        updataGameDataThread.start();
    }


    private void initXRecyclerView() {
        gameTextInfoAdapter= new NBAGameTextInfoAdapter(getActivity());
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
                gameTextLivePresenter.getNBAGameTextLiveIndex(TEXTS_PAGER_NUM,mid);
            }
        });
        recyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                TEXTS_PAGER_NUM++;
                gameTextLivePresenter.getNBAGameTextLiveIndex(TEXTS_PAGER_NUM,mid);
            }
        });
    }

    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(0);
    }

    @Override
    public void showError(String msg) {
        if(msg.equals("0")){
            Toast.makeText(getActivity(),"网络连接异常",Toast.LENGTH_SHORT).show();
        }
        recyclerview.refreshComplete(10);
        ballLaodingDismiss();
    }

    @Override
    public void hideLoading(boolean isFirst) {
        recyclerview.refreshComplete(10);
        ballLaodingDismiss();
    }


    @Override
    public void showGameTextLive(List<NBAGameTextLiveItem> nbaGameTextLiveItemList) {
        recyclerview.refreshComplete(10);
        infoList.addAll(nbaGameTextLiveItemList);
        gameTextInfoAdapter.setData(infoList);
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if(updataGameDataThread!=null && updataGameDataThread.isAlive()){
            updataGameDataThread.interrupt();
        }
    }

    /**
     * 每隔十秒更新数据
     */
    private class UpdataGameDataThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try{
                    Thread.sleep(10000);
                    Logger.d("定时器 ： " + matchPeriod);
                    if(matchPeriod.equals("1")){
                        gameTextLivePresenter.getGameTextLiveIndexTimer(mid);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
