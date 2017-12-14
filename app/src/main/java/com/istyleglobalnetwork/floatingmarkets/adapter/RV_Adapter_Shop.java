package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.shop.ShopItemActivity;
import com.istyleglobalnetwork.floatingmarkets.data.DataShopItem;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderShop;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Shop extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Shop(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_shop, parent, false);
        viewHolder = new ViewHolderShop(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderShop vh = (ViewHolderShop) holder;
        configureViewHolderShop(vh, position);
    }

    private void configureViewHolderShop(ViewHolderShop vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        final DataShopItem data = (DataShopItem) items.get(position);
        vh1.getIvShop().setImageResource(data.getImageItem());
        vh1.getTvName().setText(data.getNameItem());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), ShopItemActivity.class);
                intent.putExtra("NameShop", data.getNameItem());
//                intent.putExtra("ImageTravel", data.getImageItem());
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
