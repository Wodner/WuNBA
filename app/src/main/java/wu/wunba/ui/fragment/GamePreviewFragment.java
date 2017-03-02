package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import wu.wunba.BaseFragment;

/**
 * 描述：
 * 作者：Wu on 2017/3/2 10:18
 * 邮箱：wuwende@live.cn
 */

public class GamePreviewFragment extends BaseFragment {


    public static final String BUNDLE_MID = "mid";

    public static Fragment getInstance(String mid) {
        GamePreviewFragment fragment = new GamePreviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_MID, mid);
        fragment.setArguments(bundle);
        return fragment;
    }






}
