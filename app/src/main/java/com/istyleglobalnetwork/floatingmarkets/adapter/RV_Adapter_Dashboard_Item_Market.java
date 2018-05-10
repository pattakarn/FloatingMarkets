package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.DateTimeMillis;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductOrder;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderLineChart;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderPieChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Dashboard_Item_Market extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    DatabaseReference mRootRef;

    public RV_Adapter_Dashboard_Item_Market(List<Object> items) {
        this.items = items;
        mRootRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_linechart, parent, false);
                viewHolder = new ViewHolderLineChart(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_linechart, parent, false);
                viewHolder = new ViewHolderLineChart(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_piechart, parent, false);
                viewHolder = new ViewHolderPieChart(v3);
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderLineChart vh1 = (ViewHolderLineChart) holder;
                configureViewHolderLineChart(vh1, position);
                break;
            case 1:
                ViewHolderLineChart vh2 = (ViewHolderLineChart) holder;
                configureViewHolderLineChartQty(vh2, position);
                break;
            case 2:
                ViewHolderPieChart vh3 = (ViewHolderPieChart) holder;
                configureViewHolderPieChart(vh3, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureViewHolderLineChart(final ViewHolderLineChart vh1, int position) {
        List<DataProductOrder> dataProductOrders = (List<DataProductOrder>) items.get(position);

        List<Entry> entries1 = new ArrayList<Entry>();
        final String[] date = DateTimeMillis.getDateWeek();
        final int[] totalPrice = new int[date.length];
        int total = 0;
        for (int i = 0; i < date.length; i++) {
            totalPrice[i] = 0;
        }

        for (DataProductOrder productOrder : dataProductOrders) {
            for (WrapFdbOrder order : productOrder.getOrder()) {
                String key = order.getKey();
                FdbOrder value = order.getData();

                for (int i = 0; i < date.length; i++) {
                    if (value.getDate().equals(DateTimeMillis.DateToMillis(date[i]))) {
                        totalPrice[i] += value.getPrice();
                    }
                }
                total += value.getPrice();
            }
        }

        for (int i = 0; i < date.length; i++) {
            Log.d(date[i], "============================ Price : " + totalPrice[i]);
        }
        for (int i = 0; i < 7; i++) {
            entries1.add(new BarEntry(i, totalPrice[i]));
        }
        vh1.setTv("Price");
        vh1.addLineDataSet(entries1, "Price (" + total + ")");
        vh1.setTitleBar(DateTimeMillis.getDateWeek());

    }

    private void configureViewHolderLineChartQty(ViewHolderLineChart vh2, int position) {
        List<DataProductOrder> dataProductOrders = (List<DataProductOrder>) items.get(position);

        List<Entry> entries1 = new ArrayList<Entry>();
        final String[] date = DateTimeMillis.getDateWeek();
        final int[] totalQty = new int[date.length];
        int total = 0;
        for (int i = 0; i < date.length; i++) {
            totalQty[i] = 0;
        }

        for (DataProductOrder productOrder : dataProductOrders) {
            for (WrapFdbOrder order : productOrder.getOrder()) {
                String key = order.getKey();
                FdbOrder value = order.getData();

                for (int i = 0; i < date.length; i++) {
                    if (value.getDate().equals(DateTimeMillis.DateToMillis(date[i]))) {
                        totalQty[i] += value.getQuantity();
                    }
                }
                total += value.getQuantity();
            }
        }

        for (int i = 0; i < date.length; i++) {
            Log.d(date[i], "============================ Qty : " + totalQty[i]);
        }
        for (int i = 0; i < 7; i++) {
            entries1.add(new BarEntry(i, totalQty[i]));
        }
        vh2.setTv("Qty");
        vh2.addLineDataSet(entries1, "Qty (" + total + ")");
        vh2.setTitleBar(DateTimeMillis.getDateWeek());
    }

    public void setChart2(ViewHolderLineChart view, int day) {
        view.setTv("ยอดขาย");
        List<Entry> entries = new ArrayList<Entry>();
        List<Entry> entries2 = new ArrayList<Entry>();
        for (int i = 0; i < day; i++) {
            entries.add(new BarEntry(i, (float) ((Math.random() * 100) + 1)));
            entries2.add(new BarEntry(i, (float) ((Math.random() * 100) + 1)));
        }

        view.addLineDataSet(entries, "Entry 1");
//        view.setColorBar(Color.RED);
        view.addLineDataSet(entries2, "Entry 2");

//        view.setTitleBar(DateTimeMillis.getDateWeek());
    }

    private void configureViewHolderPieChart(final ViewHolderPieChart vh1, int position) {
        List<DataProductOrder> dataProductOrders = (List<DataProductOrder>) items.get(position);

        Map<String, Integer> tempData = new HashMap<String, Integer>();
        int total = 0;
        List<String> dataKey = new ArrayList<String>();
        List<Integer> dataValue = new ArrayList<Integer>();
        List<String> dataName = new ArrayList<String>();
        Map<String, String> mapName = new ArrayMap<String, String>();

        float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
        String[] xData = {"Mitch", "Jessica" , "Mohammad" , "Kelsey", "Sam", "Robert", "Ashley"};


        ArrayList<PieEntry> entries1 = new ArrayList<PieEntry>();
        final String[] date = DateTimeMillis.getDateWeek();
        final int[] totalPrice = new int[date.length];
        for (int i = 0; i < date.length; i++) {
            totalPrice[i] = 0;
        }
        Boolean state = true;

        for (DataProductOrder productOrder : dataProductOrders) {
//            Log.d("DataProductOrder", "========================== " + productOrder.getProduct().getData().getNameProduct());
            mapName.put(productOrder.getProduct().getKey(), productOrder.getProduct().getData().getNameProduct());
            for (WrapFdbOrder order : productOrder.getOrder()) {
                String key = order.getKey();
                FdbOrder value = order.getData();

//                Log.d("WrapFdbOrder", "========================== " + value.getProductID());
                if (dataKey.size() == 0){
                    dataKey.add(value.getProductID());
                    dataValue.add(value.getPrice());
                    dataName.add(value.getProductID());
                } else {
                    for (int i = 0; i < dataKey.size(); i++) {
//                        Log.d("dataKey", "========================== " + i + " = " + value.getProductID() + " + " + dataKey.get(i));
                        if (dataKey.get(i).equals(value.getProductID())) {
                            int sum = dataValue.get(i) + value.getPrice();
                            dataValue.set(i, sum);
                            state = false;
                            break;
                        }
                    }
                    if (state){
                        dataKey.add(value.getProductID());
                        dataValue.add(value.getPrice());
                        dataName.add(value.getProductID());
                    } else {
                        state = true;
                    }
                }


                total += value.getPrice();
            }
        }

        vh1.setTv("Product");
        vh1.getChart().setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        vh1.getChart().setHoleRadius(25f);
//        vh1.getChart().setTransparentCircleAlpha(0);
        vh1.getChart().setCenterText("Super Cool Chart");
        vh1.getChart().setCenterTextSize(10);


        for(int i = 0; i < dataKey.size(); i++){
            float number = (float) (dataValue.get(i)/(total*1.0)*100);
//            Log.d("value product", "========================== " + dataValue.get(i) + " " + total + " = " + number);
            entries1.add(new PieEntry(number, mapName.get(dataName.get(i))));
        }
//        PieDataSet dataset = new PieDataSet(entries1, "Test");
//        PieData data = new PieData(dataset); // initialize Piedata<br />
//        vh1.getChart().setData(data);

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(entries1, "Product Sell (%)");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = vh1.getChart().getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        vh1.getChart().setUsePercentValues(true);
        vh1.getChart().setDrawEntryLabels(false);
        vh1.getChart().setData(pieData);
        vh1.getChart().invalidate();


    }


    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (items.get(position) instanceof Text) {
//            return TITLE;
//        } else if (items.get(position) instanceof String) {
//            return IMAGE;
//        }
        return position;
    }


}
