package wu.wunba.http;

/**
 * 描述：
 * 作者：Wu on 2017/2/22 01:28
 * 邮箱：wuwende@live.cn
 */

public interface RequestCallBack<T> {

    void onSuccess(T t);

    void onFailure(String errorMsg);
}
