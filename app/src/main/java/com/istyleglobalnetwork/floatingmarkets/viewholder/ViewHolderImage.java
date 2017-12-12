package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderImage extends RecyclerView.ViewHolder {

    private ImageView iv;

    public ViewHolderImage(View itemView) {
        super(itemView);
        iv = (ImageView) itemView.findViewById(R.id.iv_product);
    }

    public void setImage(ImageView imageView){
        this.iv = imageView;
    }

    public ImageView getImage() {
        return iv;
    }

}
