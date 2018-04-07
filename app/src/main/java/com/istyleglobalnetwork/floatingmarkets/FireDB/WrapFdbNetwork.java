package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbNetwork {

    public String key;
    public FdbNetwork data;

    public WrapFdbNetwork() {
    }

    public WrapFdbNetwork(String key, FdbNetwork data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbNetwork getData() {
        return data;
    }

    public void setData(FdbNetwork data) {
        this.data = data;
    }
}
