package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbMarket {

    public String key;
    public FdbMarket data;

    public WrapFdbMarket() {
    }

    public WrapFdbMarket(String key, FdbMarket data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbMarket getData() {
        return data;
    }

    public void setData(FdbMarket data) {
        this.data = data;
    }
}
