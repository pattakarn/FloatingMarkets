package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbBooking {

    public String key;
    public FdbBooking data;

    public WrapFdbBooking() {
    }

    public WrapFdbBooking(String key, FdbBooking data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbBooking getData() {
        return data;
    }

    public void setData(FdbBooking data) {
        this.data = data;
    }
}
