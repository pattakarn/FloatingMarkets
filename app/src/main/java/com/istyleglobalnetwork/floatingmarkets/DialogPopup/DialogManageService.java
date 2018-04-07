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
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbService;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogManageService {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;

    String title = "";
    String childName = "";

    public DialogManageService(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


    public void Popup_ChangeText(final WrapFdbService dataService, final WrapFdbMarket dataMarket, String mode) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull editText = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

        switch (mode) {
            case "name":
                if (dataService.getData() != null)
                    editText.setText(dataService.getData().getNameService());
                title = "Service Name";
                childName = "nameService";
                break;
            case "owner":
                if (dataService.getData() != null)
                    editText.setText(dataService.getData().getOwner());
                title = "Owner Name";
                childName = "owner";
                break;
            case "phone":
                if (dataService.getData() != null)
                    editText.setText(dataService.getData().getPhone());
                editText.setNumberType(true);
                title = "Phone";
                childName = "phone";
                break;
            case "line":
                if (dataService.getData() != null)
                    editText.setText(dataService.getData().getLine());
                title = "Line ID";
                childName = "line";
                break;
            case "facebook":
                if (dataService.getData() != null)
                    editText.setText(dataService.getData().getFacebook());
                title = "Facebook";
                childName = "facebook";
                break;
            case "email":
                if (dataService.getData() != null)
                    editText.setText(dataService.getData().getEmail());
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
                DatabaseReference mMarketRef = mRootRef.child("market-service");
                DatabaseReference mServiceRef = mRootRef.child("service");

                if (!childName.equals("")) {
                    if (childName.equals("name")) {
                        dataService.getData().setNameService(editText.getText());
                        mMarketRef.child(dataMarket.getKey()).child(dataService.getKey()).setValue(dataService.getData());
                        mServiceRef.child(dataService.getKey()).setValue(dataService.getData());
                    } else {
                        mMarketRef.child(dataMarket.getKey()).child(dataService.getKey()).child(childName).setValue(editText.getText());
                        mServiceRef.child(dataService.getKey()).child(childName).setValue(editText.getText());
                    }
                }


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeOpentime(final WrapFdbService dataService, final WrapFdbMarket dataMarket, final String day) {
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
                if (dataService.getData() != null) {
                    if (dataService.getData().getMonday() != null) {
                        if (dataService.getData().getMonday().matches("(.*)-(.*)"))
                            tempDate = dataService.getData().getMonday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Monday";
                break;
            case "tuesday":
                if (dataService.getData() != null) {
                    if (dataService.getData().getTuesday() != null) {
                        if (dataService.getData().getTuesday().matches("(.*)-(.*)"))
                            tempDate = dataService.getData().getTuesday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Tuesday";
                break;
            case "Wednesday":
                if (dataService.getData() != null) {
                    if (dataService.getData().getWednesday() != null) {
                        if (dataService.getData().getWednesday().matches("(.*)-(.*)"))
                            tempDate = dataService.getData().getWednesday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "wednesday";
                break;
            case "thursday":
                if (dataService.getData() != null) {
                    if (dataService.getData().getThursday() != null) {
                        if (dataService.getData().getThursday().matches("(.*)-(.*)"))
                            tempDate = dataService.getData().getThursday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Thursday";
                break;
            case "friday":
                if (dataService.getData() != null) {
                    if (dataService.getData().getFriday() != null) {
                        if (dataService.getData().getFriday().matches("(.*)-(.*)"))
                            tempDate = dataService.getData().getFriday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Friday";
                break;
            case "saturday":
                if (dataService.getData() != null) {
                    if (dataService.getData().getSaturday() != null) {
                        if (dataService.getData().getSaturday().matches("(.*)-(.*)"))
                            tempDate = dataService.getData().getSaturday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Saturday";
                break;
            case "sunday":
                if (dataService.getData() != null) {
                    if (dataService.getData().getSunday() != null) {
                        if (dataService.getData().getSunday().matches("(.*)-(.*)"))
                            tempDate = dataService.getData().getSunday().split("-");
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
                DatabaseReference mMarketRef = mRootRef.child("market-service");
                DatabaseReference mServiceRef = mRootRef.child("service");

                String date = "";
                if (rbOpen.isChecked())
                    date = etOpen.getText() + "-" + etClose.getText();
                else if (rbClose.isChecked())
                    date = "ปิด";

                Log.d("Popup_ChangeOpentime", "============================= " + day + " " + date);
                mMarketRef.child(dataMarket.getKey()).child(dataService.getKey()).child(day).setValue(date);
                mServiceRef.child(dataService.getKey()).child(day).setValue(date);


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeAward(final WrapFdbService dataService, final WrapFdbAward dataAward, String mode) {
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
                    data.setItemID(dataService.getKey());
                    value = new WrapFdbAward(newKey, data);
                } else {
                    value.getData().setNameAward(editText.getText());
                    value.getData().setItemID(dataService.getKey());
                }

                mItemRef.child(dataService.getKey()).child(value.getKey()).setValue(value.getData());
                mAwardRef.child(value.getKey()).setValue(value.getData());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }


}
