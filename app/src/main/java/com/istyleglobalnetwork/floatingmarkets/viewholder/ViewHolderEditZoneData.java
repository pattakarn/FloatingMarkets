package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditZoneData extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colName;
    private ColAccountViewGroup colOwner;
    private ColAccountViewGroup colSize;

    public ViewHolderEditZoneData(View itemView) {
        super(itemView);
        this.colName = (ColAccountViewGroup) itemView.findViewById(R.id.col_name);
        this.colOwner = (ColAccountViewGroup) itemView.findViewById(R.id.col_owner);
        this.colSize = (ColAccountViewGroup) itemView.findViewById(R.id.col_size);
    }

    public ColAccountViewGroup getColName() {
        return colName;
    }

    public void setColName(ColAccountViewGroup colName) {
        this.colName = colName;
    }

    public ColAccountViewGroup getColOwner() {
        return colOwner;
    }

    public void setColOwner(ColAccountViewGroup colOwner) {
        this.colOwner = colOwner;
    }

    public ColAccountViewGroup getColSize() {
        return colSize;
    }

    public void setColSize(ColAccountViewGroup colSize) {
        this.colSize = colSize;
    }
}
