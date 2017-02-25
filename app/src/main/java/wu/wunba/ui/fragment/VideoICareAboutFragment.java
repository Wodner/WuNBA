package wu.wunba.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import wu.wunba.BaseFragment;
import wu.wunba.R;

/**
 * 描述：关注视频
 * 作者：Wu on 2017/2/18 02:37
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.xrecyclerview)
public class VideoICareAboutFragment extends BaseFragment{

    public static VideoICareAboutFragment videoICareAboutFragment;

    public static VideoICareAboutFragment getInstance(){
        if(videoICareAboutFragment == null){
            videoICareAboutFragment = new VideoICareAboutFragment();
        }
        return videoICareAboutFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
