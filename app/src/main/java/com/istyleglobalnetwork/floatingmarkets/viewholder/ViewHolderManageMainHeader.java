package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderManageMainHeader extends RecyclerView.ViewHolder {

    private LinearLayout ll;
    private TextView tv;


    public ViewHolderManageMainHeader(View itemView) {
        super(itemView);
        ll = (LinearLayout) itemView.findViewById(R.id.ll);
        tv = (TextView) itemView.findViewById(R.id.tv_header);
    }

    public LinearLayout getLl() {
        return ll;
    }

    public void setLl(LinearLayout ll) {
        this.ll = ll;
    }

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }
}
