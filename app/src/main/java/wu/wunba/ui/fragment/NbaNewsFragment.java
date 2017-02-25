package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.ui.adapter.ViewPagerFragmentAdatper;


/**
 * 描述：
 * 作者：Wu on 2017/2/17 22:53
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.tablayout)
public class NbaNewsFragment extends BaseFragment {

    @ViewInject(R.id.tabs)
    TabLayout tabLayout;
    @ViewInject(R.id.viewpager)
    ViewPager viewPager;

    public static NbaNewsFragment nbaNewsFragment;
//    public static NbaGameFragment nbaGameFragment;
    private List<Fragment> mFragment = new ArrayList<>();
    private ViewPagerFragmentAdatper adatper;

    private String titles[] = {"NBA头条", "NBA资讯","NBA图集","视频集锦","最佳进球","NBA花絮","社交媒体","关注新闻","关注视频"};

    public static NbaNewsFragment getInstance(){
        if(nbaNewsFragment == null){
            nbaNewsFragment = new NbaNewsFragment();
        }
        return nbaNewsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragment.clear();
        mFragment.add(NewsHeadLinesFragment.getInstance());//NBA头条
        mFragment.add(NewsInformationFragment.getInstance());//nba资讯
        mFragment.add(NewsPicturesFragment.getInstance());//NBA图集
        mFragment.add(VideoHighlightFragment.getInstance(""));//视频集锦
        mFragment.add(VideoBestFragment.getInstance());//最佳进球
        mFragment.add(VideoTidbitsFragment.getInstance());//NBA花絮
        mFragment.add(NewsSocialMediaFragment.getInstance());//社交媒体
        mFragment.add(NewsICareFragment.getInstance());//关注新闻
        mFragment.add(VideoICareAboutFragment.getInstance());//关注视频
        adatper = new ViewPagerFragmentAdatper(getChildFragmentManager(),mFragment,titles);
        viewPager.setAdapter(adatper);
        tabLayout.setupWithViewPager(viewPager);
    }


}
