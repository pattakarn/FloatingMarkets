package com.istyleglobalnetwork.floatingmarkets.data;

/**
 * Created by Sung on 13/12/2017 AD.
 */

public class DataCommentItem {

    private String title;
    private String detail;
    private String rating;

    public DataCommentItem(String title, String detail, String rating) {
        this.title = title;
        this.detail = detail;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
