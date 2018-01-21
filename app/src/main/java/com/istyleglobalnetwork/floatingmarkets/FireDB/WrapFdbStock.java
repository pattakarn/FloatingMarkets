package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbStock {

    public String key;
    public FdbStock data;

    public WrapFdbStock() {
    }

    public WrapFdbStock(String key, FdbStock data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbStock getData() {
        return data;
    }

    public void setData(FdbStock data) {
        this.data = data;
    }
}
