package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbProduct {

    public String key;
    public FdbProduct data;

    public WrapFdbProduct() {
    }

    public WrapFdbProduct(String key, FdbProduct data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbProduct getData() {
        return data;
    }

    public void setData(FdbProduct data) {
        this.data = data;
    }
}
