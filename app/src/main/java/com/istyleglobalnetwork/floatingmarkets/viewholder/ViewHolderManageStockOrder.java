package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderManageStockOrder extends RecyclerView.ViewHolder {

    private CardView cv;
    private TextView tvStatus;
    private TextView tvAmount;
    private TextView tvDetail;
    private TextView tvUser;
    private TextView tvDate;


    public ViewHolderManageStockOrder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
        tvAmount = (TextView) itemView.findViewById(R.id.tv_amount);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
        tvUser = (TextView) itemView.findViewById(R.id.tv_user);
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public TextView getTvStatus() {
        return tvStatus;
    }

    public void setTvStatus(TextView tvStatus) {
        this.tvStatus = tvStatus;
    }

    public TextView getTvAmount() {
        return tvAmount;
    }

    public void setTvAmount(TextView tvAmount) {
        this.tvAmount = tvAmount;
    }

    public TextView getTvDetail() {
        return tvDetail;
    }

    public void setTvDetail(TextView tvDetail) {
        this.tvDetail = tvDetail;
    }

    public TextView getTvUser() {
        return tvUser;
    }

    public void setTvUser(TextView tvUser) {
        this.tvUser = tvUser;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }
}
