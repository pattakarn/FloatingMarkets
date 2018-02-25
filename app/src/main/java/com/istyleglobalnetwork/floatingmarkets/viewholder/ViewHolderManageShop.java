package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderManageShop extends RecyclerView.ViewHolder {

    private CardView cv;
    private TextView tvZone;
    private TextView tvOwner;
    private TextView tvPhone;
    private TextView tvLine;
    private TextView tvFb;
    private TextView tvEmail;
    private ImageView ivStat;


    public ViewHolderManageShop(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        tvZone = (TextView) itemView.findViewById(R.id.tv_zone);
        tvOwner = (TextView) itemView.findViewById(R.id.tv_person);
        tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
        tvLine = (TextView) itemView.findViewById(R.id.tv_line);
        tvFb = (TextView) itemView.findViewById(R.id.tv_fb);
        tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
        ivStat = (ImageView) itemView.findViewById(R.id.iv_stat);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public TextView getTvZone() {
        return tvZone;
    }

    public void setTvZone(TextView tvZone) {
        this.tvZone = tvZone;
    }

    public TextView getTvOwner() {
        return tvOwner;
    }

    public void setTvOwner(TextView tvPerson) {
        this.tvOwner = tvPerson;
    }

    public TextView getTvPhone() {
        return tvPhone;
    }

    public void setTvPhone(TextView tvPhone) {
        this.tvPhone = tvPhone;
    }

    public TextView getTvLine() {
        return tvLine;
    }

    public void setTvLine(TextView tvLine) {
        this.tvLine = tvLine;
    }

    public TextView getTvFb() {
        return tvFb;
    }

    public void setTvFb(TextView tvFb) {
        this.tvFb = tvFb;
    }

    public TextView getTvEmail() {
        return tvEmail;
    }

    public void setTvEmail(TextView tvEmail) {
        this.tvEmail = tvEmail;
    }

    public ImageView getIvStat() {
        return ivStat;
    }

    public void setIvStat(ImageView ivStat) {
        this.ivStat = ivStat;
    }
}
