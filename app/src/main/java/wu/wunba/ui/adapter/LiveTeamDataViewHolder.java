package wu.wunba.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

import wu.wunba.R;
import wu.wunba.model.NBAGameLiveDataInfo;
import wu.wunba.ui.widget.RadarMarkerView;

import static wu.wunba.utils.AppUtils.getAssets;

/**
 * 描述：
 * 作者：Wu on 2017/3/9 20:10
 * 邮箱：wuwende@live.cn
 */

public class LiveTeamDataViewHolder extends BaseHolder<NBAGameLiveDataInfo>{

    private Context mContext;
    private TextView tvTitle;
    private RadarChart radarChart;

    private Typeface mTfRegular;
    private Typeface mTfLight;

    private String[] mActivities;

    public LiveTeamDataViewHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;

        tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
        radarChart = (RadarChart)itemView.findViewById(R.id.radart_chart);
        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        radarChart.setBackgroundColor(Color.WHITE);
        radarChart.getDescription().setEnabled(false);
        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.LTGRAY);
        radarChart.setWebLineWidthInner(1f);
        radarChart.setWebColorInner(Color.LTGRAY);
        radarChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(mContext, R.layout.radar_markerview);
        mv.setChartView(radarChart); // For bounds control
        radarChart.setMarker(mv); // Set the marker to the chart

    }


    @Override
    public void refreshData(NBAGameLiveDataInfo data, int position) {
        tvTitle.setText(data.getTeamStats().getText());
        mActivities = new String[data.getTeamStats().getTeamStats().size()];
        setRadarData(data);
    }


    /**
     * @param liveDataInfo
     */
    public void setRadarData(final NBAGameLiveDataInfo liveDataInfo) {

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < liveDataInfo.getTeamStats().getTeamStats().size(); i++) {
            String left_value =liveDataInfo.getTeamStats().getTeamStats().get(i).getLeftVal();
            float value_1 = Float.valueOf(left_value).intValue();
            entries1.add(new RadarEntry(value_1));

            String right_value =liveDataInfo.getTeamStats().getTeamStats().get(i).getRightVal();
            float value_2 = Float.valueOf(right_value).intValue();
            entries2.add(new RadarEntry(value_2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, liveDataInfo.getTeamInfo().getLeftName());
        set1.setColor(mContext.getResources().getColor(R.color.color_f84f21));
        set1.setFillColor(mContext.getResources().getColor(R.color.color_f84f21));//
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, liveDataInfo.getTeamInfo().getRightName());
        set2.setColor(mContext.getResources().getColor(R.color.color_7dcf2c));
        set2.setFillColor(mContext.getResources().getColor(R.color.color_7dcf2c));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTypeface(mTfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.YELLOW);

        radarChart.setData(data);
        radarChart.invalidate();

        radarChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);

        for(int i=0;i<liveDataInfo.getTeamStats().getTeamStats().size();i++){
            mActivities[i] = liveDataInfo.getTeamStats().getTeamStats().get(i).getText();
        }
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(mContext.getResources().getColor(R.color.text_content_gray));

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setTypeface(mTfLight);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        //设置分类的样式
        Legend l = radarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTypeface(mTfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(mContext.getResources().getColor(R.color.color_main_text));
    }



}
