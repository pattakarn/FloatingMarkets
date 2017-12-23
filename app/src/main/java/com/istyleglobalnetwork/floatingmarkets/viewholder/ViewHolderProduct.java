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

public class ViewHolderProduct extends RecyclerView.ViewHolder {

    private CardView cv;
    private ImageView ivProduct;
    private TextView tvName;
    private TextView tvDetail;
    private TextView tvShipping;
    private TextView tvPrice;

    private RatingBar rb;


    public ViewHolderProduct(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        ivProduct = (ImageView) itemView.findViewById(R.id.iv_product);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
        tvShipping = (TextView) itemView.findViewById(R.id.tv_shipping);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);

        rb = (RatingBar) itemView.findViewById(R.id.rb);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public ImageView getIvProduct() {
        return ivProduct;
    }

    public void setIvProduct(ImageView ivProduct) {
        this.ivProduct = ivProduct;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvDetail() {
        return tvDetail;
    }

    public void setTvDetail(TextView tvDetail) {
        this.tvDetail = tvDetail;
    }

    public TextView getTvShipping() {
        return tvShipping;
    }

    public void setTvShipping(TextView tvShipping) {
        this.tvShipping = tvShipping;
    }

    public TextView getTvPrice() {
        return tvPrice;
    }

    public void setTvPrice(TextView tvPrice) {
        this.tvPrice = tvPrice;
    }

    public RatingBar getRb() {
        return rb;
    }

    public void setRb(RatingBar rb) {
        this.rb = rb;
    }
}
