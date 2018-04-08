package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DashboardItemActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.manage.EditNetworkActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderManageNetwork;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Manage_Network extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;
    private WrapFdbMarket itemMarket;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Manage_Network(List<Object> items, WrapFdbMarket itemMarket) {
        this.items = items;
        this.itemMarket = itemMarket;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_manage_network, parent, false);
        viewHolder = new ViewHolderManageNetwork(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderManageNetwork vh = (ViewHolderManageNetwork) holder;
        configureViewHolderManageNetwork(vh, position);
    }

    private void configureViewHolderManageNetwork(ViewHolderManageNetwork vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        final WrapFdbNetwork dataNetwork = (WrapFdbNetwork) items.get(position);
//        vh1.getIv().setImageResource(data.getImageMarket());
        vh1.getTvNetwork().setText("Network : " + dataNetwork.getData().getNameNetwork());
        vh1.getTvOwner().setText(dataNetwork.getData().getOwner());
        vh1.getTvPhone().setText(dataNetwork.getData().getPhone());
        vh1.getTvLine().setText(dataNetwork.getData().getLine());
        vh1.getTvFb().setText(dataNetwork.getData().getFacebook());
        vh1.getTvEmail().setText(dataNetwork.getData().getEmail());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), EditNetworkActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemMarket", Parcels.wrap(itemMarket));
                bundle.putParcelable("itemNetwork", Parcels.wrap(dataNetwork));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
        vh1.getIvStat().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), DashboardItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemNetwork", Parcels.wrap(dataNetwork));
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
