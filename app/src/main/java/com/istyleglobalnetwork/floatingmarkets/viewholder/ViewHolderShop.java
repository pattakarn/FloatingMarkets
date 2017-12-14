package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderShop extends RecyclerView.ViewHolder {

    private CardView cv;
    private ImageView ivShop;
    private TextView tvName;
    private RatingBar rating;


    public ViewHolderShop(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        ivShop = (ImageView) itemView.findViewById(R.id.iv_shop);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        rating = (RatingBar) itemView.findViewById(R.id.rating);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public ImageView getIvShop() {
        return ivShop;
    }

    public void setIvShop(ImageView ivProduct) {
        this.ivShop = ivShop;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public RatingBar getRating() {
        return rating;
    }

    public void setRating(RatingBar rating) {
        this.rating = rating;
    }
}
