package wu.wunba.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import wu.wunba.BaseFragment;
import wu.wunba.R;
import wu.wunba.event.GameDateEvent;
import wu.wunba.event.GameLiveDataEvent;
import wu.wunba.http.NBAApi;
import wu.wunba.model.NBAMatch;
import wu.wunba.ui.activity.GameDateSelectActivity;
import wu.wunba.ui.adapter.NBAGameAdapter;
import wu.wunba.ui.widget.SpaceItemDecoration;
import wu.wunba.utils.DimenUtils;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/18 01:54
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_schedule_for_all)
public class ScheduleFragment extends BaseFragment implements NBAGameAdapter.OnRecyclerViewItemClickListener,NBAGameAdapter.OnScroceBoxPreviewAndHighlightTextLiveOnClickListener{

    @ViewInject(R.id.ll_date_picker)
    LinearLayout llDatePicker;
    @ViewInject(R.id.iv_day_add)
    ImageView ivDayAdd;
    @ViewInject(R.id.iv_day_reduce)
    ImageView ivDayReduce;
    @ViewInject(R.id.tv_match_date)
    TextView tvMatchDate;
    @ViewInject(R.id.recyclerview)
    RecyclerView recyclerView;
    @ViewInject(R.id.iv_no_data)
    ImageView ivNoData;
    @ViewInject(R.id.iv_network_error)
    ImageView iv_network_error;

    private UpdataGameDataThread updataGameDataThread;
    private boolean isGameLive = false;
    public static ScheduleFragment scheduleFragment;
    private  Callback.Cancelable cancelable;
    private SimpleDateFormat format;
    private Calendar cal;
    private Gson gson;
    private Activity mContext;
    private boolean isSelectDate = false;
    private NBAGameAdapter nbaGameAdapter;
    private String currentDate;
    private String noCurrentDate;


    public static ScheduleFragment getInstance(){
        if(scheduleFragment == null){
            scheduleFragment = new ScheduleFragment();
        }
        return scheduleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mContext = getActivity();
        gson = new Gson();
        updataGameDataThread = new UpdataGameDataThread();
        EventBus.getDefault().register(this);
        cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        format = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateStringSub(format.format(cal.getTime()));
        String week = MyUtils.getWeek(cal.get(Calendar.DAY_OF_WEEK) - 1);
        tvMatchDate.setText( week + " " + date);
        nbaGameAdapter = new NBAGameAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SpaceItemDecoration(DimenUtils.dpToPxInt(5)));

        recyclerView.setAdapter(nbaGameAdapter);
        nbaGameAdapter.setOnItemClickListener(this);
        nbaGameAdapter.setOnScroceBoxPreviewAndHighlightTextLiveClickListener(this);
        currentDate = format.format(cal.getTime());
        noCurrentDate = format.format(cal.getTime());
        if(MyUtils.isNetworkConnected(mContext)){
            iv_network_error.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            ballLaodingShow(0);
            getNBAMatchTimeMsg(NBAApi.getNBAGameTime(format.format(cal.getTime())));
        }else{
            recyclerView.setVisibility(View.GONE);
            iv_network_error.setVisibility(View.VISIBLE);
        }
        updataGameDataThread.start();
    }

    @Event(type = View.OnClickListener.class,value = R.id.iv_network_error)
    private void requestAggin(View v){
        ballLaodingShow(0);
        getNBAMatchTimeMsg(NBAApi.getNBAGameTime(format.format(cal.getTime())));
    }

    @Event(type = View.OnClickListener.class,value = R.id.iv_day_add)
    private void dayAddOnClick(View v){
        if(!MyUtils.isNetworkConnected(mContext)){
            Toast.makeText(mContext,"网络连接错误，请检查网络。",Toast.LENGTH_SHORT).show();
            return;
        }
        ballLaodingShow(0);
        cal.add(Calendar.DAY_OF_MONTH, +1);//取当前日期的后一天.
        String date = dateStringSub(format.format(cal.getTime()));
        String week = MyUtils.getWeek(cal.get(Calendar.DAY_OF_WEEK) - 1);
        tvMatchDate.setText( week + " " + date);
        noCurrentDate = format.format(cal.getTime());
        getNBAMatchTimeMsg(NBAApi.getNBAGameTime(format.format(cal.getTime())));
        tvMatchDate.setText( MyUtils.getWeek(cal.get(Calendar.DAY_OF_WEEK) - 1)+ " " + dateStringSub(format.format(cal.getTime())));
    }

    @Event(type = View.OnClickListener.class,value = R.id.iv_day_reduce)
    private void dayReduceOnClick(View v){
        if(!MyUtils.isNetworkConnected(mContext)){
            Toast.makeText(mContext,"网络连接错误，请检查网络。",Toast.LENGTH_SHORT).show();
            return;
        }
        cal.add(Calendar.DAY_OF_MONTH, -1);//取当前日期的后一天.
        String date = dateStringSub(format.format(cal.getTime()));
        String week = MyUtils.getWeek(cal.get(Calendar.DAY_OF_WEEK) - 1);
        tvMatchDate.setText( week + " " + date);
        ballLaodingShow(0);
        noCurrentDate = format.format(cal.getTime());
        getNBAMatchTimeMsg(NBAApi.getNBAGameTime(format.format(cal.getTime())));
        tvMatchDate.setText( MyUtils.getWeek(cal.get(Calendar.DAY_OF_WEEK) - 1)+ " " + dateStringSub(format.format(cal.getTime())));
    }

    @Event(type = View.OnClickListener.class,value = R.id.ll_date_picker)
    private void dayPickerOnClick(View v){
        if(!MyUtils.isNetworkConnected(mContext)){
            Toast.makeText(mContext,"网络连接错误，请检查网络。",Toast.LENGTH_SHORT).show();
            return;
        }
        isSelectDate = true;
        GameDateSelectActivity.startAction(getActivity(),Activity.RESULT_FIRST_USER);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getNBAGameSelect(GameDateEvent event){
        if(!MyUtils.isNetworkConnected(mContext)){
            Toast.makeText(mContext,"网络连接错误，请检查网络。",Toast.LENGTH_SHORT).show();
            return;
        }
        if(isSelectDate){
            isSelectDate= false;
            cal. setTime(new Date(event.getDate()));
            String date = dateStringSub(event.getDate());
            String week = MyUtils.getWeek(cal.get(Calendar.DAY_OF_WEEK) - 1);
            tvMatchDate.setText( week + " " + date);
            ballLaodingShow(0);
            noCurrentDate = event.getDate();
            getNBAMatchTimeMsg(NBAApi.getNBAGameTime(event.getDate()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updataNBAGameTimeData(GameLiveDataEvent event) {
        if(currentDate.equals(format.format(cal.getTime()))){//只更新当天的NBA数据
            parseNBAGameTimeData(event.getResult());
        }

    }


    /**
     * @param url
     */
    private void getNBAMatchTimeMsg(String url){
        RequestParams requestParams = new RequestParams(url);
        cancelable = x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.d("返回信息 ：" + result);
                parseNBAGameTimeData(result);
                ballLaodingDismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.e("当前 ： " + ex.toString());
                ballLaodingDismiss();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.e("当前 ： onCancelled");
                ballLaodingDismiss();
            }

            @Override
            public void onFinished() {
                Logger.e("当前 ： onFinished");
            }
        });
    }

    private void parseNBAGameTimeData(String result) {
        iv_network_error.setVisibility(View.GONE);
        NBAMatch gameMatch = gson.fromJson(result,NBAMatch.class);
        if(gameMatch.getData().getMatches().size()>0){
            Logger.e(" ====== " +gameMatch.getData().getMatches().get(0).getMatchInfo().getMatchPeriod());//正在直播就实时刷新数据
            if (gameMatch.getData().getMatches().get(0).getMatchInfo().getMatchPeriod().equals("1")
                    || gameMatch.getData().getMatches().get(gameMatch.getData().getMatches().size()-1).getMatchInfo().getMatchPeriod().equals("1")){
                isGameLive = true;
            }else {
                isGameLive = false;
            }
            recyclerView.setVisibility(View.VISIBLE);
            ivNoData.setVisibility(View.GONE);
            nbaGameAdapter.setData(gameMatch.getData().getMatches());
        }else{
            ivNoData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onItemClick(View v, NBAMatch.DataBean.MatchesBean matchesBean, int postion) {
        Toast.makeText(mContext,"postion" ,Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClick(View v, NBAMatch.DataBean.MatchesBean matchesBean, int position, int type) {

        switch (type){
            case NBAGameAdapter.TYPE_HIGHTLIGHT_TEXT_LIVE:

                break;
            case NBAGameAdapter.TYPE_SCROCE_BOX_PREVIEW:
                break;
        }

        Toast.makeText(mContext," "+ type ,Toast.LENGTH_SHORT).show();
    }

    /**
     * @param date
     * @return
     */
    private String  dateStringSub(String date){
        return date.substring(2,date.length());
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 每隔十秒更新数据
     */
    private class UpdataGameDataThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try{
                    Thread.sleep(10000);
                    if(isGameLive){
                        Logger.e(getCurrentDate() + " ====== " + noCurrentDate);
                        if(getCurrentDate().equals(noCurrentDate)){//只更新当天的NBA数据
                            getNBAMatchTimeMsg(NBAApi.getNBAGameTime(getCurrentDate()));
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * @return
     */
    private String getCurrentDate() {
        Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        return format.format(cal.getTime());
    }






}
