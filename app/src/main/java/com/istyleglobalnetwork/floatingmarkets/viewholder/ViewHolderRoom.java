package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderRoom extends RecyclerView.ViewHolder {

    private ImageView ivRoom;
    private TextView tvName;
    private TextView tvSize;
    private TextView tvBed;
    private TextView tvGuest;
    private TextView tvPrice;


    public ViewHolderRoom(View itemView) {
        super(itemView);
        ivRoom = (ImageView) itemView.findViewById(R.id.iv_room);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        tvSize = (TextView) itemView.findViewById(R.id.tv_size);
        tvBed = (TextView) itemView.findViewById(R.id.tv_bed);
        tvGuest = (TextView) itemView.findViewById(R.id.tv_guest);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
    }

    public ImageView getIvRoom() {
        return ivRoom;
    }

    public void setIvRoom(ImageView ivRoom) {
        this.ivRoom = ivRoom;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvSize() {
        return tvSize;
    }

    public void setTvSize(TextView tvSize) {
        this.tvSize = tvSize;
    }

    public TextView getTvBed() {
        return tvBed;
    }

    public void setTvBed(TextView tvBed) {
        this.tvBed = tvBed;
    }

    public TextView getTvGuest() {
        return tvGuest;
    }

    public void setTvGuest(TextView tvGuest) {
        this.tvGuest = tvGuest;
    }

    public TextView getTvPrice() {
        return tvPrice;
    }

    public void setTvPrice(TextView tvPrice) {
        this.tvPrice = tvPrice;
    }
}
