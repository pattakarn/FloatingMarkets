package com.istyleglobalnetwork.floatingmarkets.FireDB;

public class FdbMessage {
    String userFrom;
    String userTo;
    String tokenTo;
    String title;
    String body;

    public FdbMessage(String userFrom, String userTo, String tokenTo, String title, String body) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.tokenTo = tokenTo;
        this.title = title;
        this.body = body;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getTokenTo() {
        return tokenTo;
    }

    public void setTokenTo(String tokenTo) {
        this.tokenTo = tokenTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
