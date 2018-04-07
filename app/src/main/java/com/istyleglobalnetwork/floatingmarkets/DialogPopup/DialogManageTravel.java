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
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbTravel;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogManageTravel {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;

    String title = "";
    String childName = "";

    public DialogManageTravel(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


    public void Popup_ChangeText(final WrapFdbTravel dataTravel, final WrapFdbMarket dataMarket, String mode) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull editText = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

        switch (mode) {
            case "name":
                if (dataTravel.getData() != null)
                    editText.setText(dataTravel.getData().getNameTravel());
                title = "Travel Name";
                childName = "nameTravel";
                break;
            case "owner":
                if (dataTravel.getData() != null)
                    editText.setText(dataTravel.getData().getOwner());
                title = "Owner Name";
                childName = "owner";
                break;
            case "phone":
                if (dataTravel.getData() != null)
                    editText.setText(dataTravel.getData().getPhone());
                editText.setNumberType(true);
                title = "Phone";
                childName = "phone";
                break;
            case "line":
                if (dataTravel.getData() != null)
                    editText.setText(dataTravel.getData().getLine());
                title = "Line ID";
                childName = "line";
                break;
            case "facebook":
                if (dataTravel.getData() != null)
                    editText.setText(dataTravel.getData().getFacebook());
                title = "Facebook";
                childName = "facebook";
                break;
            case "email":
                if (dataTravel.getData() != null)
                    editText.setText(dataTravel.getData().getEmail());
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
                DatabaseReference mMarketRef = mRootRef.child("market-travel");
                DatabaseReference mTravelRef = mRootRef.child("travel");

                if (!childName.equals("")) {
                    if (childName.equals("name")) {
                        dataTravel.getData().setNameTravel(editText.getText());
                        mMarketRef.child(dataMarket.getKey()).child(dataTravel.getKey()).setValue(dataTravel.getData());
                        mTravelRef.child(dataTravel.getKey()).setValue(dataTravel.getData());
                    } else {
                        mMarketRef.child(dataMarket.getKey()).child(dataTravel.getKey()).child(childName).setValue(editText.getText());
                        mTravelRef.child(dataTravel.getKey()).child(childName).setValue(editText.getText());
                    }
                }


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeOpentime(final WrapFdbTravel dataTravel, final WrapFdbMarket dataMarket, final String day) {
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
                if (dataTravel.getData() != null) {
                    if (dataTravel.getData().getMonday() != null) {
                        if (dataTravel.getData().getMonday().matches("(.*)-(.*)"))
                            tempDate = dataTravel.getData().getMonday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Monday";
                break;
            case "tuesday":
                if (dataTravel.getData() != null) {
                    if (dataTravel.getData().getTuesday() != null) {
                        if (dataTravel.getData().getTuesday().matches("(.*)-(.*)"))
                            tempDate = dataTravel.getData().getTuesday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Tuesday";
                break;
            case "Wednesday":
                if (dataTravel.getData() != null) {
                    if (dataTravel.getData().getWednesday() != null) {
                        if (dataTravel.getData().getWednesday().matches("(.*)-(.*)"))
                            tempDate = dataTravel.getData().getWednesday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "wednesday";
                break;
            case "thursday":
                if (dataTravel.getData() != null) {
                    if (dataTravel.getData().getThursday() != null) {
                        if (dataTravel.getData().getThursday().matches("(.*)-(.*)"))
                            tempDate = dataTravel.getData().getThursday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Thursday";
                break;
            case "friday":
                if (dataTravel.getData() != null) {
                    if (dataTravel.getData().getFriday() != null) {
                        if (dataTravel.getData().getFriday().matches("(.*)-(.*)"))
                            tempDate = dataTravel.getData().getFriday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Friday";
                break;
            case "saturday":
                if (dataTravel.getData() != null) {
                    if (dataTravel.getData().getSaturday() != null) {
                        if (dataTravel.getData().getSaturday().matches("(.*)-(.*)"))
                            tempDate = dataTravel.getData().getSaturday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Saturday";
                break;
            case "sunday":
                if (dataTravel.getData() != null) {
                    if (dataTravel.getData().getSunday() != null) {
                        if (dataTravel.getData().getSunday().matches("(.*)-(.*)"))
                            tempDate = dataTravel.getData().getSunday().split("-");
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
                DatabaseReference mMarketRef = mRootRef.child("market-travel");
                DatabaseReference mTravelRef = mRootRef.child("travel");

                String date = "";
                if (rbOpen.isChecked())
                    date = etOpen.getText() + "-" + etClose.getText();
                else if (rbClose.isChecked())
                    date = "ปิด";

                Log.d("Popup_ChangeOpentime", "============================= " + day + " " + date);
                mMarketRef.child(dataMarket.getKey()).child(dataTravel.getKey()).child(day).setValue(date);
                mTravelRef.child(dataTravel.getKey()).child(day).setValue(date);


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeAward(final WrapFdbTravel dataTravel, final WrapFdbAward dataAward, String mode) {
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
                    data.setItemID(dataTravel.getKey());
                    value = new WrapFdbAward(newKey, data);
                } else {
                    value.getData().setNameAward(editText.getText());
                    value.getData().setItemID(dataTravel.getKey());
                }

                mItemRef.child(dataTravel.getKey()).child(value.getKey()).setValue(value.getData());
                mAwardRef.child(value.getKey()).setValue(value.getData());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }


}
