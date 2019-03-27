package com.bigbearg.ancient_poetry;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigbearg.ancient_poetry.Bean.DynastyBean;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DynastyActivity extends AppCompatActivity {

    private PieChart mDynastyChart;
    private List<DynastyBean> dynastyBeans;
    private LinearLayout mContentLayout;
    private BarChart mBarChart;
    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynasty);
        mContentLayout = findViewById(R.id.chart_content_layout);
        mDynastyChart = findViewById(R.id.dynasty_pie_chart);
        mBarChart = findViewById(R.id.dynasty_bar_chart);
//        mLineChart = findViewById(R.id.dynasty_line_chart);
        getData();
        initView();
    }

    private void getData() {
        //TODO:获取数据
        dynastyBeans = new ArrayList<>();
        dynastyBeans.add(new DynastyBean("唐朝",21));
        dynastyBeans.add(new DynastyBean("宋朝",24));
        dynastyBeans.add(new DynastyBean("元朝",12));
        dynastyBeans.add(new DynastyBean("明朝",6));
        dynastyBeans.add(new DynastyBean("清朝",7));

        initView();
        initBarChart();
//        initLineChart();

    }

    private void initView(){
        List<PieEntry> pieEntries = new ArrayList<>();
        try{
            for(int i=0; i<dynastyBeans.size(); i++){
                DynastyBean bean  = dynastyBeans.get(i);
                pieEntries.add(new PieEntry(bean.getCount(),bean.getDynastyName()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntries," ");
        pieDataSet.setColors(new int[]{Color.parseColor("#FFDAB9"),
                Color.parseColor("#C6E2FF"),
                Color.parseColor("#E0EEEE"),
                Color.parseColor("#C1FFC1"),
                Color.parseColor("#C71585")});
        pieDataSet.setValueTextSize(15);

        pieDataSet.setValueLinePart1OffsetPercentage(80.f);
        pieDataSet.setValueLinePart1Length(0.45f);
        pieDataSet.setValueLinePart2Length(0.35f);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


        pieDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return (int)v+"次";

            }
        });
        Legend legend = mDynastyChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        PieData data = new PieData(pieDataSet);
        data.setValueTextColor(Color.BLACK);
        Description description = new Description();
        description.setText("");
        mDynastyChart.setDescription(description);
        mDynastyChart.setEntryLabelColor(Color.BLACK);
        mDynastyChart.setEntryLabelTextSize(13);
        mDynastyChart.setDrawHoleEnabled(false);
        mDynastyChart.setUsePercentValues(true);

        //设置chart是否可以触摸  
        mDynastyChart.setTouchEnabled(true);
//X轴缩放比例  
        mDynastyChart.setScaleX(0.7f);
//Y轴缩放比例  
        mDynastyChart.setScaleY(0.7f);



        mDynastyChart.setData(data);
        mDynastyChart.invalidate();
    }

    private void initBarChart(){
        List<BarEntry> barEntries = new ArrayList<>();
        try{
            for(int i=0; i<dynastyBeans.size(); i++){
                DynastyBean bean  = dynastyBeans.get(i);
                barEntries.add(new BarEntry(i+1,bean.getCount()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Legend l = mBarChart.getLegend();//获取图例对象



//        l.setEnabled(false);//禁用图例,直接禁用X轴会显示不全


 l.setForm(Legend.LegendForm.NONE);
// l.setTextColor(Color.parseColor("#ffffff"));


        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setTextSize(13);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(dynastyBeans.size());
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return dynastyBeans.get((int)v-1).getDynastyName();
            }
        });


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mBarChart.getAxisRight().setEnabled(false);
        mBarChart.getAxisLeft().setTextSize(13);
        mBarChart.setTouchEnabled(false);
        mBarChart.setScaleEnabled(false);
        mBarChart.setDragEnabled(false);
        BarDataSet barDataSet = new BarDataSet(barEntries," ");
        barDataSet.setValueTextSize(12);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return (int)v+"次";
            }
        });
        barDataSet.setColors(new int[]{Color.parseColor("#FFDAB9"),
                Color.parseColor("#C6E2FF"),
                Color.parseColor("#E0EEEE"),
                Color.parseColor("#C1FFC1"),
                Color.parseColor("#C71585")});

        BarData barData = new BarData(barDataSet);
        Description description = new Description();
        description.setText("");
        mBarChart.setDescription(description);
        mBarChart.setData(barData);
        mBarChart.invalidate();
    }

//    private void initLineChart(){
//        List<Entry> entries = new ArrayList<>();
//        try{
//            for(int i=0; i<dynastyBeans.size(); i++){
//                DynastyBean bean  = dynastyBeans.get(i);
//                entries.add(new Entry(i+1,bean.getCount()));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        LineDataSet lineDataSet = new LineDataSet(entries," ");
//        LineData lineData = new LineData(lineDataSet);
//        Description description = new Description();
//        lineDataSet.setColors(new int[]{Color.parseColor("#FFDAB9"),
//                Color.parseColor("#C6E2FF"),
//                Color.parseColor("#E0EEEE"),
//                Color.parseColor("#C1FFC1"),
//                Color.parseColor("#C71585")});
//        description.setText("");
//        mLineChart.setDescription(description);
//        mLineChart.setData(lineData);
//        mLineChart.invalidate();
//    }




}
