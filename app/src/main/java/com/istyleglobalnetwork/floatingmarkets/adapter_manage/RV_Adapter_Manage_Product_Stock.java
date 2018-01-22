package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.EditStockActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbStock;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbStock;
import com.istyleglobalnetwork.floatingmarkets.ManageStockOrderActivity;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.ManageStockListActivity;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderManageStock;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Manage_Product_Stock extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    WrapFdbStock itemStock = null;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Manage_Product_Stock(List<Object> items) {
        this.items = items;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_manage_stock, parent, false);
        viewHolder = new ViewHolderManageStock(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderManageStock vh = (ViewHolderManageStock) holder;
        configureViewHolderManageStock(vh, position);
    }

    private void configureViewHolderManageStock(ViewHolderManageStock vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        final WrapFdbProduct data = (WrapFdbProduct) items.get(position);
        getStock(vh1, position);
//        vh1.getIv().setImageResource(data.getImageMarket());
        vh1.getTvProduct().setText("Product : " + data.getData().getNameProduct());
        vh1.getTvType().setText(data.getData().getType());
        vh1.getTvDetail().setText(data.getData().getDescription());
        vh1.getBtnEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), EditStockActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putParcelable("itemMarket", Parcels.wrap(itemMarket));
//                bundle.putParcelable("itemZone", Parcels.wrap(itemZone));
//                bundle.putParcelable("itemShop", Parcels.wrap(itemShop));
                bundle.putParcelable("itemProduct", Parcels.wrap(data));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
        vh1.getBtnList().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), ManageStockListActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putParcelable("itemMarket", Parcels.wrap(itemMarket));
//                bundle.putParcelable("itemZone", Parcels.wrap(itemZone));
//                bundle.putParcelable("itemShop", Parcels.wrap(itemShop));
                bundle.putParcelable("itemProduct", Parcels.wrap(data));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
        vh1.getBtnOrder().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), ManageStockOrderActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putParcelable("itemMarket", Parcels.wrap(itemMarket));
//                bundle.putParcelable("itemZone", Parcels.wrap(itemZone));
//                bundle.putParcelable("itemShop", Parcels.wrap(itemShop));
                bundle.putParcelable("itemProduct", Parcels.wrap(data));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });

    }

    private void getStock(final ViewHolderManageStock vh1, int position){
        final WrapFdbProduct data = (WrapFdbProduct) items.get(position);

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("stock").child(data.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbStock value = dataSnapshot.getValue(FdbStock.class);
                itemStock = new WrapFdbStock(key, value);

                if (itemStock.getData() != null) {
                    vh1.getTvAmount().setText(itemStock.getData().getQuantity() + "");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
