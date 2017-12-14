package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderTime extends RecyclerView.ViewHolder {

    private ImageView ivTime;
    private TextView tvDate;
    private TextView tvTime;


    public ViewHolderTime(View itemView) {
        super(itemView);
        ivTime = (ImageView) itemView.findViewById(R.id.iv_time);
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time);
    }

    public ImageView getIvTime() {
        return ivTime;
    }

    public void setIvTime(ImageView ivTime) {
        this.ivTime = ivTime;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public void setTvTime(TextView tvTime) {
        this.tvTime = tvTime;
    }
}
