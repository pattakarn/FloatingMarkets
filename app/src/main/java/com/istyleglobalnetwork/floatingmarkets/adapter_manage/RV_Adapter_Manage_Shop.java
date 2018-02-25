package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DashboardItemActivity;
import com.istyleglobalnetwork.floatingmarkets.EditShopActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderManageShop;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Manage_Shop extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;
    private WrapFdbMarket itemMarket;
    private WrapFdbZone itemZone;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Manage_Shop(List<Object> items, WrapFdbMarket itemMarket, WrapFdbZone itemZone) {
        this.items = items;
        this.itemMarket = itemMarket;
        this.itemZone = itemZone;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_manage_zone, parent, false);
        viewHolder = new ViewHolderManageShop(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderManageShop vh = (ViewHolderManageShop) holder;
        configureViewHolderManageShop(vh, position);
    }

    private void configureViewHolderManageShop(ViewHolderManageShop vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        final WrapFdbShop dataShop = (WrapFdbShop) items.get(position);
//        vh1.getIv().setImageResource(data.getImageMarket());
        vh1.getTvZone().setText("Shop : " + dataShop.getData().getNameShop());
        vh1.getTvOwner().setText(dataShop.getData().getOwner());
        vh1.getTvPhone().setText(dataShop.getData().getPhone());
        vh1.getTvLine().setText(dataShop.getData().getLine());
        vh1.getTvFb().setText(dataShop.getData().getFacebook());
        vh1.getTvEmail().setText(dataShop.getData().getEmail());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), EditShopActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemMarket", Parcels.wrap(itemMarket));
                bundle.putParcelable("itemZone", Parcels.wrap(itemZone));
                bundle.putParcelable("itemShop", Parcels.wrap(dataShop));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
        vh1.getIvStat().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), DashboardItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemShop", Parcels.wrap(dataShop));
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
