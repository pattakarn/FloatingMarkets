package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderManageProduct extends RecyclerView.ViewHolder {

    private CardView cv;
    private TextView tvProduct;
    private TextView tvType;
    private TextView tvDetail;


    public ViewHolderManageProduct(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        tvProduct = (TextView) itemView.findViewById(R.id.tv_product);
        tvType = (TextView) itemView.findViewById(R.id.tv_type);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public TextView getTvProduct() {
        return tvProduct;
    }

    public void setTvProduct(TextView tvProduct) {
        this.tvProduct = tvProduct;
    }

    public TextView getTvType() {
        return tvType;
    }

    public void setTvType(TextView tvType) {
        this.tvType = tvType;
    }

    public TextView getTvDetail() {
        return tvDetail;
    }

    public void setTvDetail(TextView tvDetail) {
        this.tvDetail = tvDetail;
    }
}
