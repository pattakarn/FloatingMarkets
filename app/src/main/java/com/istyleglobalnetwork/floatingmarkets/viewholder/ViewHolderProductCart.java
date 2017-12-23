package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderProductCart extends RecyclerView.ViewHolder {

    private CardView cv;
    private ImageView ivProduct;
    private TextView tvSoldBy;
    private TextView tvName;
    private TextView tvPrice;
    private ImageView ivDelete;
    private Spinner spnNumber;


    public ViewHolderProductCart(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        ivProduct = (ImageView) itemView.findViewById(R.id.iv_product);
        tvSoldBy = (TextView) itemView.findViewById(R.id.tv_soldby);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete);
        spnNumber = (Spinner) itemView.findViewById(R.id.spin_number);

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

    public TextView getTvSoldBy() {
        return tvSoldBy;
    }

    public void setTvSoldBy(TextView tvSoldBy) {
        this.tvSoldBy = tvSoldBy;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvPrice() {
        return tvPrice;
    }

    public void setTvPrice(TextView tvPrice) {
        this.tvPrice = tvPrice;
    }

    public ImageView getIvDelete() {
        return ivDelete;
    }

    public void setIvDelete(ImageView ivDelete) {
        this.ivDelete = ivDelete;
    }

    public Spinner getSpnNumber() {
        return spnNumber;
    }

    public void setSpnNumber(Spinner spnNumber) {
        this.spnNumber = spnNumber;
    }
}
