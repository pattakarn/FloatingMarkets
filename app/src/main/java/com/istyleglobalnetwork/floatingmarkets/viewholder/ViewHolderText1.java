package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderText1 extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvDetail;

    public ViewHolderText1(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
    }

    public void setTitle(TextView textView){
        this.tvTitle = textView;
    }

    public TextView getTitle() {
        return tvTitle;
    }

    public void setDetail(TextView textView) {
        this.tvDetail = textView;
    }

    public TextView getDetail() {
        return tvDetail;
    }


}
