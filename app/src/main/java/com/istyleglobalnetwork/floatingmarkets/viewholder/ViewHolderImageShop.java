package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;

import java.util.ArrayList;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderImageShop extends RecyclerView.ViewHolder {

    private TextView tvName;
    private RecyclerView rv;
    ArrayList<WrapFdbImage> image = new ArrayList<WrapFdbImage>();

    public ViewHolderImageShop(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        rv = (RecyclerView) itemView.findViewById(R.id.rv);
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
}
