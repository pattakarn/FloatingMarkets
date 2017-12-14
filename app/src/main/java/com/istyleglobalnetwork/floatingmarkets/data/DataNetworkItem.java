package com.istyleglobalnetwork.floatingmarkets.data;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataNetworkItem {

    private int[] imageItem;
    private String nameItem;

    public DataNetworkItem(int[] imageItem, String nameItem) {
        this.imageItem = imageItem;
        this.nameItem = nameItem;
    }

    public int[] getImageItem() {
        return imageItem;
    }

    public void setImageItem(int[] imageItem) {
        this.imageItem = imageItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }
}
