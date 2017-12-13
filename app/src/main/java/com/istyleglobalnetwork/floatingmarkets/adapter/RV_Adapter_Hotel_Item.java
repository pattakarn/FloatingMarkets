package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.istyleglobalnetwork.floatingmarkets.CommentActivity;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataImageHotel;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderContact;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderImageHotel;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderLocation;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRoom;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Hotel_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Hotel_Item(List<Object> items) {
        this.items = items;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_image_hotel, parent, false);
                viewHolder = new ViewHolderImageHotel(v1);
                break;
            case 1:
                View v3 = inflater.inflate(R.layout.card_contact, parent, false);
                viewHolder = new ViewHolderContact(v3);
                break;
            case 2:
                View v4 = inflater.inflate(R.layout.card_location, parent, false);
                viewHolder = new ViewHolderLocation(v4, inflater);
                break;
            case 3:
                View v5 = inflater.inflate(R.layout.card_rating, parent, false);
                viewHolder = new ViewHolderRating(v5);
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                View v6 = inflater.inflate(R.layout.card_room, parent, false);
                viewHolder = new ViewHolderRoom(v6);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderImageHotel vh1 = (ViewHolderImageHotel) holder;
                configureViewHolderImageHotel(vh1, position);
                break;
            case 1:
                ViewHolderContact vh3 = (ViewHolderContact) holder;
                configureViewHolderContact(vh3, position);
                break;
            case 2:
                ViewHolderLocation vh4 = (ViewHolderLocation) holder;
                configureViewHolderLocation(vh4);
                break;
            case 3:
                ViewHolderRating vh5 = (ViewHolderRating) holder;
                configureViewHolderRating(vh5, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                ViewHolderRoom vh6 = (ViewHolderRoom) holder;
                configureViewHolderRoom(vh6, position);
                break;
        }
    }

    private void configureViewHolderContact(ViewHolderContact vh1, int position) {

//        User user = (User) items.get(position);
//        if (user != null) {
        vh1.getTvLink().setText("http://www.diamondsweethotel.com");
        vh1.getTvPhone().setText("02 455 2031");
        vh1.getTvEmail().setText("diamond_sweet41@hotmail.com");
//        vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderRating(ViewHolderRating vh1, final int position) {
        vh1.getBtnRating().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), CommentActivity.class);
                intent.putExtra("Name", items.get(position).toString());
                inflater.getContext().startActivity(intent);
            }
        });
//        User user = (User) items.get(position);
//        if (user != null) {
//        vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderRoom(ViewHolderRoom vh1, int position) {

//        User user = (User) items.get(position);
//        if (user != null) {
        vh1.getTvName().setText(items.get(position).toString());
//        vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderImageHotel(ViewHolderImageHotel vh2, int position) {
        DataImageHotel data = (DataImageHotel) items.get(position);
//        vh2.getIvHotel().setImageResource(data.getImage());
        vh2.getTvName().setText(data.getNameHotel());
//        vh2.getImage().setImageResource(R.drawable.talad3);
    }

    private void configureViewHolderLocation(ViewHolderLocation vh2) {
//        vh2.getImage().setImageResource(R.drawable.talad3);
//        vh2.getImage().setImageResource(R.drawable.talad3);
        vh2.getTvLocation().setText("58/1 ถนนเพชรเกษม ซอยเพชรเกษม 41 แขวงบางแค เขตบางแค กรุงเทพมหานคร 10160");
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
