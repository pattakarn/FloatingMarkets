package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbAward {

    public String key;
    public FdbAward data;

    public WrapFdbAward() {
    }

    public WrapFdbAward(String key, FdbAward data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbAward getData() {
        return data;
    }

    public void setData(FdbAward data) {
        this.data = data;
    }
}
