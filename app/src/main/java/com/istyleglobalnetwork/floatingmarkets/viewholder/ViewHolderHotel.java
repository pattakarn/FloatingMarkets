package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderHotel extends RecyclerView.ViewHolder {

    private CardView cv;
    private ImageView ivHotel;
    private TextView tvName;
    private TextView tvLocation;
    private TextView tvRating;

    public ViewHolderHotel(View itemView) {
        super(itemView);
        this.cv = (CardView) itemView.findViewById(R.id.cv);
        this.ivHotel = (ImageView) itemView.findViewById(R.id.iv_hotel);
        this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        this.tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
        this.tvRating = (TextView) itemView.findViewById(R.id.tv_rating);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public ImageView getIvHotel() {
        return ivHotel;
    }

    public void setIvHotel(ImageView ivHotel) {
        this.ivHotel = ivHotel;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvLocation() {
        return tvLocation;
    }

    public void setTvLocation(TextView tvLocation) {
        this.tvLocation = tvLocation;
    }

    public TextView getTvRating() {
        return tvRating;
    }

    public void setTvRating(TextView tvRating) {
        this.tvRating = tvRating;
    }
}
