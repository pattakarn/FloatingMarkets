package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 5/23/2017 AD.
 */
@Parcel
public class WrapFdbComment {

    public String key;
    public FdbComment data;

    public WrapFdbComment() {
    }

    public WrapFdbComment(String key, FdbComment data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FdbComment getData() {
        return data;
    }

    public void setData(FdbComment data) {
        this.data = data;
    }
}
