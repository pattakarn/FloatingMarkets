package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.ArrayList;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderImageService2 extends RecyclerView.ViewHolder {

    private TextView tvName;
    private RecyclerView rv;
    ArrayList<WrapFdbImage> image = new ArrayList<WrapFdbImage>();

    private ShineButton ivLove;
    private TextView tvCount;

    public ViewHolderImageService2(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        rv = (RecyclerView) itemView.findViewById(R.id.rv);

        ivLove = (ShineButton) itemView.findViewById(R.id.iv_love);
        tvCount = (TextView) itemView.findViewById(R.id.tv_count);
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public void setImage(ArrayList<WrapFdbImage> image) {
        this.image = image;

        LinearLayoutManager llm = new LinearLayoutManager(itemView.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Grid_Image_Fdb adapterList = new RV_Adapter_Grid_Image_Fdb(image);
        rv.setAdapter(adapterList);
    }

    public ShineButton getIvLove() {
        return ivLove;
    }

    public void setIvLove(ShineButton ivLove) {
        this.ivLove = ivLove;
    }

    public TextView getTvCount() {
        return tvCount;
    }

    public void setTvCount(TextView tvCount) {
        this.tvCount = tvCount;
    }
}
