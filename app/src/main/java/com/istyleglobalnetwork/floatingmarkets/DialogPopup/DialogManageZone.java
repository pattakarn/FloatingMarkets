package com.istyleglobalnetwork.floatingmarkets.DialogPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogManageZone {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;

    String title = "";
    String childName = "";

    public DialogManageZone(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


    public void Popup_ChangeText(final WrapFdbZone dataZone, final WrapFdbMarket dataMarket, String mode) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull editText = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

        switch (mode) {
            case "name":
                if (dataZone.getData() != null)
                    editText.setText(dataZone.getData().getNameZone());
                title = "Zone Name";
                childName = "nameZone";
                break;
            case "owner":
                if (dataZone.getData() != null)
                    editText.setText(dataZone.getData().getOwner());
                title = "Owner Name";
                childName = "owner";
                break;
            case "size":
                if (dataZone.getData() != null)
                    editText.setText(dataZone.getData().getSize());
                title = "Size";
                childName = "size";
                break;

        }

        popupDialog.setView(layout_popup);
        popupDialog.setTitle(title);
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mShopRef = mRootRef.child("market-zone");
                DatabaseReference mProductRef = mRootRef.child("zone");

                if (!childName.equals("")) {
                    if (childName.equals("name")){
                        dataZone.getData().setNameZone(editText.getText());
                        mShopRef.child(dataMarket.getKey()).child(dataZone.getKey()).setValue(dataZone.getData());
                        mProductRef.child(dataZone.getKey()).setValue(dataZone.getData());
                    } else {
                        mShopRef.child(dataMarket.getKey()).child(dataZone.getKey()).child(childName).setValue(editText.getText());
                        mProductRef.child(dataZone.getKey()).child(childName).setValue(editText.getText());
                    }
                }


            }
        });
        popupDialog.create();
        popupDialog.show();
    }


}
