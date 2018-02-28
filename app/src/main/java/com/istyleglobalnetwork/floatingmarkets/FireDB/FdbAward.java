package com.istyleglobalnetwork.floatingmarkets.FireDB;

import org.parceler.Parcel;

/**
 * Created by Sung on 13/12/2017 AD.
 */

@Parcel
public class FdbAward {

    String nameAward;
    String give;
    String itemID;

    public String getNameAward() {
        return nameAward;
    }

    public void setNameAward(String nameAward) {
        this.nameAward = nameAward;
    }

    public String getGive() {
        return give;
    }

    public void setGive(String give) {
        this.give = give;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
}
