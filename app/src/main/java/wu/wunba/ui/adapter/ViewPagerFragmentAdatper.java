package wu.wunba.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 描述：
 * 作者：Wu on 2017/2/18 02:04
 * 邮箱：wuwende@live.cn
 */

public class ViewPagerFragmentAdatper extends FragmentPagerAdapter {

    private String [] title;
    private List<Fragment> mFragment;

    public ViewPagerFragmentAdatper(FragmentManager fm, List<Fragment> fragments, String []titles) {
        super(fm);
        this.title = titles;
        this.mFragment = fragments;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

