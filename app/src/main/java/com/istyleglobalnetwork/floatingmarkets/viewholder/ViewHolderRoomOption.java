package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderRoomOption extends RecyclerView.ViewHolder {

    private TextView tvSize;
    private TextView tvGuest;
    private TextView tvBed;
    private TextView tvBreakfast;
    private TextView tvWifi;
    private TextView tvAir;
    private TextView tvSmoke;
    private TextView tvCar;


    public ViewHolderRoomOption(View itemView) {
        super(itemView);
        tvSize = (TextView) itemView.findViewById(R.id.tv_size);
        tvGuest = (TextView) itemView.findViewById(R.id.tv_guest);
        tvBed = (TextView) itemView.findViewById(R.id.tv_bed);
        tvBreakfast = (TextView) itemView.findViewById(R.id.tv_breakfast);
        tvWifi = (TextView) itemView.findViewById(R.id.tv_wifi);
        tvAir = (TextView) itemView.findViewById(R.id.tv_air);
        tvSmoke = (TextView) itemView.findViewById(R.id.tv_smoke);
        tvCar = (TextView) itemView.findViewById(R.id.tv_car);
    }

    public TextView getTvSize() {
        return tvSize;
    }

    public void setTvSize(TextView tvSize) {
        this.tvSize = tvSize;
    }

    public TextView getTvGuest() {
        return tvGuest;
    }

    public void setTvGuest(TextView tvGuest) {
        this.tvGuest = tvGuest;
    }

    public TextView getTvBed() {
        return tvBed;
    }

    public void setTvBed(TextView tvBed) {
        this.tvBed = tvBed;
    }

    public TextView getTvBreakfast() {
        return tvBreakfast;
    }

    public void setTvBreakfast(TextView tvBreakfast) {
        this.tvBreakfast = tvBreakfast;
    }

    public TextView getTvWifi() {
        return tvWifi;
    }

    public void setTvWifi(TextView tvWifi) {
        this.tvWifi = tvWifi;
    }

    public TextView getTvAir() {
        return tvAir;
    }

    public void setTvAir(TextView tvAir) {
        this.tvAir = tvAir;
    }

    public TextView getTvSmoke() {
        return tvSmoke;
    }

    public void setTvSmoke(TextView tvSmoke) {
        this.tvSmoke = tvSmoke;
    }

    public TextView getTvCar() {
        return tvCar;
    }

    public void setTvCar(TextView tvCar) {
        this.tvCar = tvCar;
    }
}
