package com.istyleglobalnetwork.floatingmarkets.DialogPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

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

    public void Popup_ChangeOpentime(final WrapFdbNetwork dataNetwork, final WrapFdbMarket dataMarket, final String day) {
        layout_popup = inflater.inflate(R.layout.dialog_opentime, null);
        final RadioButton rbOpen = (RadioButton) layout_popup.findViewById(R.id.radio_open);
        final RadioButton rbClose = (RadioButton) layout_popup.findViewById(R.id.radio_close);
        final EditTextNotNull etOpen = (EditTextNotNull) layout_popup.findViewById(R.id.open);
        final EditTextNotNull etClose = (EditTextNotNull) layout_popup.findViewById(R.id.close);

        rbOpen.setChecked(true);
        etOpen.setHint("เวลาเปิด");
        etClose.setHint("เวลาปิด");
        etOpen.setDateType(true);
        etClose.setDateType(true);

        rbOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    etOpen.setEnable(b);
                    etClose.setEnable(b);
                    etOpen.setHint("เวลาเปิด");
                    etClose.setHint("เวลาปิด");
                } else {
                    etOpen.setEnable(b);
                    etClose.setEnable(b);
                    etOpen.setHint("");
                    etClose.setHint("");
                }

            }
        });
//        rbClose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    etOpen.setEnable(b);
//                    etClose.setEnable(b);
//                    etOpen.setHint("");
//                    etClose.setHint("");
//                } else {
//                    etOpen.setEnable(b);
//                    etClose.setEnable(b);
//                    etOpen.setHint("เวลาเปิด");
//                    etClose.setHint("เวลาปิด");
//                }
//            }
//        });
//        rbOpen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                etOpen.setEnable(true);
//                etClose.setEnable(true);
//                etOpen.setHint("เวลาเปิด");
//                etClose.setHint("เวลาปิด");
//            }
//        });
//        rbClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                etOpen.setEnable(false);
//                etClose.setEnable(false);
//                etOpen.setHint("");
//                etClose.setHint("");
//            }
//        });

        String[] tempDate = null;
        switch (day) {
            case "monday":
                if (dataNetwork.getData() != null) {
                    if (dataNetwork.getData().getMonday() != null) {
                        if (dataNetwork.getData().getMonday().matches("(.*)-(.*)"))
                            tempDate = dataNetwork.getData().getMonday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Monday";
                break;
            case "tuesday":
                if (dataNetwork.getData() != null) {
                    if (dataNetwork.getData().getTuesday() != null) {
                        if (dataNetwork.getData().getTuesday().matches("(.*)-(.*)"))
                            tempDate = dataNetwork.getData().getTuesday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Tuesday";
                break;
            case "Wednesday":
                if (dataNetwork.getData() != null) {
                    if (dataNetwork.getData().getWednesday() != null) {
                        if (dataNetwork.getData().getWednesday().matches("(.*)-(.*)"))
                            tempDate = dataNetwork.getData().getWednesday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "wednesday";
                break;
            case "thursday":
                if (dataNetwork.getData() != null) {
                    if (dataNetwork.getData().getThursday() != null) {
                        if (dataNetwork.getData().getThursday().matches("(.*)-(.*)"))
                            tempDate = dataNetwork.getData().getThursday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Thursday";
                break;
            case "friday":
                if (dataNetwork.getData() != null) {
                    if (dataNetwork.getData().getFriday() != null) {
                        if (dataNetwork.getData().getFriday().matches("(.*)-(.*)"))
                            tempDate = dataNetwork.getData().getFriday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Friday";
                break;
            case "saturday":
                if (dataNetwork.getData() != null) {
                    if (dataNetwork.getData().getSaturday() != null) {
                        if (dataNetwork.getData().getSaturday().matches("(.*)-(.*)"))
                            tempDate = dataNetwork.getData().getSaturday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Saturday";
                break;
            case "sunday":
                if (dataNetwork.getData() != null) {
                    if (dataNetwork.getData().getSunday() != null) {
                        if (dataNetwork.getData().getSunday().matches("(.*)-(.*)"))
                            tempDate = dataNetwork.getData().getSunday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Sunday";
                break;

        }

        if (tempDate != null) {
            etOpen.setText(tempDate[0]);
            etClose.setText(tempDate[1]);
        } else {
            rbClose.setChecked(true);
        }


        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Opentime");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mMarketRef = mRootRef.child("market-network");
                DatabaseReference mNetworkRef = mRootRef.child("network");

                String date = "";
                if (rbOpen.isChecked())
                    date = etOpen.getText() + "-" + etClose.getText();
                else if (rbClose.isChecked())
                    date = "ปิด";

                Log.d("Popup_ChangeOpentime", "============================= " + day + " " + date);
                mMarketRef.child(dataMarket.getKey()).child(dataNetwork.getKey()).child(day).setValue(date);
                mNetworkRef.child(dataNetwork.getKey()).child(day).setValue(date);


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
