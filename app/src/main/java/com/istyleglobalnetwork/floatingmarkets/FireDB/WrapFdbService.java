package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbService {

    public String key;
    public FdbService data;

    public WrapFdbService() {
    }

    public WrapFdbService(String key, FdbService data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbService getData() {
        return data;
    }

    public void setData(FdbService data) {
        this.data = data;
    }
}
