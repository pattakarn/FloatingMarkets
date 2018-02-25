package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditZoneHead extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colMarket;

    public ViewHolderEditZoneHead(View itemView) {
        super(itemView);
        this.colMarket = (ColAccountViewGroup) itemView.findViewById(R.id.col_market);
    }

    public ColAccountViewGroup getColMarket() {
        return colMarket;
    }

    public void setColMarket(ColAccountViewGroup colMarket) {
        this.colMarket = colMarket;
    }


}
