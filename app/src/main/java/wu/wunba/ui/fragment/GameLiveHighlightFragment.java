package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.wunba.BaseFragment;
import wu.wunba.R;

/**
 * 描述：mid 场比赛集锦
 * 作者：Wu on 2017/3/2 10:20
 * 邮箱：wuwende@live.cn
 */

public class GameLiveHighlightFragment extends BaseFragment {


    @Bind(R.id.recyclerview)
    LRecyclerView recyclerview;


    public static final String BUNDLE_MID = "mid";

    public static Fragment getInstance(String mid) {
        GameLiveHighlightFragment fragment = new GameLiveHighlightFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_MID, mid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.xrecyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
