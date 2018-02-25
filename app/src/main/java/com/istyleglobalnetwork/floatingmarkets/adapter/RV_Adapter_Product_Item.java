package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.CommentActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderImageProduct;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderText1;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Product_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;
    WrapFdbProduct itemProduct;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Product_Item(WrapFdbProduct itemProduct, List<Object> items) {
        this.itemProduct = itemProduct;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v2 = inflater.inflate(R.layout.card_image_product_rv, parent, false);
                viewHolder = new ViewHolderImageProduct(v2);
                break;
            case 1:
                View v1 = inflater.inflate(R.layout.card_text1, parent, false);
                viewHolder = new ViewHolderText1(v1);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_text2, parent, false);
                viewHolder = new ViewHolderText1(v3);
                break;
            case 3:
                View v4 = inflater.inflate(R.layout.card_rating, parent, false);
                viewHolder = new ViewHolderRating(v4);
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
                ViewHolderImageProduct vh2 = (ViewHolderImageProduct) holder;
                configureViewHolderImageProduct(vh2, position);
                break;
            case 1:
                ViewHolderText1 vh1 = (ViewHolderText1) holder;
                configureViewHolderText1(vh1, position);
                break;
            case 2:
                ViewHolderText1 vh3 = (ViewHolderText1) holder;
                configureViewHolderText2(vh3, position);
                break;
            case 3:
                ViewHolderRating vh4 = (ViewHolderRating) holder;
                configureViewHolderRating(vh4, position);
                break;

            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureViewHolderText1(ViewHolderText1 vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        vh1.getTitle().setText("จำหน่ายโดย");
        if (items.get(position) != null)
            vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderText2(ViewHolderText1 vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        vh1.getTitle().setText("รายละเอียดสินค้า");
        if (items.get(position) != null)
            vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderImageProduct(ViewHolderImageProduct vh2, int position) {
        ArrayList<WrapFdbImage> data = (ArrayList<WrapFdbImage>) items.get(position);
        vh2.setImage(data);
        if (items.get(position) != null)
            vh2.getTvName().setText(itemProduct.getData().getNameProduct());
    }

    private void configureViewHolderRating(ViewHolderRating vh2, final int position) {
//        vh2.getImage().setImageResource(R.drawable.talad3);
        vh2.getBtnRating().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemProduct", Parcels.wrap(itemProduct));
                intent.putExtras(bundle);
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
