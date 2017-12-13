package com.istyleglobalnetwork.floatingmarkets.data;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataProductItem {

    private int image;
    private String nameProduct;
    private String nameShop;

    public DataProductItem(String nameShop, int image, String nameProduct) {
        this.nameShop = nameShop;
        this.image = image;
        this.nameProduct = nameProduct;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
