package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbTravel {

    public String key;
    public FdbTravel data;

    public WrapFdbTravel() {
    }

    public WrapFdbTravel(String key, FdbTravel data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbTravel getData() {
        return data;
    }

    public void setData(FdbTravel data) {
        this.data = data;
    }
}
