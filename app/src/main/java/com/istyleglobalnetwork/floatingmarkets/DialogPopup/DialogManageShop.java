package com.istyleglobalnetwork.floatingmarkets.DialogPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogManageShop {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;

    String title = "";
    String childName = "";

    public DialogManageShop(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


    public void Popup_ChangeText(final WrapFdbShop dataShop, final WrapFdbZone dataZone, String mode) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull editText = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

        switch (mode) {
            case "name":
                if (dataShop.getData() != null)
                    editText.setText(dataShop.getData().getNameShop());
                title = "Shop Name";
                childName = "nameShop";
                break;
            case "owner":
                if (dataShop.getData() != null)
                    editText.setText(dataShop.getData().getOwner());
                title = "Owner Name";
                childName = "owner";
                break;
            case "phone":
                if (dataShop.getData() != null)
                    editText.setText(dataShop.getData().getPhone());
                editText.setNumberType(true);
                title = "Phone";
                childName = "phone";
                break;
            case "line":
                if (dataShop.getData() != null)
                    editText.setText(dataShop.getData().getLine());
                title = "Line ID";
                childName = "line";
                break;
            case "facebook":
                if (dataShop.getData() != null)
                    editText.setText(dataShop.getData().getFacebook());
                title = "Facebook";
                childName = "facebook";
                break;
            case "email":
                if (dataShop.getData() != null)
                    editText.setText(dataShop.getData().getEmail());
                title = "Email";
                childName = "email";
                break;

        }

        popupDialog.setView(layout_popup);
        popupDialog.setTitle(title);
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mShopRef = mRootRef.child("zone-shop");
                DatabaseReference mProductRef = mRootRef.child("shop");

                if (!childName.equals("")) {
                    if (childName.equals("name")){
                        dataShop.getData().setNameShop(editText.getText());
                        mShopRef.child(dataZone.getKey()).child(dataShop.getKey()).setValue(dataShop.getData());
                        mProductRef.child(dataShop.getKey()).setValue(dataShop.getData());
                    } else {
                        mShopRef.child(dataZone.getKey()).child(dataShop.getKey()).child(childName).setValue(editText.getText());
                        mProductRef.child(dataShop.getKey()).child(childName).setValue(editText.getText());
                    }
                }


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeOpentime(final WrapFdbShop dataShop, final WrapFdbZone dataZone, String day) {
        layout_popup = inflater.inflate(R.layout.dialog_opentime, null);

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Opentime");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

//                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
//                DatabaseReference mShopRef = mRootRef.child("shop-product");
//                DatabaseReference mProductRef = mRootRef.child("product");
//
//                dataProduct.getData().setNameProduct(name.getText());
//
//                mShopRef.child(dataShop.getKey()).child(dataProduct.getKey()).setValue(dataProduct.getData());
//                mProductRef.child(dataProduct.getKey()).setValue(dataProduct.getData());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }


}
