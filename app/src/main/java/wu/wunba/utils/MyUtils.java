package wu.wunba.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orhanobut.logger.Logger;

/**
 * 描述：
 * 作者：Wu on 2017/2/19 00:21
 * 邮箱：wuwende@live.cn
 */

public class MyUtils {

    public static String [] week = {"周日","周一","周二","周三","周四","周五","周六"};

    public static String getWeek(int i){
        Logger.d("------ " + i);
       return week[i];
    }


    /**
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
              ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                      .getSystemService(Context.CONNECTIVITY_SERVICE);
               NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
               if (mNetworkInfo != null) {
                   return mNetworkInfo.isAvailable();
               }
        }
        return false;
    }

    /**
     * @param context
     * @return
     */
    public boolean isWifiConnected(Context context) {
       if (context != null) {
           ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                   .getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                   .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
           if (mWiFiNetworkInfo != null) {
               return mWiFiNetworkInfo.isAvailable();
           }
       }
        return false;
    }

    /**
     * @param context
     * @return
     */
    public boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
