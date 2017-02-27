package wu.wunba.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orhanobut.logger.Logger;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import wu.wunba.R;

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


    public static void showShare(Context context,String titleUrl,String url,String title,String txtAbstract) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(txtAbstract);
        oks.setImageUrl(titleUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(txtAbstract);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);
        // 启动分享GUI
        oks.show(context);
    }
}
