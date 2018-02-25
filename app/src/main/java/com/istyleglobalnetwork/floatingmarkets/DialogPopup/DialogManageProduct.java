package com.istyleglobalnetwork.floatingmarkets.DialogPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogManageProduct {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;


    public DialogManageProduct(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


    public void Popup_ChangeName(final WrapFdbProduct dataProduct, final WrapFdbShop dataShop) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull name = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

//        name.setHint("Product Name");
        if (dataProduct.getData() != null) {
            name.setText(dataProduct.getData().getNameProduct());
        }
        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Product Name");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mShopRef = mRootRef.child("shop-product");
                DatabaseReference mProductRef = mRootRef.child("product");

                mShopRef.child(dataShop.getKey()).child(dataProduct.getKey()).child("nameProduct").setValue(name.getText());
                mProductRef.child(dataProduct.getKey()).child("nameProduct").setValue(name.getText());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeType(final WrapFdbProduct dataProduct, final WrapFdbShop dataShop) {
        final String[] type = new String[]{"SAUCE", "APPETISERS", "SOUP OR CURRY", "NORTHEST FOOD", "DISHED",
                "MAIN COURSES", "NOODLES", "DESSERT", "JAPANESE FOOD", "ITLIAN FOOD", "SALAD", "PASTAS", "STEAK"};
        int checkItem = 0;
        if (dataProduct.getData() != null) {
            for (int i = 0; i < type.length; i++) {
                if (dataProduct.getData().getType() != null) {
                    if (dataProduct.getData().getType().equals(type[i])) {
                        checkItem = i;
                        break;
                    }
                }
            }
        }

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Category");
        popupDialog.setSingleChoiceItems(type, checkItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mShopRef = mRootRef.child("shop-product");
                DatabaseReference mProductRef = mRootRef.child("product");

                mShopRef.child(dataShop.getKey()).child(dataProduct.getKey()).child("type").setValue(type[i]);
                mProductRef.child(dataProduct.getKey()).child("type").setValue(type[i]);
                dialogInterface.dismiss();
            }
        });
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeDetail(final WrapFdbProduct dataProduct, final WrapFdbShop dataShop) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull detail = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

//        detail.setHint("Detail");
        if (dataProduct.getData() != null) {
            detail.setText(dataProduct.getData().getDescription());
        }
        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Detail");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mShopRef = mRootRef.child("shop-product");
                DatabaseReference mProductRef = mRootRef.child("product");

                mShopRef.child(dataShop.getKey()).child(dataProduct.getKey()).child("description").setValue(detail.getText());
                mProductRef.child(dataProduct.getKey()).child("description").setValue(detail.getText());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangePrice(final WrapFdbProduct dataProduct, final WrapFdbShop dataShop) {
        layout_popup = inflater.inflate(R.layout.dialog_phone, null);
        final EditTextNotNull price = (EditTextNotNull) layout_popup.findViewById(R.id.phone);

        if (dataProduct.getData() != null) {
            price.setText(dataProduct.getData().getPrice() + "");
        }
        price.setNumberType(true);

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Price");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mShopRef = mRootRef.child("shop-product");
                DatabaseReference mProductRef = mRootRef.child("product");

                mShopRef.child(dataShop.getKey()).child(dataProduct.getKey()).child("price").setValue(Integer.parseInt(price.getText()));
                mProductRef.child(dataProduct.getKey()).child("price").setValue(Integer.parseInt(price.getText()));


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

}
