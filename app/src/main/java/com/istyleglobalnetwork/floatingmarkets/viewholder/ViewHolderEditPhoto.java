package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ImageViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditPhoto extends RecyclerView.ViewHolder {

    private ImageViewGroup ivg;

    public ViewHolderEditPhoto(View itemView) {
        super(itemView);
        this.ivg = (ImageViewGroup) itemView.findViewById(R.id.ivg);
    }

    public ImageViewGroup getIvg() {
        return ivg;
    }

    public void setIvg(ImageViewGroup ivg) {
        this.ivg = ivg;
    }
}
