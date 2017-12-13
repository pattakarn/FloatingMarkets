package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderContact extends RecyclerView.ViewHolder {

    private TextView tvLink;
    private TextView tvPhone;
    private TextView tvLine;
    private TextView tvFb;
    private TextView tvEmail;


    public ViewHolderContact(View itemView) {
        super(itemView);
        tvLink = (TextView) itemView.findViewById(R.id.tv_link);
        tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
        tvLine = (TextView) itemView.findViewById(R.id.tv_line);
        tvFb = (TextView) itemView.findViewById(R.id.tv_fb);
        tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
    }

    public TextView getTvLink() {
        return tvLink;
    }

    public void setTvLink(TextView tvLink) {
        this.tvLink = tvLink;
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
}
