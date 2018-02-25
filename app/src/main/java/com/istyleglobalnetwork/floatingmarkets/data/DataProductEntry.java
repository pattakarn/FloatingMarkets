package com.istyleglobalnetwork.floatingmarkets.data;

import com.github.mikephil.charting.data.Entry;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 25/2/2018 AD.
 */

public class DataProductEntry {

    WrapFdbProduct product;
    List<Entry> entry;

    public DataProductEntry(WrapFdbProduct product) {
        this.product = product;
        entry = new ArrayList<Entry>();
    }

    public DataProductEntry(WrapFdbProduct product, List<Entry> order) {
        this.product = product;
        this.entry = order;
    }

    public WrapFdbProduct getProduct() {
        return product;
    }

    public void setProduct(WrapFdbProduct product) {
        this.product = product;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
