package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderImage;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderLocation;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRoom;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Hotel_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Hotel_Item(List<Object> items, LayoutInflater inflater) {
        this.items = items;
        this.inflater = inflater;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_image, parent, false);
                viewHolder = new ViewHolderImage(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_location, parent, false);
                viewHolder = new ViewHolderLocation(v2, inflater);
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                View v3 = inflater.inflate(R.layout.card_room, parent, false);
                viewHolder = new ViewHolderRoom(v3);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderImage vh1 = (ViewHolderImage) holder;
                configureViewHolderImage(vh1, position);
                break;
            case 1:
                ViewHolderLocation vh2 = (ViewHolderLocation) holder;
                configureViewHolderLocation(vh2);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                ViewHolderRoom vh3 = (ViewHolderRoom) holder;
                configureViewHolderRoom(vh3, position);
                break;
        }
    }

    private void configureViewHolderRoom(ViewHolderRoom vh1, int position) {

//        User user = (User) items.get(position);
//        if (user != null) {
        vh1.getTvName().setText(items.get(position).toString());
//        vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderImage(ViewHolderImage vh2, int position) {
        vh2.getImage().setImageResource((Integer) items.get(position));
//        vh2.getImage().setImageResource(R.drawable.talad3);
    }

    private void configureViewHolderLocation(ViewHolderLocation vh2) {
//        vh2.getImage().setImageResource(R.drawable.talad3);
//        vh2.getImage().setImageResource(R.drawable.talad3);
        vh2.getIbMap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(inflater.getContext(), "Hello Google Map", Toast.LENGTH_SHORT);
                toast.show();
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
