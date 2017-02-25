package wu.wunba.app;

import android.app.Application;

import org.xutils.x;

import wu.wunba.utils.AppUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/17 23:13
 * 邮箱：wuwende@live.cn
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        AppUtils.init(this);
    }

}
