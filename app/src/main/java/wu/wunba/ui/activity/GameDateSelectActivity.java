package wu.wunba.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import wu.wunba.BaseSwipeBackCompatActivity;
import wu.wunba.R;
import wu.wunba.event.GameDateEvent;

/**
 * 描述：
 * 作者：Wu on 2017/2/19 13:04
 * 邮箱：wuwende@live.cn
 */
public class GameDateSelectActivity extends BaseSwipeBackCompatActivity implements CalendarPickerController{

    @Bind(R.id.agenda_calendar_view)
    AgendaCalendarView agendaCalendarView;

    private List<CalendarEvent> eventList;
    public static final String DATE_SELECT = "date";

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nba_game_date_sel;
    }


    @Override
    protected void initViewsAndEvents() {
        eventList = new ArrayList<>();
        // minimum and maximum date of our calendar
        // 2 month behind, one year ahead, example: March 2015 <-> May 2015 <-> May 2016
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();
        minDate.add(Calendar.YEAR, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);
        mockList(eventList);
        agendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
    }




    @Override
    public void onEventSelected(CalendarEvent event) {
        Logger.d(" 事件选择 ====== " + event.getDayReference());
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
//        Date date =new Date(calendar.getTimeInMillis());
//        Logger.d(" 滑动 ====== " + sd.format(date));
    }

    @Override
    public void onDaySelected(DayItem dayItem) {
        dayItem.getValue();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        String date=     sd.format(dayItem.getDate());
        Logger.d(" 单选 ====== " + date);
        EventBus.getDefault().post(new GameDateEvent(date));
        this.finish();
    }

    /**
     * @param context
     */
    public static void startAction(Activity context,int requestCode) {
        Intent intent = new Intent(context, GameDateSelectActivity.class);
        context.startActivityForResult(intent,requestCode);
    }


    private void mockList(List<CalendarEvent> eventList) {
        Calendar startTime1 = Calendar.getInstance();
        Calendar endTime1 = Calendar.getInstance();
        endTime1.add(Calendar.MONTH, 1);
        BaseCalendarEvent event1 = new BaseCalendarEvent("Thibault travels in Iceland", "A wonderful journey!", "Iceland",
                ContextCompat.getColor(this, R.color.red), startTime1, endTime1, true);
        eventList.add(event1);

        Calendar startTime2 = Calendar.getInstance();
        startTime2.add(Calendar.DAY_OF_YEAR, 1);
        Calendar endTime2 = Calendar.getInstance();
        endTime2.add(Calendar.DAY_OF_YEAR, 3);
        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
                ContextCompat.getColor(this, R.color.yellow), startTime2, endTime2, true);
        eventList.add(event2);

        // Example on how to provide your own layout
        Calendar startTime3 = Calendar.getInstance();
        Calendar endTime3 = Calendar.getInstance();
        startTime3.set(Calendar.HOUR_OF_DAY, 14);
        startTime3.set(Calendar.MINUTE, 0);
        endTime3.set(Calendar.HOUR_OF_DAY, 15);
        endTime3.set(Calendar.MINUTE, 0);


//        DrawableCalendarEvent event3 = new DrawableCalendarEvent("Visit of Harpa", "", "Dalvík",
//                ContextCompat.getColor(this, R.color.greenyellow), startTime3, endTime3, false, R.mipmap.match_icon_play_by_play);
//        eventList.add(event3);
    }

}
