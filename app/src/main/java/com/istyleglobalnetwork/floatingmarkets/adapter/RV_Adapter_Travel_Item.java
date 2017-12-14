package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.CommentActivity;
import com.istyleglobalnetwork.floatingmarkets.MapsActivity;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataTravelItem;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderContact;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderImageTravel;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderLocation;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderText1;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderTime;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Travel_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Travel_Item(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_image_travel, parent, false);
                viewHolder = new ViewHolderImageTravel(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_location, parent, false);
                viewHolder = new ViewHolderLocation(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_time, parent, false);
                viewHolder = new ViewHolderTime(v3);
                break;
            case 3:
                View v4 = inflater.inflate(R.layout.card_contact, parent, false);
                viewHolder = new ViewHolderContact(v4);
                break;
            case 4:
                View v5 = inflater.inflate(R.layout.card_rating, parent, false);
                viewHolder = new ViewHolderRating(v5);
                break;
            case 5:
                View v6 = inflater.inflate(R.layout.card_text2, parent, false);
                viewHolder = new ViewHolderText1(v6);
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
                ViewHolderImageTravel vh1 = (ViewHolderImageTravel) holder;
                configureViewHolderImageTravel(vh1, position);
                break;
            case 1:
                ViewHolderLocation vh2 = (ViewHolderLocation) holder;
                configureViewHolderLocation(vh2);
                break;
            case 2:
                ViewHolderTime vh3 = (ViewHolderTime) holder;
                configureViewHolderTime(vh3, position);
                break;
            case 3:
                ViewHolderContact vh4 = (ViewHolderContact) holder;
                configureViewHolderContact(vh4, position);
                break;
            case 4:
                ViewHolderRating vh5 = (ViewHolderRating) holder;
                configureViewHolderRating(vh5, position);
                break;
            case 5:
                ViewHolderText1 vh6 = (ViewHolderText1) holder;
                configureViewHolderText2(vh6, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
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

    private void configureViewHolderText2(ViewHolderText1 vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        vh1.getTitle().setText("Detail");
        vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderImageTravel(ViewHolderImageTravel vh2, int position) {
        DataTravelItem data = (DataTravelItem) items.get(position);
        vh2.getTvName().setText(data.getNameItem());
    }

    private void configureViewHolderRating(ViewHolderRating vh2, final int position) {
//        vh2.getImage().setImageResource(R.drawable.talad3);
        vh2.getBtnRating().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), CommentActivity.class);
                intent.putExtra("Name", items.get(position).toString());
                inflater.getContext().startActivity(intent);
            }
        });
    }

    private void configureViewHolderLocation(ViewHolderLocation vh2) {
//        vh2.getImage().setImageResource(R.drawable.talad3);
//        vh2.getImage().setImageResource(R.drawable.talad3);
        vh2.getTvLocation().setText("58/1 ถนนเพชรเกษม ซอยเพชรเกษม 41 แขวงบางแค เขตบางแค กรุงเทพมหานคร 10160");
        vh2.getIbMap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), MapsActivity.class);
                inflater.getContext().startActivity(intent);
            }
        });

    }

    private void configureViewHolderTime(ViewHolderTime vh2, int position) {
        vh2.getTvDate().setText("วันจันทร์\nวันอังคาร\nวันพุธ\nวันพฤหัสบดี\nวันศุกร์\nวันเสาร์\nวันอาทิตย์");
        vh2.getTvTime().setText("ปิด\nปิด\n9:00 - 18:00\n9:00 - 18:00\n9:00 - 22:00\n9:00 - 22:00\n9:00 - 22:00");
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
