package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditTravelLocation extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colAddress;
    private ColAccountViewGroup colLatitude;
    private ColAccountViewGroup colLongitude;

    public ViewHolderEditTravelLocation(View itemView) {
        super(itemView);
        this.colAddress = (ColAccountViewGroup) itemView.findViewById(R.id.col_address);
        this.colLatitude = (ColAccountViewGroup) itemView.findViewById(R.id.col_latitude);
        this.colLongitude = (ColAccountViewGroup) itemView.findViewById(R.id.col_longitude);
    }

    public ColAccountViewGroup getColAddress() {
        return colAddress;
    }

    public void setColAddress(ColAccountViewGroup colAddress) {
        this.colAddress = colAddress;
    }

    public ColAccountViewGroup getColLatitude() {
        return colLatitude;
    }

    public void setColLatitude(ColAccountViewGroup colLatitude) {
        this.colLatitude = colLatitude;
    }

    public ColAccountViewGroup getColLongitude() {
        return colLongitude;
    }

    public void setColLongitude(ColAccountViewGroup colLongitude) {
        this.colLongitude = colLongitude;
    }
}
