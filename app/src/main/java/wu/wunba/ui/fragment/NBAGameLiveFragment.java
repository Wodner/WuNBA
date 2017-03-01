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

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
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
import wu.wunba.model.NBAMatch;
import wu.wunba.ui.presenter.NBAGameLivePresenter;
import wu.wunba.ui.activity.GameDateSelectActivity;
import wu.wunba.ui.adapter.NBAGameAdapter;
import wu.wunba.ui.view.NBAGameLiveView;
import wu.wunba.ui.widget.BasketballLoading;
import wu.wunba.ui.widget.SpaceItemDecoration;
import wu.wunba.utils.DimenUtils;
import wu.wunba.utils.MyUtils;

/**
 * 描述：
 * 作者：Wu on 2017/2/27 10:13
 * 邮箱：wuwende@live.cn
 */
@ContentView(R.layout.fragment_schedule_for_all)
public class NBAGameLiveFragment extends BaseFragment implements NBAGameAdapter.OnRecyclerViewItemClickListener,NBAGameLiveView{

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


    private Calendar cal;
    private Activity mContext;
    private String currentDate;
    private String noCurrentDate;
    private SimpleDateFormat format;
    private boolean isGameLive = false;
    private boolean isSelectDate = false;
    private NBAGameAdapter nbaGameAdapter;
    private  Callback.Cancelable cancelable;
    private UpdataGameDataThread updataGameDataThread;
    private NBAGameLivePresenter gameLivePresenter;
    public static NBAGameLiveFragment nbaGameLiveFragment;
    public static NBAGameLiveFragment getInstance(){
        if(nbaGameLiveFragment == null){
            nbaGameLiveFragment = new NBAGameLiveFragment();
        }
        return nbaGameLiveFragment;
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
        initCalendar();
        initRecyclerView();
        EventBus.getDefault().register(this);
        updataGameDataThread = new UpdataGameDataThread();
        gameLivePresenter = new NBAGameLivePresenter(mContext,this);
        gameLivePresenter.initialized(0);
        updataGameDataThread.start();
    }

    private void initCalendar() {
        cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
        format = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateStringSub(format.format(cal.getTime()));
        String week = MyUtils.getWeek(cal.get(Calendar.DAY_OF_WEEK) - 1);
        tvMatchDate.setText( week + " " + date);
        currentDate = format.format(cal.getTime());
        noCurrentDate = format.format(cal.getTime());
    }

    private void initRecyclerView() {
        nbaGameAdapter = new NBAGameAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SpaceItemDecoration(DimenUtils.dpToPxInt(5)));
        recyclerView.setAdapter(nbaGameAdapter);
        nbaGameAdapter.setOnItemClickListener(this);
    }


    @Event(type = View.OnClickListener.class,value = R.id.iv_network_error)
    private void requestAggin(View v){
        ballLaodingShow(0);
        gameLivePresenter.getNBAGameLive(format.format(cal.getTime()));
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
        gameLivePresenter.getNBAGameLive(format.format(cal.getTime()));
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
        gameLivePresenter.getNBAGameLive(format.format(cal.getTime()));
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

            Logger.d(noCurrentDate+ " 选择日期 --- " + event.getDate() + "=== " + format.format(cal.getTime()));
            noCurrentDate = event.getDate();
            gameLivePresenter.getNBAGameLive(noCurrentDate);
        }
    }

    @Override
    public void noNBAGame(boolean noGame) {
        if(noGame){
            recyclerView.setVisibility(View.GONE);
            ivNoData.setVisibility(View.VISIBLE);
        }else {
            ivNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(View v, NBAMatch.DataBean.MatchesBean matchesBean, int postion) {

    }


    @Override
    public void showNBAGameIsLive(boolean isLive) {
        isGameLive = isLive;
    }

    @Override
    public void showError(String msg) {
        ballLaodingDismiss();
        if(msg.equals("-1")){
            iv_network_error.setVisibility(View.VISIBLE);
        }else {
            iv_network_error.setVisibility(View.GONE);
        }
    }


    @Override
    public void showLoading(boolean isFirst) {
        ballLaodingShow(BasketballLoading.TYPE_QUAN);
    }

    @Override
    public void hideLoading(boolean isFirst) {
        ballLaodingDismiss();
    }


    @Override
    public void showNBAGameLive(NBAMatch nbaMatch) {
        iv_network_error.setVisibility(View.GONE);
        nbaGameAdapter.setData(nbaMatch.getData().getMatches());
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
//                        Logger.e(getCurrentDate() + " ====== " + noCurrentDate);
                        if(getCurrentDate().equals(noCurrentDate)){//只更新当天的NBA数据
                            gameLivePresenter.getNBAGameLive(getCurrentDate());
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

    /**
     * @param date
     * @return
     */
    private String  dateStringSub(String date){
        return date.substring(2,date.length());
    }

}
