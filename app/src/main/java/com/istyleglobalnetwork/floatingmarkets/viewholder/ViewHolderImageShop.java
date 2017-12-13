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

public class ViewHolderImageShop extends RecyclerView.ViewHolder {

    private TextView tvName;

    public ViewHolderImageShop(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);

        int[] image = { R.drawable.talad1, R.drawable.talad2, R.drawable.talad3};

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
