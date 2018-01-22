package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbStockList {

    public String key;
    public FdbStockList data;

    public WrapFdbStockList() {
    }

    public WrapFdbStockList(String key, FdbStockList data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbStockList getData() {
        return data;
    }

    public void setData(FdbStockList data) {
        this.data = data;
    }
}
