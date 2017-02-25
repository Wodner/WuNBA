package wu.wunba.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.model.NBAVideo;
import wu.wunba.ui.adapter.NBAVideoAdapter;
import wu.wunba.ui.view.NBAVideoView;
import wu.wunba.ui.NBAVideoPresenter;

/**
 * 描述：集锦
 * 作者：Wu on 2017/2/18 02:36
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.xrecyclerview)
public class VideoHighlightFragment extends BaseFragment implements NBAVideoView{


    @ViewInject(R.id.recyclerview)
    LRecyclerView mRecyclerView;

    public static final String BUNDLE_MID = "mid";

    private Context mContext;
    private int VIDEO_PAGER_NUM = 1;/*多少页数据*/
    private  Callback.Cancelable cancelable;
    private NBAVideoAdapter nbaVideoAdapter;
    private NBAVideoPresenter nbaVideoPresenter;
    private List<NBAVideo> modeNBAVideoList = new ArrayList<>();
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    public static Fragment getInstance(String mid) {
        VideoHighlightFragment fragment = new VideoHighlightFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_MID, mid);
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
        mContext = getActivity();
        initXRecyclerView();
        nbaVideoPresenter = new NBAVideoPresenter(this);
        nbaVideoPresenter.initialized(NBAVideoPresenter.TYPE_VIDEO_HIGHLIGHT);
    }

    private void initXRecyclerView() {
        nbaVideoAdapter = new NBAVideoAdapter(mContext);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(nbaVideoAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                VIDEO_PAGER_NUM = 1;
                modeNBAVideoList.clear();
                nbaVideoPresenter.setArticleIds(VIDEO_PAGER_NUM,NBAVideoPresenter.TYPE_VIDEO_HIGHLIGHT);
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                VIDEO_PAGER_NUM++;
                nbaVideoPresenter.setArticleIds(VIDEO_PAGER_NUM,NBAVideoPresenter.TYPE_VIDEO_HIGHLIGHT);
            }
        });
    }


    @Override
    public void showError(String msg) {
        mRecyclerView.refreshComplete(10);
        if(msg.equals("-1")){
            //已经到底了
        }
    }

    @Override
    public void showLoading(boolean isFirst) {
        if(isFirst){
            ballLaodingShow(0);
        }
    }

    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
    }

    @Override
    public void showMatchVideo(final List<NBAVideo> nbaVideolist) {
        mRecyclerView.refreshComplete(10);
        modeNBAVideoList.addAll(nbaVideolist);
        nbaVideoAdapter.setData(modeNBAVideoList);
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }



    @Override
    public void onStop() {
        super.onStop();
        JCVideoPlayer.releaseAllVideos();
    }



}
