package com.istyleglobalnetwork.floatingmarkets.FireDB;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Sung on 1/3/2018 AD.
 */

public class FBAnalytics {

    private FirebaseAnalytics mFirebaseAnalytics;
    private Context context;
    Bundle bundle;

    public FBAnalytics(Context context) {
        this.context = context;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        bundle = new Bundle();
    }

    public void addItemID(String itemID) {
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemID);
    }

    public void addItemName(String itemName) {
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
    }

    public void addPrice(double price) {
        bundle.putDouble(FirebaseAnalytics.Param.PRICE, price);
    }

    public void addQuantity(long quantity) {
        bundle.putLong(FirebaseAnalytics.Param.QUANTITY, quantity);
    }

    public void addItem(String itemID, String itemName, double price, long quantity) {
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemID);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);
        bundle.putDouble(FirebaseAnalytics.Param.PRICE, price);
        bundle.putLong(FirebaseAnalytics.Param.QUANTITY, quantity);
    }

    public void addUserID(String uid) {
        bundle.putString("USER_ID", uid);
    }

    public void addEmail(String email) {
        bundle.putString("EMAIL", email);
    }

    public void addUser(String uid, String email) {
        bundle.putString("USER_ID", uid);
        bundle.putString("EMAIL", email);
    }

    public void EventLogin() {
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }

    public void EventSignup() {
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);
    }

    public void EventViewItem() {
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
    }

    public void EventAddToCart() {
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, bundle);
    }

    public void EventCheckout() {
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.CHECKOUT_PROGRESS, bundle);
    }

public void EventAppOpen() {
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
    }

}
