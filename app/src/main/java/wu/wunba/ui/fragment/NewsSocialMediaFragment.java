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
 * 描述：
 * 作者：Wu on 2017/2/18 02:41
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_news_social_media)
public class NewsSocialMediaFragment extends BaseFragment{
    public static NewsSocialMediaFragment newsSocialMediaFragment;

    public static NewsSocialMediaFragment getInstance(){
        if(newsSocialMediaFragment == null){
            newsSocialMediaFragment = new NewsSocialMediaFragment();
        }
        return newsSocialMediaFragment;
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
