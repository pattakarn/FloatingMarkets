package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditRoomOption extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colSize;
    private ColAccountViewGroup colGuest;
    private ColAccountViewGroup colBed;
    private ColAccountViewGroup colBreakfast;
    private ColAccountViewGroup colWifi;
    private ColAccountViewGroup colAir;
    private ColAccountViewGroup colSmoke;
    private ColAccountViewGroup colCar;

    public ViewHolderEditRoomOption(View itemView) {
        super(itemView);
        this.colSize = (ColAccountViewGroup) itemView.findViewById(R.id.col_size);
        this.colGuest = (ColAccountViewGroup) itemView.findViewById(R.id.col_guest);
        this.colBed = (ColAccountViewGroup) itemView.findViewById(R.id.col_bed);
        this.colBreakfast = (ColAccountViewGroup) itemView.findViewById(R.id.col_breakfast);
        this.colWifi = (ColAccountViewGroup) itemView.findViewById(R.id.col_wifi);
        this.colAir = (ColAccountViewGroup) itemView.findViewById(R.id.col_air);
        this.colSmoke = (ColAccountViewGroup) itemView.findViewById(R.id.col_smoke);
        this.colCar = (ColAccountViewGroup) itemView.findViewById(R.id.col_car);
    }

    public ColAccountViewGroup getColSize() {
        return colSize;
    }

    public void setColSize(ColAccountViewGroup colSize) {
        this.colSize = colSize;
    }

    public ColAccountViewGroup getColGuest() {
        return colGuest;
    }

    public void setColGuest(ColAccountViewGroup colGuest) {
        this.colGuest = colGuest;
    }

    public ColAccountViewGroup getColBed() {
        return colBed;
    }

    public void setColBed(ColAccountViewGroup colBed) {
        this.colBed = colBed;
    }

    public ColAccountViewGroup getColBreakfast() {
        return colBreakfast;
    }

    public void setColBreakfast(ColAccountViewGroup colBreakfast) {
        this.colBreakfast = colBreakfast;
    }

    public ColAccountViewGroup getColWifi() {
        return colWifi;
    }

    public void setColWifi(ColAccountViewGroup colWifi) {
        this.colWifi = colWifi;
    }

    public ColAccountViewGroup getColAir() {
        return colAir;
    }

    public void setColAir(ColAccountViewGroup colAir) {
        this.colAir = colAir;
    }

    public ColAccountViewGroup getColSmoke() {
        return colSmoke;
    }

    public void setColSmoke(ColAccountViewGroup colSmoke) {
        this.colSmoke = colSmoke;
    }

    public ColAccountViewGroup getColCar() {
        return colCar;
    }

    public void setColCar(ColAccountViewGroup colCar) {
        this.colCar = colCar;
    }
}
