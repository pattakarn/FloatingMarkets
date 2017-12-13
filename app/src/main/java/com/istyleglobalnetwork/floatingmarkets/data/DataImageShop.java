package com.istyleglobalnetwork.floatingmarkets.data;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataImageShop {

    private int image;
    private String nameShop;

    public DataImageShop(int image, String nameShop) {
        this.image = image;
        this.nameShop = nameShop;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }
}
