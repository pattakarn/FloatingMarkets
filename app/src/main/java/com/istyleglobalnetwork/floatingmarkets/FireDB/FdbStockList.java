package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 13/12/2017 AD.
 */

@Parcel
public class FdbStockList {

    int quantity;
    String mark;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
