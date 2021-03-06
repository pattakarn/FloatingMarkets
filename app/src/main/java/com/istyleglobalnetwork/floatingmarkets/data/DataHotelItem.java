package com.istyleglobalnetwork.floatingmarkets.data;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataHotelItem {

    private WrapFdbHotel itemHotel;
    private WrapFdbRoom itemRoom;
    private int image;

    public DataHotelItem(WrapFdbHotel itemHotel, WrapFdbRoom itemRoom, int image) {
        this.itemHotel = itemHotel;
        this.itemRoom = itemRoom;
        this.image = image;
    }

    public WrapFdbHotel getItemHotel() {
        return itemHotel;
    }

    public void setItemHotel(WrapFdbHotel itemHotel) {
        this.itemHotel = itemHotel;
    }

    public WrapFdbRoom getItemRoom() {
        return itemRoom;
    }

    public void setItemRoom(WrapFdbRoom itemRoom) {
        this.itemRoom = itemRoom;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
