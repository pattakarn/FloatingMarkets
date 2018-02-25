package com.istyleglobalnetwork.floatingmarkets.data;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 25/2/2018 AD.
 */

public class DataShopProductOrder {

    WrapFdbShop shop;
    List<DataProductOrder> productOrders;
    int[] totalPrice;
    int[] totalQty;

    public DataShopProductOrder(WrapFdbShop shop) {
        this.shop = shop;
        productOrders = new ArrayList<DataProductOrder>();
    }

    public DataShopProductOrder(WrapFdbShop shop, List<DataProductOrder> productOrders) {
        this.shop = shop;
        this.productOrders = productOrders;
    }

    public WrapFdbShop getShop() {
        return shop;
    }

    public void setShop(WrapFdbShop shop) {
        this.shop = shop;
    }

    public List<DataProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<DataProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    public void setSizeArrayInt(int size){
        totalPrice = new int[size];
        totalQty = new int[size];

        for (int i = 0;i<size; i++){
            totalPrice[i] = 0;
            totalQty[i] = 0;
        }
    }

    public int getTotalPrice(int index) {
        return totalPrice[index];
    }

    public int getTotalQty(int index) {
        return totalQty[index];
    }

    public void addTotalPrice(int index, int value){
        totalPrice[index] += value;
    }

    public void addTotalQty(int index, int value){
        totalQty[index] += value;
    }
}
