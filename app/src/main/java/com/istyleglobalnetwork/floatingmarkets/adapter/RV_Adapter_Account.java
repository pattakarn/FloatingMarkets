package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderAccountPhoto;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderAccountProfile;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Account extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Account(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_account_photo, parent, false);
                viewHolder = new ViewHolderAccountPhoto(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_account_profile, parent, false);
                viewHolder = new ViewHolderAccountProfile(v2);
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderAccountPhoto vh1 = (ViewHolderAccountPhoto) holder;
                configureViewHolderAccountPhoto(vh1, position);
                break;
            case 1:
                ViewHolderAccountProfile vh2 = (ViewHolderAccountProfile) holder;
                configureViewHolderAccountProfile(vh2, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureViewHolderAccountProfile(ViewHolderAccountProfile vh1, int position) {

        vh1.getColName().getTvTitle().setText("Contact Name");
        vh1.getColEmail().getTvTitle().setText("Email");
        vh1.getColSex().getTvTitle().setText("Gender");
        vh1.getColBirth().getTvTitle().setText("Birth");

//        vh1.getColEmail().getTvValue().setText();getTv
    }

    private void configureViewHolderAccountPhoto(ViewHolderAccountPhoto vh1, int position) {

        vh1.getTvTitle().setText("Photo");

//        vh1.getColEmail().getTvValue().setText();getTv
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
