package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbHotel {

    public String key;
    public FdbHotel data;

    public WrapFdbHotel() {
    }

    public WrapFdbHotel(String key, FdbHotel data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbHotel getData() {
        return data;
    }

    public void setData(FdbHotel data) {
        this.data = data;
    }
}
