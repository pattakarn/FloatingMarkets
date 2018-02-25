package com.istyleglobalnetwork.floatingmarkets.data;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 25/2/2018 AD.
 */

public class DataProductOrder {

    WrapFdbProduct product;
    List<WrapFdbOrder> order;
    int[] totalPrice;
    int[] totalQty;


    public DataProductOrder(WrapFdbProduct product) {
        this.product = product;
        order = new ArrayList<WrapFdbOrder>();
    }

    public DataProductOrder(WrapFdbProduct product, List<WrapFdbOrder> order) {
        this.product = product;
        this.order = order;
    }

    public WrapFdbProduct getProduct() {
        return product;
    }

    public void setProduct(WrapFdbProduct product) {
        this.product = product;
    }

    public List<WrapFdbOrder> getOrder() {
        return order;
    }

    public void setOrder(List<WrapFdbOrder> order) {
        this.order = order;
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
