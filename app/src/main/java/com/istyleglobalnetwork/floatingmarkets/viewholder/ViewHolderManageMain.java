package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderManageMain extends RecyclerView.ViewHolder {

    private LinearLayout cv;
    private TextView tv;


    public ViewHolderManageMain(View itemView) {
        super(itemView);
        cv = (LinearLayout) itemView.findViewById(R.id.cv);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }

    public LinearLayout getCv() {
        return cv;
    }

    public void setCv(LinearLayout cv) {
        this.cv = cv;
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }
}
