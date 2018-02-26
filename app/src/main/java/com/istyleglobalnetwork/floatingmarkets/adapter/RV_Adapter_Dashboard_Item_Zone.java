package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.DateTimeMillis;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductOrder;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderLineChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Dashboard_Item_Zone extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    DatabaseReference mRootRef;

    public RV_Adapter_Dashboard_Item_Zone(List<Object> items) {
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
