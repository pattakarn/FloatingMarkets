package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.istyleglobalnetwork.floatingmarkets.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderPieChart extends RecyclerView.ViewHolder {

    TextView tv;
    PieChart chart;

    List<ILineDataSet> lineDataSet;
    IAxisValueFormatter formatter;

    int colorBar = Color.BLUE;
    int colorValueText = Color.RED;

    public ViewHolderPieChart(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
        chart = (PieChart) itemView.findViewById(R.id.piechart);

        lineDataSet = new ArrayList<ILineDataSet>();
    }

    public String getTv() {
        return tv.getText().toString();
    }

    public void setTv(String title) {
        tv.setText(title);
    }

    public PieChart getChart() {
        return chart;
    }

    public void setChart(PieChart chart) {
        this.chart = chart;
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

//    public void setTitleBar(final String[] dataTitle) {
//        Log.d("dataTitle", "================ " + dataTitle.length);
//        formatter = new IAxisValueFormatter() {
//
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return dataTitle[(int) value];
//            }
//
//
//            // we don't draw numbers, so no decimal digits needed
//        };
//
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setGranularity(1); // minimum axis-step (interval) is 1
//        xAxis.setValueFormatter(formatter);
//    }
//
//    public int getSizeLineData() {
//        if (lineDataSet.size() != 0) {
//            return lineDataSet.get(0).getEntryCount();
//        }
//        return 0;
//    }

}
