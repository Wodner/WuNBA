package wu.wunba.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;

import wu.wunba.ui.widget.BasketballLoading;

public class BaseFragment extends Fragment {
	protected LayoutInflater inflater;
	private View contentView;
	private Context context;
	private ViewGroup container;
	public Activity mActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity().getApplicationContext();
		mActivity = getActivity();
	}

	@Override
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		onCreateView(savedInstanceState);
		if (contentView == null)
			return super.onCreateView(inflater, container, savedInstanceState);
		return contentView;
	}

	protected void onCreateView(Bundle savedInstanceState) {

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		contentView = null;
		container = null;
		inflater = null;
	}

	public Context getApplicationContext() {
		return context;
	}

	public void setContentView(int layoutResID) {
		setContentView((ViewGroup) inflater.inflate(layoutResID, container, false));
	}

	public void setContentView(View view) {
		contentView = view;
	}

	public View getContentView() {
		return contentView;
	}

	public View findViewById(int id) {
		if (contentView != null)
			return contentView.findViewById(id);
		return null;
	}

	// http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}


	protected BasketballLoading basketballLoading;
    /**
     * @param type 篮球加载框
     */
    protected void ballLaodingShow(int type) {
        basketballLoading = BasketballLoading.createDialog(getActivity(),type);
        basketballLoading.show();

    }

    /**
     * 隐藏加载框
     */
    protected void ballLaodingDismiss() {
        if(basketballLoading!=null){
            basketballLoading.dismiss();
            basketballLoading = null;
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }

}
