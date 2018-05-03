package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbBookingList {

    public String key;
    public FdbBookingList data;

    public WrapFdbBookingList() {
    }

    public WrapFdbBookingList(String key, FdbBookingList data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbBookingList getData() {
        return data;
    }

    public void setData(FdbBookingList data) {
        this.data = data;
    }
}
