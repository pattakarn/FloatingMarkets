package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbUser {

    public String key;
    public FdbUser data;

    public WrapFdbUser() {
    }

    public WrapFdbUser(String key, FdbUser data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbUser getData() {
        return data;
    }

    public void setData(FdbUser data) {
        this.data = data;
    }
}
