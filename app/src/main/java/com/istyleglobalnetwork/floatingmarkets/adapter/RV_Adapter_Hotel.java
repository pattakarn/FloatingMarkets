package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.hotel.HotelItemActivity;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderHotel;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Hotel extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Hotel(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_hotel, parent, false);
        viewHolder = new ViewHolderHotel(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderHotel vh = (ViewHolderHotel) holder;
        configureViewHolderHotel(vh, position);
    }

    private void configureViewHolderHotel(ViewHolderHotel vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
//        final DataHotelItem data = (DataHotelItem) items.get(position);
        final WrapFdbHotel data = (WrapFdbHotel) items.get(position);
//        vh1.getIvHotel().setImageResource(data.getData().get);
        vh1.getTvName().setText(data.getData().getNameHotel());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), HotelItemActivity.class);
//                intent.putExtra("NameShop", data.getNameItem());
//                intent.putExtra("ImageTravel", data.getImageItem());
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemHotel", Parcels.wrap(data));
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
