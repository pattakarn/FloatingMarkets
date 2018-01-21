package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.CommentActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.product.ProductItemActivity;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductItem;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderAward;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderContact;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderImageShop;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderProduct;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderTime;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Shop_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    WrapFdbShop itemShop;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Shop_Item(WrapFdbShop itemShop, List<Object> items, LayoutInflater inflater) {
        this.itemShop = itemShop;
        this.items = items;
        this.inflater = inflater;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_image_shop_rv, parent, false);
                viewHolder = new ViewHolderImageShop(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_award, parent, false);
                viewHolder = new ViewHolderAward(v2);
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
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                View v6 = inflater.inflate(R.layout.card_product, parent, false);
                viewHolder = new ViewHolderProduct(v6);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderImageShop vh1 = (ViewHolderImageShop) holder;
                configureViewHolderImageShop(vh1, position);
                break;
            case 1:
                ViewHolderAward vh2 = (ViewHolderAward) holder;
                configureViewHolderAward(vh2, position);
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
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                ViewHolderProduct vh6 = (ViewHolderProduct) holder;
                configureViewHolderProduct(vh6, position);
                break;
        }
    }

    private void configureViewHolderContact(ViewHolderContact vh1, int position) {
        WrapFdbShop data = (WrapFdbShop) items.get(position);
//        User user = (User) items.get(position);
//        if (user != null) {
        vh1.getTvLink().setText(data.getData().getFacebook());
        vh1.getTvPhone().setText(data.getData().getPhone());
        vh1.getTvLine().setText(data.getData().getLine());
        vh1.getTvFb().setText(data.getData().getFacebook());
        vh1.getTvEmail().setText(data.getData().getEmail());
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

    private void configureViewHolderAward(ViewHolderAward vh2, int position) {
        vh2.getTvAward().setText("รางวัลที่ 1\nรางวัลที่ 2\nรางวัลที่ 3");
//        vh2.getImage().setImageResource(R.drawable.talad3);
    }

    private void configureViewHolderTime(ViewHolderTime vh2, int position) {
        vh2.getTvDate().setText("วันจันทร์\nวันอังคาร\nวันพุธ\nวันพฤหัสบดี\nวันศุกร์\nวันเสาร์\nวันอาทิตย์");
        vh2.getTvTime().setText("ปิด\nปิด\n9:00 - 18:00\n9:00 - 18:00\n9:00 - 22:00\n9:00 - 22:00\n9:00 - 22:00");
//        vh2.getImage().setImageResource(R.drawable.talad3);
    }

    private void configureViewHolderImageShop(ViewHolderImageShop vh2, int position) {

        ArrayList<WrapFdbImage> data = (ArrayList<WrapFdbImage>) items.get(position);
        vh2.setImage(data);
        vh2.getTvName().setText(itemShop.getData().getNameShop());
//        vh2.getImage().setImageResource(R.drawable.talad3);
    }

    private void configureViewHolderProduct(ViewHolderProduct vh2, int position) {

        final DataProductItem data = (DataProductItem) items.get(position);
        vh2.getIvProduct().setImageResource(data.getImage());
        vh2.getTvName().setText(data.getItemProduct().getData().getNameProduct());
        vh2.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), ProductItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemShop", Parcels.wrap(data.getItemShop()));
                bundle.putParcelable("itemProduct", Parcels.wrap(data.getItemProduct()));
                intent.putExtra("ImageItem", data.getImage());
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
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
