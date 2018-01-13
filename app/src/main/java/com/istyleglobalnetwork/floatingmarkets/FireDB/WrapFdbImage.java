package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbImage {

    public String key;
    public FdbImage data;

    public WrapFdbImage() {
    }

    public WrapFdbImage(String key, FdbImage data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbImage getData() {
        return data;
    }

    public void setData(FdbImage data) {
        this.data = data;
    }
}
