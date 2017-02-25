//package wu.wunba.service;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//
//import com.orhanobut.logger.Logger;
//
//import org.greenrobot.eventbus.EventBus;
//import org.xutils.common.Callback;
//import org.xutils.http.RequestParams;
//import org.xutils.x;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//import wu.wunba.app.MyApplication;
//import wu.wunba.event.GameLiveDataEvent;
//import wu.wunba.http.NBAApi;
//
///**
// * 描述：获取当天的一些NBA 数据
// * 作者：Wu on 2017/2/20 10:24
// * 邮箱：wuwende@live.cn
// */
//
//public class NBAGetDataService extends Service{
//
//
//    private final int UPDATA_GAME_DATA = 10000;
//    private Handler mHandler = new Handler();
//    private  Callback.Cancelable cancelable;
//    private MyApplication myApplication;
//    private SimpleDateFormat format;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        format = new SimpleDateFormat("yyyy/MM/dd");
//        myApplication = (MyApplication) getApplication();
//        mHandler.postDelayed(mTasks,UPDATA_GAME_DATA);
//    }
//
//
//    /**
//     * @return
//     */
//    private String getCurrentDate() {
//        Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
//        return format.format(cal.getTime());
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return START_STICKY;
//    }
//
//
//    private Runnable mTasks = new Runnable() {
//        @Override
//        public void run() {
//            Logger.e(getCurrentDate() + " ====== " + myApplication.gameDate);
//        if(getCurrentDate().equals(myApplication.gameDate)){//只更新当天的NBA数据
//            getNBAMatchTimeMsg(NBAApi.getNBAGameTime(getCurrentDate()));
//        }
//        mHandler.postDelayed(mTasks,UPDATA_GAME_DATA);
//        }
//    };
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mHandler.removeCallbacks(mTasks);
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//
//
//
//    /**
//     * @param url
//     */
//    private void getNBAMatchTimeMsg(String url){
//        RequestParams requestParams = new RequestParams(url);
//        cancelable = x.http().get(requestParams, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Logger.d("返回信息 ：" + result);
//                EventBus.getDefault().post(new GameLiveDataEvent(result));
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Logger.e("当前 ： " + ex.toString());
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                Logger.e("当前 ： onCancelled");
//            }
//
//            @Override
//            public void onFinished() {
//                Logger.e("当前 ： onFinished");
//            }
//        });
//    }
//}
