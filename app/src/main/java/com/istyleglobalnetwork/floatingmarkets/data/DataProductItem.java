package com.istyleglobalnetwork.floatingmarkets.data;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataProductItem {

    private WrapFdbShop itemShop;
    private WrapFdbProduct itemProduct;
    private int image;

    public DataProductItem(WrapFdbShop itemShop, WrapFdbProduct itemProduct, int image) {
        this.itemShop = itemShop;
        this.itemProduct = itemProduct;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public WrapFdbShop getItemShop() {
        return itemShop;
    }

    public void setItemShop(WrapFdbShop itemShop) {
        this.itemShop = itemShop;
    }

    public WrapFdbProduct getItemProduct() {
        return itemProduct;
    }

    public void setItemProduct(WrapFdbProduct itemProduct) {
        this.itemProduct = itemProduct;
    }
}
