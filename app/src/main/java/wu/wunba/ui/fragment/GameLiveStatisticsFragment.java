package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import wu.wunba.BaseFragment;

/**
 * 描述：直播数据统计
 * 作者：Wu on 2017/3/2 10:22
 * 邮箱：wuwende@live.cn
 */

public class GameLiveStatisticsFragment extends BaseFragment {


    public static final String BUNDLE_MID = "mid";

    public static Fragment getInstance(String mid) {
        GameLiveStatisticsFragment fragment = new GameLiveStatisticsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_MID, mid);
        fragment.setArguments(bundle);
        return fragment;
    }
}
