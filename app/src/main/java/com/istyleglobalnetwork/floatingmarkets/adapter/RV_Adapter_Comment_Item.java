package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbUser;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbComment;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataRating;
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
                View v1 = inflater.inflate(R.layout.card_rating, parent, false);
                viewHolder = new ViewHolderRating(v1);
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                View v2 = inflater.inflate(R.layout.card_comment, parent, false);
                viewHolder = new ViewHolderComment(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderRating vh1 = (ViewHolderRating) holder;
                configureViewHolderRating(vh1, position);
                break;

            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                ViewHolderComment vh5 = (ViewHolderComment) holder;
                configureViewHolderComment(vh5, position);
                break;
        }
    }

    private void configureViewHolderRating(ViewHolderRating vh2, int position) {
//        vh2.getImage().setImageResource(R.drawable.talad3);
        DataRating data = (DataRating) items.get(position);
        vh2.getBtnRating().setVisibility(View.INVISIBLE);
        vh2.getRatingBar().setRating(data.getStarAvg());
        vh2.getTvRatingMean().setText(data.getStarAvg() + " out of 5");
        vh2.getTvRatingAll().setText(data.getUserAll() + " rating & review");
        vh2.getTv5star().setText("5 stars : " + data.getStar5());
        vh2.getTv4star().setText("4 stars : " + data.getStar4());
        vh2.getTv3star().setText("3 stars : " + data.getStar3());
        vh2.getTv2star().setText("2 stars : " + data.getStar2());
        vh2.getTv1star().setText("1 stars : " + data.getStar1());
    }

    private void configureViewHolderComment(final ViewHolderComment vh2, int position) {
        final WrapFdbComment data = (WrapFdbComment) items.get(position);
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("user").child(data.getData().getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbUser value = dataSnapshot.getValue(FdbUser.class);
                vh2.getTvTitle().setText(value.getNameContact());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        vh2.getTvTitle().setText(data.getData().getUserName());
        vh2.getTvDetail().setText(data.getData().getComment());
        vh2.getRatingBar().setRating(data.getData().getRating());
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
