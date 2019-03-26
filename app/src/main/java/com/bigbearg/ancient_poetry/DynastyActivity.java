package com.bigbearg.ancient_poetry;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bigbearg.ancient_poetry.Bean.DynastyBean;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DynastyActivity extends AppCompatActivity {

    private PieChart mDynastyChart;
    private List<DynastyBean> dynastyBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynasty);
        mDynastyChart = findViewById(R.id.dynasty_pie_chart);
        getData();
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
        pieDataSet.setColors(new int[]{Color.RED,Color.YELLOW,Color.GREEN,Color.DKGRAY,Color.MAGENTA});
        pieDataSet.setValueTextSize(20);
        PieData data = new PieData(pieDataSet);
        data.setValueTextColor(Color.BLACK);
        Description description = new Description();
        description.setText("");
        mDynastyChart.setDescription(description);
        mDynastyChart.setEntryLabelColor(Color.BLACK);
        mDynastyChart.setEntryLabelTextSize(18);
        mDynastyChart.setDrawHoleEnabled(false);
        mDynastyChart.setData(data);
        mDynastyChart.invalidate();
    }
}
