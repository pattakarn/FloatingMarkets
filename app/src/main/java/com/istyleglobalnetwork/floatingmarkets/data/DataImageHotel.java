package com.istyleglobalnetwork.floatingmarkets.data;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataImageHotel {

    private int image;
    private String nameHotel;
    private String ratingHotel;

    public DataImageHotel(int image, String nameHotel, String ratingHotel) {
        this.image = image;
        this.nameHotel = nameHotel;
        this.ratingHotel = ratingHotel;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameHotel() {
        return nameHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    public String getRatingHotel() {
        return ratingHotel;
    }

    public void setRatingHotel(String ratingHotel) {
        this.ratingHotel = ratingHotel;
    }
}
