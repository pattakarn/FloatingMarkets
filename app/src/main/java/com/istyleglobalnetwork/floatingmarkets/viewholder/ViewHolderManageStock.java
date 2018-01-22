package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderManageStock extends RecyclerView.ViewHolder {

    private CardView cv;
    private TextView tvProduct;
    private TextView tvAmount;
    private TextView tvType;
    private TextView tvDetail;
    private Button btnList;
    private Button btnOrder;
    private Button btnEdit;



    public ViewHolderManageStock(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        tvProduct = (TextView) itemView.findViewById(R.id.tv_product);
        tvAmount = (TextView) itemView.findViewById(R.id.tv_amount);
        tvType = (TextView) itemView.findViewById(R.id.tv_type);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
        btnList = (Button) itemView.findViewById(R.id.btn_list);
        btnOrder = (Button) itemView.findViewById(R.id.btn_order);
        btnEdit = (Button) itemView.findViewById(R.id.btn_edit);
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

    public TextView getTvAmount() {
        return tvAmount;
    }

    public void setTvAmount(TextView tvAmount) {
        this.tvAmount = tvAmount;
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

    public Button getBtnList() {
        return btnList;
    }

    public void setBtnList(Button btnList) {
        this.btnList = btnList;
    }

    public Button getBtnOrder() {
        return btnOrder;
    }

    public void setBtnOrder(Button btnOrder) {
        this.btnOrder = btnOrder;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }
}
