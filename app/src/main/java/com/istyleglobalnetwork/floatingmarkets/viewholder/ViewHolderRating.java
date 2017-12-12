package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderRating extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvRatingMean;
    private TextView tvRatingAll;
    private TextView tv5star;
    private TextView tv4star;
    private TextView tv3star;
    private TextView tv2star;
    private TextView tv1star;

    private RatingBar ratingBar;

    public ViewHolderRating(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvRatingMean = (TextView) itemView.findViewById(R.id.tv_ratingMean);
        tvRatingMean = (TextView) itemView.findViewById(R.id.tv_ratingAll);
        tv5star = (TextView) itemView.findViewById(R.id.tv_5star);
        tv4star = (TextView) itemView.findViewById(R.id.tv_4star);
        tv3star = (TextView) itemView.findViewById(R.id.tv_3star);
        tv2star = (TextView) itemView.findViewById(R.id.tv_2star);
        tv1star = (TextView) itemView.findViewById(R.id.tv_1star);

        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
    }

    public TextView getTitle() {
        return tvTitle;
    }

    public void setTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvRatingMean() {
        return tvRatingMean;
    }

    public void setTvRatingMean(TextView tvRatingMean) {
        this.tvRatingMean = tvRatingMean;
    }

    public TextView getTvRatingAll() {
        return tvRatingAll;
    }

    public void setTvRatingAll(TextView tvRatingAll) {
        this.tvRatingAll = tvRatingAll;
    }

    public TextView getTv5star() {
        return tv5star;
    }

    public void setTv5star(TextView tv5star) {
        this.tv5star = tv5star;
    }

    public TextView getTv4star() {
        return tv4star;
    }

    public void setTv4star(TextView tv4star) {
        this.tv4star = tv4star;
    }

    public TextView getTv3star() {
        return tv3star;
    }

    public void setTv3star(TextView tv3star) {
        this.tv3star = tv3star;
    }

    public TextView getTv2star() {
        return tv2star;
    }

    public void setTv2star(TextView tv2star) {
        this.tv2star = tv2star;
    }

    public TextView getTv1star() {
        return tv1star;
    }

    public void setTv1star(TextView tv1star) {
        this.tv1star = tv1star;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }
}
