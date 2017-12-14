package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.CommentActivity;
import com.istyleglobalnetwork.floatingmarkets.MapsActivity;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataServiceItem;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderContact;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderImageService;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderLocation;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderText1;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Service_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Service_Item(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_image_service, parent, false);
                viewHolder = new ViewHolderImageService(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_contact, parent, false);
                viewHolder = new ViewHolderContact(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_location, parent, false);
                viewHolder = new ViewHolderLocation(v3);
                break;
            case 3:
                View v4 = inflater.inflate(R.layout.card_rating, parent, false);
                viewHolder = new ViewHolderRating(v4);
                break;
            case 4:
                View v5 = inflater.inflate(R.layout.card_text2, parent, false);
                viewHolder = new ViewHolderText1(v5);
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
                ViewHolderImageService vh1 = (ViewHolderImageService) holder;
                configureViewHolderImageService(vh1, position);
                break;
            case 1:
                ViewHolderContact vh2 = (ViewHolderContact) holder;
                configureViewHolderContact(vh2, position);
                break;
            case 2:
                ViewHolderLocation vh3 = (ViewHolderLocation) holder;
                configureViewHolderLocation(vh3);
                break;
            case 3:
                ViewHolderRating vh4 = (ViewHolderRating) holder;
                configureViewHolderRating(vh4, position);
                break;
            case 4:
                ViewHolderText1 vh5 = (ViewHolderText1) holder;
                configureViewHolderText2(vh5, position);
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

    private void configureViewHolderImageService(ViewHolderImageService vh2, int position) {
        DataServiceItem data = (DataServiceItem) items.get(position);
        vh2.setPagerImage(data.getImageItem());
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
