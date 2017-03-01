package wu.wunba.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.app.Config;
import wu.wunba.model.NBANews;
import wu.wunba.ui.presenter.NBANewsPresenter;
import wu.wunba.ui.activity.NBANewsDetailActivity;
import wu.wunba.ui.activity.NBAPicturesDetailActivity;
import wu.wunba.ui.adapter.NBANewsAdapter;
import wu.wunba.ui.view.NBANewsView;


/**
 * 描述：
 * 作者：Wu on 2017/2/18 02:41
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.xrecyclerview)
public class NewsHeadLinesFragment extends BaseFragment implements NBANewsView,NBANewsAdapter.OnRecyclerViewItemClickListener{


    @ViewInject(R.id.recyclerview)
    LRecyclerView mRecyclerView;

    public static NewsHeadLinesFragment newsHeadLinesFragment;
    private Activity mContext;
    private int NEWS_PAGER_NUM = 1;
    private NBANewsPresenter nbaNewsHeadlinePresenter;
    private NBANewsAdapter nbaNewsAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<NBANews> nbaNewsList = new ArrayList<>();

    public static NewsHeadLinesFragment getInstance(){
        if(newsHeadLinesFragment == null){
            newsHeadLinesFragment = new NewsHeadLinesFragment();
        }
        return newsHeadLinesFragment;
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
        nbaNewsHeadlinePresenter = new NBANewsPresenter(this);
        nbaNewsHeadlinePresenter.initialized(NBANewsPresenter.TYPE_NEWS_HEADLINE);
    }

    private void initXRecyclerView() {
        nbaNewsAdapter = new NBANewsAdapter(mContext);
        nbaNewsAdapter.setOnRecyclerViewItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(nbaNewsAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                NEWS_PAGER_NUM = 1;
                nbaNewsList.clear();
                nbaNewsHeadlinePresenter.setArticleIds(NEWS_PAGER_NUM,NBANewsPresenter.TYPE_NEWS_HEADLINE);
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                NEWS_PAGER_NUM++;
                nbaNewsHeadlinePresenter.setArticleIds(NEWS_PAGER_NUM,NBANewsPresenter.TYPE_NEWS_HEADLINE);
            }
        });
    }


    @Override
    public void onItemClick(View v, NBANews nbaNews, int postion) {
        if(nbaNews.getAtype().equals("0")){//新闻
            Bundle bundle = new Bundle();
            bundle.putString(Config.ARTICLE_ID,nbaNews.getNewsId());
            bundle.putString(Config.UPPER_ACTIVITY_NAME,"NBA头条");
            NBANewsDetailActivity.startAction(mContext,bundle);
        }else if(nbaNews.getAtype().equals("1")){//图集
            Bundle bundle = new Bundle();
            bundle.putString(Config.ARTICLE_ID,nbaNews.getNewsId());
            bundle.putString(Config.UPPER_ACTIVITY_NAME,"NBA头条");
            NBAPicturesDetailActivity.startAction(mContext,bundle);
        }
    }

    @Override
    public void showLoading(boolean isFirst) {
        if(isFirst){
            ballLaodingShow(0);
        }
    }

    @Override
    public void showError(String msg) {
        mRecyclerView.refreshComplete(10);
        if(msg.equals("-1")){
            //已经到底了
        }
    }

    @Override
    public void hideLoading(boolean isFirst) {
        if(isFirst){
            ballLaodingDismiss();
        }
    }

    @Override
    public void showNews(List<NBANews> newsList) {
        mRecyclerView.refreshComplete(10);
        nbaNewsList.addAll(newsList);
        nbaNewsAdapter.setData(nbaNewsList);
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }
}
