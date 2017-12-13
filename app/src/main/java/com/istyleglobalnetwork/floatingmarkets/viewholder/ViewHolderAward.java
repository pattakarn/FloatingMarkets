package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderAward extends RecyclerView.ViewHolder {

    private ImageView ivAward;
    private TextView tvAward;


    public ViewHolderAward(View itemView) {
        super(itemView);
        ivAward = (ImageView) itemView.findViewById(R.id.iv_award);
        tvAward = (TextView) itemView.findViewById(R.id.tv_award);
    }

    public ImageView getIvAward() {
        return ivAward;
    }

    public void setIvAward(ImageView ivAward) {
        this.ivAward = ivAward;
    }

    public TextView getTvAward() {
        return tvAward;
    }

    public void setTvAward(TextView tvAward) {
        this.tvAward = tvAward;
    }
}
