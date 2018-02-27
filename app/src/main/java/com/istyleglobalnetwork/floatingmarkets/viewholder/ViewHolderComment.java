package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderComment extends RecyclerView.ViewHolder {

    private ImageView iv;
    private TextView tvTitle;
    private TextView tvDetail;
    private RatingBar ratingBar;

    public ViewHolderComment(View itemView) {
        super(itemView);
        this.iv = (ImageView) itemView.findViewById(R.id.iv);
        this.tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        this.tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
        this.ratingBar = (RatingBar) itemView.findViewById(R.id.rating);
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvDetail() {
        return tvDetail;
    }

    public void setTvDetail(TextView tvDetail) {
        this.tvDetail = tvDetail;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }
}
