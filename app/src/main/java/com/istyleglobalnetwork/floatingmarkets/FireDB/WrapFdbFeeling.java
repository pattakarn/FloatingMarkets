package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbFeeling {

    public String key;
    public FdbFeeling data;

    public WrapFdbFeeling() {
    }

    public WrapFdbFeeling(String key, FdbFeeling data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbFeeling getData() {
        return data;
    }

    public void setData(FdbFeeling data) {
        this.data = data;
    }
}
