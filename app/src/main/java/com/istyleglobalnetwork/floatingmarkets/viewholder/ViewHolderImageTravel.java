package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderImageTravel extends RecyclerView.ViewHolder {

    private TextView tvName;

    public ViewHolderImageTravel(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);

        int[] image = { R.drawable.tra_item_1, R.drawable.tra_item_2, R.drawable.tra_item_3, R.drawable.tra_item_4};

//        PagerAdapterImage adapter = new PagerAdapterImage(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), image);
//        ViewPager pager = (ViewPager) itemView.findViewById(R.id.pager);
//        pager.setAdapter(adapter);
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

}
