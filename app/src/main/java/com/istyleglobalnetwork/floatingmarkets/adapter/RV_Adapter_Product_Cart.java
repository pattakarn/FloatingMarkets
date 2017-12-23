package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderProductCart;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Product_Cart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Product_Cart(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_product_cart, parent, false);
        viewHolder = new ViewHolderProductCart(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderProductCart vh = (ViewHolderProductCart) holder;
        configureViewHolderProductCart(vh, position);
    }

    private void configureViewHolderProductCart(ViewHolderProductCart vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        String[] number = {"1", "2", "3", "4", "5", "6", "7"};
        ArrayAdapter<String> adapterThai = new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_spinner_item, number);
        vh1.getSpnNumber().setAdapter(adapterThai);

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
