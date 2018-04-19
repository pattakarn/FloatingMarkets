package com.istyleglobalnetwork.floatingmarkets.DialogPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogManageRoom {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;


    public DialogManageRoom(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


    public void Popup_ChangeName(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull name = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

//        name.setHint("Product Name");
        if (dataRoom.getData() != null) {
            name.setText(dataRoom.getData().getNameRoom());

            Log.d("Popup_ChangeName", " ++++ " + dataRoom.getData().getMarketID());
            Log.d("Popup_ChangeName", " ++++ " + dataRoom.getData().getHotelID());
        }
        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Room Name");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                dataRoom.getData().setNameRoom(name.getText());

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).setValue(dataRoom.getData());
                mRoomRef.child(dataRoom.getKey()).setValue(dataRoom.getData());

            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeType(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel) {
        final String[] type = new String[]{"SAUCE", "APPETISERS", "SOUP OR CURRY", "NORTHEST FOOD", "DISHED",
                "MAIN COURSES", "NOODLES", "DESSERT", "JAPANESE FOOD", "ITLIAN FOOD", "SALAD", "PASTAS", "STEAK"};
        int checkItem = 0;
        if (dataRoom.getData() != null) {
            for (int i = 0; i < type.length; i++) {
                if (dataRoom.getData().getType() != null) {
                    if (dataRoom.getData().getType().equals(type[i])) {
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
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).child("type").setValue(type[i]);
                mRoomRef.child(dataRoom.getKey()).child("type").setValue(type[i]);
                dialogInterface.dismiss();
            }
        });
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeDetail(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel) {
        layout_popup = inflater.inflate(R.layout.dialog_edit, null);
        final EditTextNotNull detail = (EditTextNotNull) layout_popup.findViewById(R.id.edit);

//        detail.setHint("Detail");
        if (dataRoom.getData() != null) {
            detail.setText(dataRoom.getData().getDescription());
        }
        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Detail");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).child("description").setValue(detail.getText());
                mRoomRef.child(dataRoom.getKey()).child("description").setValue(detail.getText());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangePrice(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel) {
        layout_popup = inflater.inflate(R.layout.dialog_phone, null);
        final EditTextNotNull price = (EditTextNotNull) layout_popup.findViewById(R.id.phone);

        if (dataRoom.getData() != null) {
            price.setText(dataRoom.getData().getPrice() + "");
        }
        price.setNumberType(true);

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Price");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).child("price").setValue(Integer.parseInt(price.getText()));
                mRoomRef.child(dataRoom.getKey()).child("price").setValue(Integer.parseInt(price.getText()));


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeQuantity(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel) {
        layout_popup = inflater.inflate(R.layout.dialog_phone, null);
        final EditTextNotNull quantity = (EditTextNotNull) layout_popup.findViewById(R.id.phone);

        if (dataRoom.getData() != null) {
            quantity.setText(dataRoom.getData().getQuantity() + "");
        }
        quantity.setNumberType(true);

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Quantity");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).child("quantity").setValue(Integer.parseInt(quantity.getText()));
                mRoomRef.child(dataRoom.getKey()).child("quantity").setValue(Integer.parseInt(quantity.getText()));


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeSize(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel) {
        layout_popup = inflater.inflate(R.layout.dialog_phone, null);
        final EditTextNotNull sizeRoom = (EditTextNotNull) layout_popup.findViewById(R.id.phone);
        sizeRoom.setNumberType(true);
        sizeRoom.setHint("Square Meter");

        if (dataRoom.getData() != null) {
            sizeRoom.setText(dataRoom.getData().getPrice() + "");
        }
        sizeRoom.setNumberType(true);

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Size");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).child("size").setValue(sizeRoom.getText());
                mRoomRef.child(dataRoom.getKey()).child("size").setValue(sizeRoom.getText());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeGuest(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel) {
        layout_popup = inflater.inflate(R.layout.dialog_phone, null);
        final EditTextNotNull guest = (EditTextNotNull) layout_popup.findViewById(R.id.phone);
        guest.setNumberType(true);
        guest.setHint("Max guests");

        if (dataRoom.getData() != null) {
            guest.setText(dataRoom.getData().getPrice() + "");
        }
        guest.setNumberType(true);

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Guest");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).child("guest").setValue(guest.getText());
                mRoomRef.child(dataRoom.getKey()).child("guest").setValue(guest.getText());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeBed(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel) {
        final String[] result = new String[]{"1 double bed", "2 single bed"};
        int checkItem = 0;
        if (dataRoom.getData() != null) {
            for (int i = 0; i < result.length; i++) {
                if (dataRoom.getData().getType() != null) {
                    if (dataRoom.getData().getType().equals(result[i])) {
                        checkItem = i;
                        break;
                    }
                }
            }
        }

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Category");
        popupDialog.setSingleChoiceItems(result, checkItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).child("bed").setValue(result[i]);
                mRoomRef.child(dataRoom.getKey()).child("bed").setValue(result[i]);
                dialogInterface.dismiss();
            }
        });
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeBrackfast(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel){
        Popup_ChangeYesNo(dataRoom, dataHotel, "breakfast");
    }

    public void Popup_ChangeWifi(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel){
        Popup_ChangeYesNo(dataRoom, dataHotel, "wifi");
    }

    public void Popup_ChangeAir(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel){
        Popup_ChangeYesNo(dataRoom, dataHotel, "air");
    }

    public void Popup_ChangeSmoke(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel){
        Popup_ChangeYesNo(dataRoom, dataHotel, "smoke");
    }


    public void Popup_ChangeYesNo(final WrapFdbRoom dataRoom, final WrapFdbHotel dataHotel, final String type) {
        final String[] result = new String[]{"YES", "NO"};
        int checkItem = 0;
        if (dataRoom.getData() != null) {
            for (int i = 0; i < result.length; i++) {
                if (dataRoom.getData().getType() != null) {
                    if (dataRoom.getData().getType().equals(result[i])) {
                        checkItem = i;
                        break;
                    }
                }
            }
        }

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Category");
        popupDialog.setSingleChoiceItems(result, checkItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mHotelRef = mRootRef.child("hotel-room");
                DatabaseReference mRoomRef = mRootRef.child("room");

                mHotelRef.child(dataHotel.getKey()).child(dataRoom.getKey()).child(type).setValue(result[i]);
                mRoomRef.child(dataRoom.getKey()).child(type).setValue(result[i]);
                dialogInterface.dismiss();
            }
        });
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.create();
        popupDialog.show();
    }

}
