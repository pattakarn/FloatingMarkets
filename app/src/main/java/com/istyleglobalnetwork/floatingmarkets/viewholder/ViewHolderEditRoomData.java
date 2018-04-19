package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditRoomData extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colName;
    private ColAccountViewGroup colType;
    private ColAccountViewGroup colDetail;
    private ColAccountViewGroup colPrice;
    private ColAccountViewGroup colQuantity;

    public ViewHolderEditRoomData(View itemView) {
        super(itemView);
        this.colName = (ColAccountViewGroup) itemView.findViewById(R.id.col_name);
        this.colType = (ColAccountViewGroup) itemView.findViewById(R.id.col_type);
        this.colDetail = (ColAccountViewGroup) itemView.findViewById(R.id.col_detail);
        this.colPrice = (ColAccountViewGroup) itemView.findViewById(R.id.col_price);
        this.colQuantity = (ColAccountViewGroup) itemView.findViewById(R.id.col_quantity);
    }

    public ColAccountViewGroup getColName() {
        return colName;
    }

    public void setColName(ColAccountViewGroup colName) {
        this.colName = colName;
    }

    public ColAccountViewGroup getColType() {
        return colType;
    }

    public void setColType(ColAccountViewGroup colType) {
        this.colType = colType;
    }

    public ColAccountViewGroup getColDetail() {
        return colDetail;
    }

    public void setColDetail(ColAccountViewGroup colDetail) {
        this.colDetail = colDetail;
    }

    public ColAccountViewGroup getColPrice() {
        return colPrice;
    }

    public void setColPrice(ColAccountViewGroup colPrice) {
        this.colPrice = colPrice;
    }

    public ColAccountViewGroup getColQuantity() {
        return colQuantity;
    }

    public void setColQuantity(ColAccountViewGroup colQuantity) {
        this.colQuantity = colQuantity;
    }
}
