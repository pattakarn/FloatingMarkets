package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.EditProductActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderManageProduct;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Manage_Product extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;
    private WrapFdbMarket itemMarket;
    private WrapFdbZone itemZone;
    private WrapFdbShop itemShop;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Manage_Product(List<Object> items, WrapFdbMarket itemMarket, WrapFdbZone itemZone, WrapFdbShop itemShop) {
        this.items = items;
        this.itemMarket = itemMarket;
        this.itemZone = itemZone;
        this.itemShop = itemShop;

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
        final WrapFdbProduct data = (WrapFdbProduct) items.get(position);
//        vh1.getIv().setImageResource(data.getImageMarket());
        vh1.getTvProduct().setText("Product : " + data.getData().getNameProduct());
        vh1.getTvType().setText(data.getData().getPrice() + "");
        vh1.getTvDetail().setText(data.getData().getDescription());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), EditProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemMarket", Parcels.wrap(itemMarket));
                bundle.putParcelable("itemZone", Parcels.wrap(itemZone));
                bundle.putParcelable("itemShop", Parcels.wrap(itemShop));
                bundle.putParcelable("itemProduct", Parcels.wrap(data));
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
