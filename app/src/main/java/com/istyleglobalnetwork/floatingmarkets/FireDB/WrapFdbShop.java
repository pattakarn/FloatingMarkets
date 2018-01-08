package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbShop {

    public String key;
    public FdbShop data;

    public WrapFdbShop() {
    }

    public WrapFdbShop(String key, FdbShop data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbShop getData() {
        return data;
    }

    public void setData(FdbShop data) {
        this.data = data;
    }
}
