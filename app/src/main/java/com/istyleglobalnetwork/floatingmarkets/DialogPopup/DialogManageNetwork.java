package com.istyleglobalnetwork.floatingmarkets.DialogPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogManageNetwork {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;

    String title = "";
    String childName = "";

    public DialogManageNetwork(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


    public void Popup_ChangeText(final WrapFdbNetwork dataNetwork, final WrapFdbMarket dataMarket, String mode) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull editText = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

        switch (mode) {
            case "name":
                if (dataNetwork.getData() != null)
                    editText.setText(dataNetwork.getData().getNameNetwork());
                title = "Network Name";
                childName = "nameNetwork";
                break;
            case "owner":
                if (dataNetwork.getData() != null)
                    editText.setText(dataNetwork.getData().getOwner());
                title = "Owner Name";
                childName = "owner";
                break;
            case "phone":
                if (dataNetwork.getData() != null)
                    editText.setText(dataNetwork.getData().getPhone());
                editText.setNumberType(true);
                title = "Phone";
                childName = "phone";
                break;
            case "line":
                if (dataNetwork.getData() != null)
                    editText.setText(dataNetwork.getData().getLine());
                title = "Line ID";
                childName = "line";
                break;
            case "facebook":
                if (dataNetwork.getData() != null)
                    editText.setText(dataNetwork.getData().getFacebook());
                title = "Facebook";
                childName = "facebook";
                break;
            case "email":
                if (dataNetwork.getData() != null)
                    editText.setText(dataNetwork.getData().getEmail());
                title = "Email";
                childName = "email";
                break;
            case "detail":
                if (dataNetwork.getData() != null)
                    editText.setText(dataNetwork.getData().getDetail());
                title = "Detail";
                childName = "detail";
                break;

        }

        popupDialog.setView(layout_popup);
        popupDialog.setTitle(title);
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mMarketRef = mRootRef.child("market-network");
                DatabaseReference mNetworkRef = mRootRef.child("network");

                if (!childName.equals("")) {
                    if (childName.equals("name")) {
                        dataNetwork.getData().setNameNetwork(editText.getText());
                        mMarketRef.child(dataMarket.getKey()).child(dataNetwork.getKey()).setValue(dataNetwork.getData());
                        mNetworkRef.child(dataNetwork.getKey()).setValue(dataNetwork.getData());
                    } else {
                        mMarketRef.child(dataMarket.getKey()).child(dataNetwork.getKey()).child(childName).setValue(editText.getText());
                        mNetworkRef.child(dataNetwork.getKey()).child(childName).setValue(editText.getText());
                    }
                }


            }
        });
        popupDialog.create();
        popupDialog.show();
    }


    public void Popup_ChangeAward(final WrapFdbNetwork dataNetwork, final WrapFdbAward dataAward, String mode) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull editText = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

        if (dataAward != null)
            editText.setText(dataAward.getData().getNameAward());

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Award");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mItemRef = mRootRef.child("item-award");
                DatabaseReference mAwardRef = mRootRef.child("award");

                WrapFdbAward value = dataAward;

                if (value == null) {
                    String newKey = mAwardRef.push().getKey();
                    FdbAward data = new FdbAward();
                    data.setNameAward(editText.getText());
                    data.setItemID(dataNetwork.getKey());
                    value = new WrapFdbAward(newKey, data);
                } else {
                    value.getData().setNameAward(editText.getText());
                    value.getData().setItemID(dataNetwork.getKey());
                }

                mItemRef.child(dataNetwork.getKey()).child(value.getKey()).setValue(value.getData());
                mAwardRef.child(value.getKey()).setValue(value.getData());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }


}
