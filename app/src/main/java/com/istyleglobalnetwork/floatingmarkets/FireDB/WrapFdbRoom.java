package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbRoom {

    public String key;
    public FdbRoom data;

    public WrapFdbRoom() {
    }

    public WrapFdbRoom(String key, FdbRoom data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbRoom getData() {
        return data;
    }

    public void setData(FdbRoom data) {
        this.data = data;
    }
}
