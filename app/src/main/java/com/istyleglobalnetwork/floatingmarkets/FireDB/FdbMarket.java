package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 13/12/2017 AD.
 */

@Parcel
public class FdbMarket {

    private String nameMarket;
    private String image;

    public String getNameMarket() {
        return nameMarket;
    }

    public void setNameMarket(String nameMarket) {
        this.nameMarket = nameMarket;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
