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
                    if (childName.equals("name")) {
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

    public void Popup_ChangeOpentime(final WrapFdbShop dataShop, final WrapFdbZone dataZone, final String day) {
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
                if (dataShop.getData() != null) {
                    if (dataShop.getData().getMonday() != null) {
                        if (dataShop.getData().getMonday().matches("(.*)-(.*)"))
                            tempDate = dataShop.getData().getMonday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Monday";
                break;
            case "tuesday":
                if (dataShop.getData() != null) {
                    if (dataShop.getData().getTuesday() != null) {
                        if (dataShop.getData().getTuesday().matches("(.*)-(.*)"))
                            tempDate = dataShop.getData().getTuesday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Tuesday";
                break;
            case "Wednesday":
                if (dataShop.getData() != null) {
                    if (dataShop.getData().getWednesday() != null) {
                        if (dataShop.getData().getWednesday().matches("(.*)-(.*)"))
                            tempDate = dataShop.getData().getWednesday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "wednesday";
                break;
            case "thursday":
                if (dataShop.getData() != null) {
                    if (dataShop.getData().getThursday() != null) {
                        if (dataShop.getData().getThursday().matches("(.*)-(.*)"))
                            tempDate = dataShop.getData().getThursday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Thursday";
                break;
            case "friday":
                if (dataShop.getData() != null) {
                    if (dataShop.getData().getFriday() != null) {
                        if (dataShop.getData().getFriday().matches("(.*)-(.*)"))
                            tempDate = dataShop.getData().getFriday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Friday";
                break;
            case "saturday":
                if (dataShop.getData() != null) {
                    if (dataShop.getData().getSaturday() != null) {
                        if (dataShop.getData().getSaturday().matches("(.*)-(.*)"))
                            tempDate = dataShop.getData().getSaturday().split("-");
                        else
                            rbClose.setChecked(true);
                    }
                }
                title = "Saturday";
                break;
            case "sunday":
                if (dataShop.getData() != null) {
                    if (dataShop.getData().getSunday() != null) {
                        if (dataShop.getData().getSunday().matches("(.*)-(.*)"))
                            tempDate = dataShop.getData().getSunday().split("-");
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
                DatabaseReference mShopRef = mRootRef.child("zone-shop");
                DatabaseReference mProductRef = mRootRef.child("shop");

                String date = "";
                if (rbOpen.isChecked())
                    date = etOpen.getText() + "-" + etClose.getText();
                else if (rbClose.isChecked())
                    date = "ปิด";

                Log.d("Popup_ChangeOpentime", "============================= " + day + " " + date);
                mShopRef.child(dataZone.getKey()).child(dataShop.getKey()).child(day).setValue(date);
                mProductRef.child(dataShop.getKey()).child(day).setValue(date);


            }
        });
        popupDialog.create();
        popupDialog.show();
    }


}
