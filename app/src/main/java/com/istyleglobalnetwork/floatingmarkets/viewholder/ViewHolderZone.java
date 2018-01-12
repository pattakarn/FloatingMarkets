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

public class ViewHolderZone extends RecyclerView.ViewHolder {

    private CardView cv;
    private ImageView iv;
    private TextView tv;


    public ViewHolderZone(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        iv = (ImageView) itemView.findViewById(R.id.iv);
        tv = (TextView) itemView.findViewById(R.id.tv);
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
}
