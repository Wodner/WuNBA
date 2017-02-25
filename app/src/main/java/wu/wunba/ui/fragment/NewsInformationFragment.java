package wu.wunba.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import wu.wunba.ui.NBANewsPresenter;
import wu.wunba.ui.activity.NBANewsDetailActivity;
import wu.wunba.ui.activity.NBAPicturesDetailActivity;
import wu.wunba.ui.adapter.NBANewsInformAdapter;
import wu.wunba.ui.view.NBANewsView;
import wu.wunba.utils.Xutils3ImageUtils;

/**
 * 描述：NBA资讯
 * 作者：Wu on 2017/2/18 02:41
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.xrecyclerview)
public class NewsInformationFragment extends BaseFragment implements NBANewsView,NBANewsInformAdapter.OnRecyclerViewItemClickListener{

    @ViewInject(R.id.recyclerview)
    LRecyclerView mRecyclerView;

    private Activity mContext;
    private int NEWS_PAGER_NUM = 1;
    private ImageView ivNewsCover;//banner 封面
    private TextView tvNewsTitle;//banner 标题
    private String newsArticleId;//banner 新闻id
    private String newsType;//banner 新闻类型 0 图片 1 新闻
    private boolean isPullToRefresh = true;
    private NBANewsPresenter newsInformPresenter;
    private NBANewsInformAdapter nbaNewsInformAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private List<NBANews> nbaNewsList = new ArrayList<>();
    public static NewsInformationFragment newsInformationFragment;

    public static NewsInformationFragment getInstance(){
        if(newsInformationFragment == null){
            newsInformationFragment = new NewsInformationFragment();
        }
        return newsInformationFragment;
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
        newsInformPresenter = new NBANewsPresenter(this);
        newsInformPresenter.initialized(NBANewsPresenter.TYPE_NEWS_INFORM);
    }

    private void initXRecyclerView() {
        nbaNewsInformAdapter = new NBANewsInformAdapter(mContext);
        nbaNewsInformAdapter.setOnRecyclerViewItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(nbaNewsInformAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        View header = LayoutInflater.from(mContext).inflate(R.layout.item_news_header,(ViewGroup) getActivity().getWindow().findViewById(android.R.id.content), false);
        ivNewsCover = (ImageView)header.findViewById(R.id.iv_news_header);
        tvNewsTitle = (TextView)header.findViewById(R.id.tv_news_title);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newsType.equals("0")){//新闻
                    Bundle bundle = new Bundle();
                    bundle.putString(Config.ARTICLE_ID,newsArticleId);
                    bundle.putString(Config.UPPER_ACTIVITY_NAME,"NBA资讯");
                    NBANewsDetailActivity.startAction(mContext,bundle);
                }else if(newsType.equals("1")){//图集
                    Bundle bundle = new Bundle();
                    bundle.putString(Config.ARTICLE_ID,newsArticleId);
                    bundle.putString(Config.UPPER_ACTIVITY_NAME,"NBA资讯");
                    NBAPicturesDetailActivity.startAction(mContext,bundle);
                }
            }
        });
        mLRecyclerViewAdapter.addHeaderView(header);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                isPullToRefresh = true;
                NEWS_PAGER_NUM = 1;
                nbaNewsList.clear();
                newsInformPresenter.setArticleIds(NEWS_PAGER_NUM,NBANewsPresenter.TYPE_NEWS_INFORM);
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isPullToRefresh = false;
                NEWS_PAGER_NUM++;
                newsInformPresenter.setArticleIds(NEWS_PAGER_NUM,NBANewsPresenter.TYPE_NEWS_INFORM);
            }
        });
    }


    @Override
    public void onItemClick(View v, NBANews nbaNews, int postion) {
        if(nbaNews.getAtype().equals("0")){//新闻
            Bundle bundle = new Bundle();
            bundle.putString(Config.ARTICLE_ID,nbaNews.getNewsId());
            bundle.putString(Config.UPPER_ACTIVITY_NAME,"NBA资讯");
            NBANewsDetailActivity.startAction(mContext,bundle);
        }else if(nbaNews.getAtype().equals("1")){//图集
            Bundle bundle = new Bundle();
            bundle.putString(Config.ARTICLE_ID,nbaNews.getNewsId());
            bundle.putString(Config.UPPER_ACTIVITY_NAME,"NBA资讯");
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
        ballLaodingDismiss();
    }


    @Override
    public void showNews(List<NBANews> newsList) {
        if(isPullToRefresh){
            showNewsBanner(newsList.get(0));
            newsList.remove(0);
        }
        mRecyclerView.refreshComplete(10);
        nbaNewsList.addAll(newsList);
        nbaNewsInformAdapter.setData(nbaNewsList);
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }


    /**
     * @param nbaNews
     */
    private void showNewsBanner(NBANews nbaNews){
        newsArticleId = nbaNews.getNewsId();
        tvNewsTitle.setText(nbaNews.getTitle());
        newsType = nbaNews.getAtype();
        Xutils3ImageUtils.display(ivNewsCover,nbaNews.getImgurl(),
                Xutils3ImageUtils.getImageOptionsDefault(R.mipmap.latest_pic_head_loading_720px, R.mipmap.latest_pic_head_loading_720px));

    }
}
