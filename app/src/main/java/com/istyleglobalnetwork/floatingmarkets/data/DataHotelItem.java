package com.istyleglobalnetwork.floatingmarkets.data;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataHotelItem {

    private int imageHotel;
    private String nameHotel;

    public DataHotelItem(int imageHotel, String nameHotel) {
        this.imageHotel = imageHotel;
        this.nameHotel = nameHotel;
    }

    public int getImageHotel() {
        return imageHotel;
    }

    public void setImageHotel(int imageHotel) {
        this.imageHotel = imageHotel;
    }

    public String getNameHotel() {
        return nameHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }
}
