package com.istyleglobalnetwork.floatingmarkets.data;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataMainMenu {

    Class<?> cItem;
    String nameItem;

    public DataMainMenu(Class<?> cItem, String nameItem) {
        this.cItem = cItem;
        this.nameItem = nameItem;
    }

    public Class<?> getcItem() {
        return cItem;
    }

    public void setcItem(Class<?> cItem) {
        this.cItem = cItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }
}
