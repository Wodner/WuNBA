package wu.wunba;


import android.support.v4.app.Fragment;

import wu.wunba.ui.widget.BasketballLoading;

/**
 * 描述：
 * 作者：Wu on 2017/2/17 22:55
 * 邮箱：wuwende@live.cn
 */

public abstract class BaseFragment extends Fragment {

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
}
