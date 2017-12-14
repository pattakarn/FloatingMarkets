package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.pageradapter.PagerAdapterImage;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderImageNetwork extends RecyclerView.ViewHolder {

    private TextView tvName;
    ViewPager pager;

    public ViewHolderImageNetwork(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        pager = (ViewPager) itemView.findViewById(R.id.pager);

    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public void setPagerImage(int[] image){
        PagerAdapterImage adapter = new PagerAdapterImage(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), image);
        pager.setAdapter(adapter);
    }

}
