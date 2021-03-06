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

public class ViewHolderManageMarket extends RecyclerView.ViewHolder {

    private CardView cv;
    private ImageView iv;
    private TextView tv;
    private ImageView ivStat;

    public ViewHolderManageMarket(View itemView) {
        super(itemView);
        this.cv = (CardView) itemView.findViewById(R.id.cv);
        this.iv = (ImageView) itemView.findViewById(R.id.iv);
        this.tv = (TextView) itemView.findViewById(R.id.tv);
        this.ivStat = (ImageView) itemView.findViewById(R.id.iv_stat);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public ImageView getIvStat() {
        return ivStat;
    }

    public void setIvStat(ImageView ivStat) {
        this.ivStat = ivStat;
    }
}
