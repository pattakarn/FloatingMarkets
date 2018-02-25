package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditShopHead extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colMarket;
    private ColAccountViewGroup colZone;

    public ViewHolderEditShopHead(View itemView) {
        super(itemView);
        this.colMarket = (ColAccountViewGroup) itemView.findViewById(R.id.col_market);
        this.colZone = (ColAccountViewGroup) itemView.findViewById(R.id.col_zone);
    }

    public ColAccountViewGroup getColMarket() {
        return colMarket;
    }

    public void setColMarket(ColAccountViewGroup colMarket) {
        this.colMarket = colMarket;
    }

    public ColAccountViewGroup getColZone() {
        return colZone;
    }

    public void setColZone(ColAccountViewGroup colZone) {
        this.colZone = colZone;
    }

}
