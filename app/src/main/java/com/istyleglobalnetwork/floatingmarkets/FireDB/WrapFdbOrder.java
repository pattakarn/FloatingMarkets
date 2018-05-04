package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbOrder {

    public String key;
    public FdbOrder data;

    public WrapFdbOrder() {
    }

    public WrapFdbOrder(String key, FdbOrder data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbOrder getData() {
        return data;
    }

    public void setData(FdbOrder data) {
        this.data = data;
    }

}
