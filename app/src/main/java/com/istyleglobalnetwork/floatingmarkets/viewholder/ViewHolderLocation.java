package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderLocation extends RecyclerView.ViewHolder {

    private TextView tvLocation;
    private RatingBar ratingBar;
    private ImageButton ibMap;

    public ViewHolderLocation(final View itemView) {
        super(itemView);
        tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        ibMap = (ImageButton) itemView.findViewById(R.id.ib_map);
    }

    public TextView getTvLocation() {
        return tvLocation;
    }

    public void setTvLocation(TextView tvLocation) {
        this.tvLocation = tvLocation;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }

    public ImageButton getIbMap() {
        return ibMap;
    }

    public void setIbMap(ImageButton ibMap) {
        this.ibMap = ibMap;
    }

}
