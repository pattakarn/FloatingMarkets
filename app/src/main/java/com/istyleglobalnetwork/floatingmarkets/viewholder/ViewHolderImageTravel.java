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

public class ViewHolderImageTravel extends RecyclerView.ViewHolder {

    private TextView tvName;

    public ViewHolderImageTravel(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);

        int[] image = { R.drawable.ice1, R.drawable.ice2, R.drawable.ice3};

        PagerAdapterImage adapter = new PagerAdapterImage(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), image);
        ViewPager pager = (ViewPager) itemView.findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

}
