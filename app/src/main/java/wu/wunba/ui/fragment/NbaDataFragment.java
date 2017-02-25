//package wu.wunba.ui.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import org.xutils.view.annotation.ContentView;
//import org.xutils.view.annotation.ViewInject;
//import org.xutils.x;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import wu.wunba.BaseFragment;
//import wu.wunba.R;
//import wu.wunba.ui.adapter.ViewPagerFragmentAdatper;
//
///**
// * 描述：
// * 作者：Wu on 2017/2/17 22:50
// * 邮箱：wuwende@live.cn
// */
//@ContentView(R.layout.tablayout)
//public class NbaDataFragment extends BaseFragment {
//
//    @ViewInject(R.id.tabs)
//    TabLayout tabLayout;
//    @ViewInject(R.id.viewpager)
//    ViewPager viewPager;
//
//    public static NbaDataFragment nbaDataFragment;
//
//    private List<Fragment> mFragment = new ArrayList<>();
//    private ViewPagerFragmentAdatper adatper;
//    private String titles[] = {"每日", "赛季"};
//
//    public static NbaDataFragment getInstance(){
//        if(nbaDataFragment == null){
//            nbaDataFragment = new NbaDataFragment();
//        }
//        return nbaDataFragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return x.view().inject(this,inflater,container);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mFragment.clear();
//        mFragment.add(DayDataFragment.getInstance());
//        mFragment.add(SeasonDataFragment.getInstance());
//        adatper = new ViewPagerFragmentAdatper(getChildFragmentManager(),mFragment,titles);
//        viewPager.setAdapter(adatper);
//        tabLayout.setTabsFromPagerAdapter(adatper);
//        tabLayout.setupWithViewPager(viewPager);
//    }
//}
