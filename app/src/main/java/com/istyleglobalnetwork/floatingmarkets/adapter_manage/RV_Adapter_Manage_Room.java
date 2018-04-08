package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DashboardItemActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.manage.EditRoomActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderManageProduct;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Manage_Room extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;
    private WrapFdbMarket itemMarket;
    private WrapFdbHotel itemHotel;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Manage_Room(List<Object> items, WrapFdbMarket itemMarket, WrapFdbHotel itemHotel) {
        this.items = items;
        this.itemMarket = itemMarket;
        this.itemHotel = itemHotel;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_manage_product, parent, false);
        viewHolder = new ViewHolderManageProduct(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderManageProduct vh = (ViewHolderManageProduct) holder;
        configureViewHolderManageProduct(vh, position);
    }

    private void configureViewHolderManageProduct(ViewHolderManageProduct vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        final WrapFdbRoom dataRoom = (WrapFdbRoom) items.get(position);
//        vh1.getIv().setImageResource(data.getImageMarket());
        vh1.getTvProduct().setText("Product : " + dataRoom.getData().getNameRoom());
        vh1.getTvType().setText(dataRoom.getData().getPrice() + "");
        vh1.getTvDetail().setText(dataRoom.getData().getDescription());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), EditRoomActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemMarket", Parcels.wrap(itemMarket));
                bundle.putParcelable("itemHotel", Parcels.wrap(itemHotel));
                bundle.putParcelable("itemRoom", Parcels.wrap(dataRoom));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
        vh1.getIvStat().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), DashboardItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemRoom", Parcels.wrap(dataRoom));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });

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
