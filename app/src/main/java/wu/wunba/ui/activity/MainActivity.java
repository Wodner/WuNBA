package wu.wunba.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wu.wunba.BaseActivity;
import wu.wunba.R;
import wu.wunba.ui.adapter.NaviListMenuAdapter;
import wu.wunba.ui.fragment.NBADataSortFragment;
import wu.wunba.ui.fragment.NBAGameLiveFragment;
import wu.wunba.ui.fragment.NbaMoreFragment;
import wu.wunba.ui.fragment.NbaNewsFragment;
import wu.wunba.ui.fragment.NbaTeamRankingFragment;

public class MainActivity extends BaseActivity {


    @Bind(R.id.common_toolbar)
    Toolbar toolbar;
    @Bind(R.id.home_drawer)
    DrawerLayout drawer;

    @Bind(R.id.home_navigation_list)
    ListView listNavigation;


    private Fragment openEdFragment = null;
    private List<Fragment> fragmentList = new ArrayList<>();
    private NaviListMenuAdapter naviListMenuAdapter;
    private final String [] naviName={"NBA资讯","赛事直播","球队战绩","数据排行","更多"};
    private final int [] naviIcon={R.mipmap.toolbar_icon_latest_nor,R.mipmap.toolbar_icon_video_nor,
            R.mipmap.toolbar_icon_games_nor,R.mipmap.toolbar_icon_data_nor,R.mipmap.toolbar_icon_more_nor};


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        toolbar.setTitle(naviName[0]);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        initNaviMenu();
        initFragment();
        switchFragment(0);
    }

    /**
     * 初始化侧滑菜单
     */
    private void initNaviMenu(){
        naviListMenuAdapter = new NaviListMenuAdapter(mContext,naviName,naviIcon);
        listNavigation.setAdapter(naviListMenuAdapter);
        listNavigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawer.closeDrawer(GravityCompat.START,true);
                switchFragment(i);
                toolbar.setTitle(naviName[i]);
            }
        });
    }


    /**
     * 初始化fragment
     */
    private void initFragment(){
        fragmentList.clear();
        fragmentList.add( NbaNewsFragment.getInstance());
        fragmentList.add( NBAGameLiveFragment.getInstance());
        fragmentList.add( NbaTeamRankingFragment.getInstance());
        fragmentList.add( NBADataSortFragment.getInstance());
        fragmentList.add( NbaMoreFragment.getInstance());
    }

    /**
     * @param index 切换页面
     */
     private void switchFragment(int index){
         if(openEdFragment==null){
             getSupportFragmentManager().beginTransaction().add(R.id.main_content,fragmentList.get(index)).commit();
             openEdFragment = fragmentList.get(index);
         }else{
             if(!openEdFragment.equals(fragmentList.get(index))){
                 if(fragmentList.get(index).isAdded()){
                     getSupportFragmentManager().beginTransaction().hide(openEdFragment).show(fragmentList.get(index)).commit();
                     openEdFragment = fragmentList.get(index);
                 }else{
                     getSupportFragmentManager().beginTransaction().hide(openEdFragment).add(R.id.main_content,fragmentList.get(index)).commit();
                     openEdFragment = fragmentList.get(index);
                 }
             }else {
                 Logger.d("--- 别点了 还是当前页面" );
             }
         }
     }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
