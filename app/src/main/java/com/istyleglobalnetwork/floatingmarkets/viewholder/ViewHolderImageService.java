package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderImageService extends RecyclerView.ViewHolder {

    private TextView tvName;
    ViewPager pager;

    public ViewHolderImageService(View itemView) {
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
//        PagerAdapterImage adapter = new PagerAdapterImage(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), image);
//        pager.setAdapter(adapter);
    }

}
