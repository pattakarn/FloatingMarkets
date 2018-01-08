package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbZone {

    public String key;
    public FdbZone data;

    public WrapFdbZone() {
    }

    public WrapFdbZone(String key, FdbZone data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbZone getData() {
        return data;
    }

    public void setData(FdbZone data) {
        this.data = data;
    }
}
