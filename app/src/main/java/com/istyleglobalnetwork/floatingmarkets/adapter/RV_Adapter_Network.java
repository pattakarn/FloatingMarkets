package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.network.NetworkItemActivity;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderTravel;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Network extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Network(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_travel, parent, false);
        viewHolder = new ViewHolderTravel(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderTravel vh = (ViewHolderTravel) holder;
        configureViewHolderTravel(vh, position);
    }

    private void configureViewHolderTravel(ViewHolderTravel vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
//        final DataTravelItem data = (DataTravelItem) items.get(position);
        final WrapFdbNetwork dataNetwork = (WrapFdbNetwork) items.get(position);
//        vh1.getIv().setImageResource(dataNetwork.getData().getImageItem());
        vh1.getTv().setText(dataNetwork.getData().getNameNetwork());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), NetworkItemActivity.class);
//                intent.putExtra("NameShop", data.getNameItem());
//                intent.putExtra("ImageTravel", data.getImageItem());
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemNetwork", Parcels.wrap(dataNetwork));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
//        }
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
