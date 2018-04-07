package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderManageMain extends RecyclerView.ViewHolder {

    private CardView cv;
    private TextView tv;


    public ViewHolderManageMain(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }
}
