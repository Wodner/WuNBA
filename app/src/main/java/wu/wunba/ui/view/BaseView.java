package wu.wunba.ui.view;

/**
 * 描述：
 * 作者：Wu on 2017/2/21 18:22
 * 邮箱：wuwende@live.cn
 */

public interface BaseView {

    /**
     * show loading message
     *
     * @param isFirst
     */
    void showLoading(boolean isFirst);

    /**
     * hide loading
     */
    void hideLoading(boolean isFirst);

    /**
     * show error message   :  0:代表没网络，-1：代表加载到底了
     */
    void showError(String msg);
}
