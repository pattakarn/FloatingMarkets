package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderImage extends RecyclerView.ViewHolder {

    public ViewHolderImage(View itemView) {
        super(itemView);

        int[] image = { R.drawable.ice1, R.drawable.ice2, R.drawable.ice3};

//        PagerAdapterImage adapter = new PagerAdapterImage(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), image);
//        ViewPager pager = (ViewPager) itemView.findViewById(R.id.pager);
//        pager.setAdapter(adapter);
    }

}
