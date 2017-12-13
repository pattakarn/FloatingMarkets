package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataCommentItem;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderComment;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRating;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Comment_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Comment_Item(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v4 = inflater.inflate(R.layout.card_rating, parent, false);
                viewHolder = new ViewHolderRating(v4);
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                View v5 = inflater.inflate(R.layout.card_comment, parent, false);
                viewHolder = new ViewHolderComment(v5);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderRating vh4 = (ViewHolderRating) holder;
                configureViewHolderRating(vh4);
                break;

            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                ViewHolderComment vh5 = (ViewHolderComment) holder;
                configureViewHolderComment(vh5, position);
                break;
        }
    }

    private void configureViewHolderRating(ViewHolderRating vh2) {
//        vh2.getImage().setImageResource(R.drawable.talad3);
        vh2.getBtnRating().setVisibility(View.INVISIBLE);
    }

    private void configureViewHolderComment(ViewHolderComment vh2, int position) {
        DataCommentItem data = (DataCommentItem) items.get(position);
        vh2.getTvTitle().setText(data.getTitle());
        vh2.getTvDetail().setText(data.getDetail());
//        vh2.getImage().setImageResource(R.drawable.talad3);
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
