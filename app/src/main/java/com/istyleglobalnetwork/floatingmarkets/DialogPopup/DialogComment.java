package com.istyleglobalnetwork.floatingmarkets.DialogPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.DateTimeMillis;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbComment;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbUser;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbComment;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogComment {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;

    DatabaseReference mRootRef;
    WrapFdbComment dataComment;
    String userID;
    String userName;

    public DialogComment(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


    public void Popup_ChangeComment(final String itemID) {
        layout_popup = inflater.inflate(R.layout.dialog_comment, null);
        final RatingBar rating = (RatingBar) layout_popup.findViewById(R.id.rating);
        final EditTextNotNull comment = (EditTextNotNull) layout_popup.findViewById(R.id.comment);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("user").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbUser value = dataSnapshot.getValue(FdbUser.class);

                userName = value.getNameContact();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("item-comment", "==========================================================" + itemID);
        mRootRef.child("item-comment").child(itemID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbComment value = postSnapshot.getValue(FdbComment.class);
//
                    Log.d("item-comment", "==========================================================" + key);
                    Log.d("item-comment", "==========================================================" + value.getComment());
                    if (value != null) {
                        if (value.getUserID().equals(userID)) {
                            dataComment = new WrapFdbComment(key, value);
                            rating.setRating(dataComment.getData().getRating());
                            comment.setText(dataComment.getData().getComment());
                            break;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        comment.setHint("Comment");
//        phone.setText(dataUser.getData().getPhone());
//        phone.setNumberType(true);

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Comment");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {


                DatabaseReference mItemRef = mRootRef.child("item-comment");
                DatabaseReference mCommentRef = mRootRef.child("comment");


                if (dataComment == null) {
                    String newKey = mCommentRef.push().getKey();
                    FdbComment data = new FdbComment();
                    data.setRating(rating.getRating());
                    data.setComment(comment.getText());
                    data.setUserID(userID);
                    data.setItemID(itemID);
                    data.setDate(DateTimeMillis.getDateMillisNow());
                    data.setTime(DateTimeMillis.getTimeMillisNow());
                    dataComment = new WrapFdbComment(newKey, data);
                } else if (dataComment.getData() == null){
                    String newKey = mCommentRef.push().getKey();
                    FdbComment data = new FdbComment();
                    data.setRating(rating.getRating());
                    data.setComment(comment.getText());
                    data.setUserID(userID);
                    data.setItemID(itemID);
                    data.setDate(DateTimeMillis.getDateMillisNow());
                    data.setTime(DateTimeMillis.getTimeMillisNow());
                    dataComment = new WrapFdbComment(newKey, data);
                } else {

                    dataComment.getData().setRating(rating.getRating());
                    dataComment.getData().setComment(comment.getText());
                    dataComment.getData().setDate(DateTimeMillis.getDateMillisNow());
                    dataComment.getData().setTime(DateTimeMillis.getTimeMillisNow());
                }

                mItemRef.child(itemID).child(dataComment.getKey()).setValue(dataComment.getData());
                mCommentRef.child(dataComment.getKey()).setValue(dataComment.getData());
//                dataComment.getData().setRating((int) rating.getRating());
//                dataComment.getData().setComment(comment.getText());
//                dataComment.getData().setDate(DateTimeMillis.getDateMillisNow());
//                dataComment.getData().setTime(DateTimeMillis.getTimeMillisNow());
//
//
//                mItemRef.child(userID).child(itemID).setValue(dataComment.getData());
//                if (!childName.equals("")) {
//                    if (childName.equals("name")) {
//                        dataShop.getData().setNameShop(editText.getText());
//                        mShopRef.child(dataZone.getKey()).child(dataShop.getKey()).setValue(dataShop.getData());
//                        mProductRef.child(dataShop.getKey()).setValue(dataShop.getData());
//                    } else {
//                        mShopRef.child(dataZone.getKey()).child(dataShop.getKey()).child(childName).setValue(editText.getText());
//                        mProductRef.child(dataShop.getKey()).child(childName).setValue(editText.getText());
//                    }
//                }


            }
        });
        popupDialog.create();
        popupDialog.show();
    }


}
