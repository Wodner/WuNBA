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

//    {
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
//        PlatformConfig.setQQZone("1105935693", "V1o8xFLhV4yQK39w");
//    }

}
