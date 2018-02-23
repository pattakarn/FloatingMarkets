package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderAccountPhoto extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private ImageView ivPhoto;

    public ViewHolderAccountPhoto(View itemView) {
        super(itemView);
        this.tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        this.ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public ImageView getIvPhoto() {
        return ivPhoto;
    }

    public void setIvPhoto(ImageView ivPhoto) {
        this.ivPhoto = ivPhoto;
    }
}
