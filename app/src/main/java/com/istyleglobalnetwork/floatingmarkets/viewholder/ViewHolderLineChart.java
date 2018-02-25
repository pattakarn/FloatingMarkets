package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.istyleglobalnetwork.floatingmarkets.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderLineChart extends RecyclerView.ViewHolder {

    TextView tv;
    LineChart chart;
    RadioButton week;
    RadioButton month;
    RadioButton quatre;
    RadioButton year;

    List<ILineDataSet> lineDataSet;
    IAxisValueFormatter formatter;

    int colorBar = Color.BLUE;
    int colorValueText = Color.RED;

    public ViewHolderLineChart(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
        chart = (LineChart) itemView.findViewById(R.id.chart);
        week = (RadioButton) itemView.findViewById(R.id.week);
        month = (RadioButton) itemView.findViewById(R.id.month);
        quatre = (RadioButton) itemView.findViewById(R.id.quatre);
        year = (RadioButton) itemView.findViewById(R.id.year);

        lineDataSet = new ArrayList<ILineDataSet>();
    }

    public String getTv() {
        return tv.getText().toString();
    }

    public void setTv(String title) {
        tv.setText(title);
    }

    public void addLineDataSet(List<Entry> entries, String nameBar) {
        LineDataSet lineData = new LineDataSet(entries, nameBar);
        switch (lineDataSet.size()) {
            case 0:
                lineData.setColor(Color.RED);
                lineData.setValueTextColor(Color.RED);
                break;
            case 1:
                lineData.setColor(Color.BLUE);
                lineData.setValueTextColor(Color.BLUE);
                break;
            case 2:
                lineData.setColor(Color.GREEN);
                lineData.setValueTextColor(Color.GREEN);
                break;
            case 3:
                lineData.setColor(Color.GRAY);
                lineData.setValueTextColor(Color.GRAY);
                break;

        }
        lineData.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.add(lineData);
        LineData data = new LineData(lineDataSet);
        chart.setData(data);
        chart.invalidate();

    }


    public void clearChart() {
        lineDataSet = new ArrayList<ILineDataSet>();
    }

    public void setColorBar(int colorBar) {
        this.colorBar = colorBar;
    }

    public void setColorValueText(int colorValueText) {
        this.colorValueText = colorValueText;
    }

    public void setTitleBar(final String[] dataTitle) {
        Log.d("dataTitle", "================ " + dataTitle.length);
        formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dataTitle[(int) value];
            }


            // we don't draw numbers, so no decimal digits needed
        };

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
    }

    public int getSizeLineData() {
        if (lineDataSet.size() != 0) {
            return lineDataSet.get(0).getEntryCount();
        }
        return 0;
    }

    public RadioButton getWeek() {
        return week;
    }

    public RadioButton getMonth() {
        return month;
    }

    public RadioButton getQuatre() {
        return quatre;
    }

    public RadioButton getYear() {
        return year;
    }
}
