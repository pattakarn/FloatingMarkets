package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbStock;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderManageStockOrder;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Manage_StockOrder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    WrapFdbStock itemStock = null;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Manage_StockOrder(List<Object> items) {
        this.items = items;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_stockorder, parent, false);
        viewHolder = new ViewHolderManageStockOrder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderManageStockOrder vh = (ViewHolderManageStockOrder) holder;
        configureViewHolderManageStock(vh, position);
    }

    private void configureViewHolderManageStock(ViewHolderManageStockOrder vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        final WrapFdbOrder data = (WrapFdbOrder) items.get(position);
        String status = data.getData().getStatus();
//        vh1.getIv().setImageResource(data.getImageMarket());
        vh1.getTvStatus().setText(status);
        vh1.getTvAmount().setText(data.getData().getQuantity() + "");

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
