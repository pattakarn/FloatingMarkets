package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditShopOpentime extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colMonday;
    private ColAccountViewGroup colTuesday;
    private ColAccountViewGroup colWednesday;
    private ColAccountViewGroup colThursday;
    private ColAccountViewGroup colFriday;
    private ColAccountViewGroup colSaturday;
    private ColAccountViewGroup colSunday;

    public ViewHolderEditShopOpentime(View itemView) {
        super(itemView);
        this.colMonday = (ColAccountViewGroup) itemView.findViewById(R.id.col_monday);
        this.colTuesday = (ColAccountViewGroup) itemView.findViewById(R.id.col_tuesday);
        this.colWednesday = (ColAccountViewGroup) itemView.findViewById(R.id.col_wednesday);
        this.colThursday = (ColAccountViewGroup) itemView.findViewById(R.id.col_thursday);
        this.colFriday = (ColAccountViewGroup) itemView.findViewById(R.id.col_friday);
        this.colSaturday = (ColAccountViewGroup) itemView.findViewById(R.id.col_saturday);
        this.colSunday = (ColAccountViewGroup) itemView.findViewById(R.id.col_sunday);
    }

    public ColAccountViewGroup getColMonday() {
        return colMonday;
    }

    public void setColMonday(ColAccountViewGroup colMonday) {
        this.colMonday = colMonday;
    }

    public ColAccountViewGroup getColTuesday() {
        return colTuesday;
    }

    public void setColTuesday(ColAccountViewGroup colTuesday) {
        this.colTuesday = colTuesday;
    }

    public ColAccountViewGroup getColWednesday() {
        return colWednesday;
    }

    public void setColWednesday(ColAccountViewGroup colWednesday) {
        this.colWednesday = colWednesday;
    }

    public ColAccountViewGroup getColThursday() {
        return colThursday;
    }

    public void setColThursday(ColAccountViewGroup colThursday) {
        this.colThursday = colThursday;
    }

    public ColAccountViewGroup getColFriday() {
        return colFriday;
    }

    public void setColFriday(ColAccountViewGroup colFriday) {
        this.colFriday = colFriday;
    }

    public ColAccountViewGroup getColSaturday() {
        return colSaturday;
    }

    public void setColSaturday(ColAccountViewGroup colSaturday) {
        this.colSaturday = colSaturday;
    }

    public ColAccountViewGroup getColSunday() {
        return colSunday;
    }

    public void setColSunday(ColAccountViewGroup colSunday) {
        this.colSunday = colSunday;
    }
}
