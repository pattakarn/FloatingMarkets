package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.shop.ShopListActivity;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderZone;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Zone extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> itemsIv;
    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Zone(List<Object> itemsIv, List<Object> items) {
        this.itemsIv = itemsIv;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_grid_zone, parent, false);
        viewHolder = new ViewHolderZone(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderZone vh = (ViewHolderZone) holder;
        configureViewHolderZone(vh, position);
    }

    private void configureViewHolderZone(ViewHolderZone vh1, final int position) {
        final WrapFdbZone data = (WrapFdbZone) items.get(position);

        vh1.getIv().setImageResource((Integer) itemsIv.get(position));
        vh1.getTv().setText(data.getData().getNameZone());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), ShopListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemZone", Parcels.wrap(data));
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
