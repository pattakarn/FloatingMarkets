package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditShopAward extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colAward1;
    private ColAccountViewGroup colAward2;
    private ColAccountViewGroup colAward3;

    public ViewHolderEditShopAward(View itemView) {
        super(itemView);
        this.colAward1 = (ColAccountViewGroup) itemView.findViewById(R.id.col_award1);
        this.colAward2 = (ColAccountViewGroup) itemView.findViewById(R.id.col_award2);
        this.colAward3 = (ColAccountViewGroup) itemView.findViewById(R.id.col_award3);
    }

    public ColAccountViewGroup getColAward1() {
        return colAward1;
    }

    public void setColAward1(ColAccountViewGroup colAward1) {
        this.colAward1 = colAward1;
    }

    public ColAccountViewGroup getColAward2() {
        return colAward2;
    }

    public void setColAward2(ColAccountViewGroup colAward2) {
        this.colAward2 = colAward2;
    }

    public ColAccountViewGroup getColAward3() {
        return colAward3;
    }

    public void setColAward3(ColAccountViewGroup colAward3) {
        this.colAward3 = colAward3;
    }
}
