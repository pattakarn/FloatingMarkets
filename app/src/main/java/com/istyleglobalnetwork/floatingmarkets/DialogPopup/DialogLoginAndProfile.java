package com.istyleglobalnetwork.floatingmarkets.DialogPopup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.DateTimeMillis;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbUser;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.EditTextNotNull;


/**
 * Created by Sung on 1/9/2016 AD.
 */
public class DialogLoginAndProfile {

    Context context;
    AlertDialog.Builder popupDialog;
    View layout_popup;
    LayoutInflater inflater;
    FirebaseAuth mAuth;


    public DialogLoginAndProfile(Context context) {
        this.context = context;
        this.popupDialog = new AlertDialog.Builder(context);
        this.inflater = LayoutInflater.from(context);
    }


//    public void Popup_Date() {
//        layout_popup = inflater.inflate(R.layout.popup_datepicker, null);
//        final DatePicker dp = (DatePicker) layout_popup.findViewById(R.id.datePicker);
//        tv = (TextView) view;
//        popupDialog.setView(layout_popup);
//        popupDialog.setNegativeButton("ยกเลิก", null);
//        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int arg1) {
////                String str = dp.getDayOfMonth() + "-" + (dp.getMonth() + 1) + "-" + dp.getYear();
//                String str = dp.getDayOfMonth() + "-" + (dp.getMonth() + 1) + "-" + dp.getYear();
////                if (mode.equals("1")) {
////                    db_employee.setByIndex(db_index_field, str);
////                    db_employee.Save();
////                } else if (mode.equals("2")) {
////                    db_spouse.setByIndex(db_index_field, str);
////                    db_spouse.Save();
////                } else if (mode.equals("3")) {
////                    db_family.setByIndex(db_index_field, db_index, str);
////                    db_family.Save(db_index);
////                }
//                tv.setText(str);
//            }
//        });
//        popupDialog.create();
//        popupDialog.show();
//    }
//
//    public void Popup_Time() {
//        layout_popup = inflater.inflate(R.layout.popup_timepicker, null);
//        final TimePicker tp = (TimePicker) layout_popup.findViewById(R.id.timePicker);
//        tp.setIs24HourView(true);
//        tp.setCurrentMinute(00);
//        tv = (TextView) view;
//        popupDialog.setView(layout_popup);
//        popupDialog.setNegativeButton("ยกเลิก", null);
//        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int arg1) {
//                String str = tp.getCurrentHour() + ":" + tp.getCurrentMinute();
////                if (mode.equals("1")) {
////                    db_employee.setByIndex(db_index_field, str);
////                    db_employee.Save();
////                } else if (mode.equals("2")) {
////                    db_spouse.setByIndex(db_index_field, str);
////                    db_spouse.Save();
////                } else if (mode.equals("3")) {
////                    db_family.setByIndex(db_index_field, db_index, str);
////                    db_family.Save(db_index);
////                }
//                tv.setText(str);
//            }
//        });
//        popupDialog.create();
//        popupDialog.show();
//    }

    public void Popup_Register(final Boolean isToast) {
        layout_popup = inflater.inflate(R.layout.dialog_register, null);
        final EditTextNotNull etEmail = (EditTextNotNull) layout_popup.findViewById(R.id.email);
        final EditTextNotNull etPassword = (EditTextNotNull) layout_popup.findViewById(R.id.password);
        final EditTextNotNull etRepassword = (EditTextNotNull) layout_popup.findViewById(R.id.repassword);


        FirebaseAuth.AuthStateListener mAuthListener;


        etEmail.setEmailType(true);
        etPassword.setPasswordType(true);
        etRepassword.setPasswordType(true);
        etEmail.setHint("Email");
        etPassword.setHint("Password");
        etRepassword.setHint("Re-password");
        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Register");
        popupDialog.setCancelable(false);
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("สร้างบัญชี", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                etEmail.checkNull();
                etPassword.checkNull();
                etRepassword.checkNull();

                if (etPassword.getText().equals(etRepassword.getText())) {
                    if (etEmail.checkNull() && etPassword.checkNull() && etRepassword.checkNull()) {

                        mAuth = FirebaseAuth.getInstance();
                        mAuth.createUserWithEmailAndPassword(etEmail.getText(), etPassword.getText())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            if (isToast) {
                                                Toast.makeText(context.getApplicationContext(), "สร้างบัญชีสำเร็จ กรุณายืนยันอีเมล", Toast.LENGTH_SHORT).show();
                                            }
//                                        Toast.makeText(context.getApplicationContext(), "สร้างบัญชีสำเร็จ", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context.getApplicationContext(), "สร้างบัญชีไม่สำเร็จ กรุณาลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(context.getApplicationContext(), "สร้างบัญชีไม่สำเร็จ กรุณาลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context.getApplicationContext(), "รหัสผ่านผิดพลาด", Toast.LENGTH_SHORT).show();
                }


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_Login() {
        layout_popup = inflater.inflate(R.layout.dialog_login, null);
        final EditTextNotNull etEmail = (EditTextNotNull) layout_popup.findViewById(R.id.email);
        final EditTextNotNull etPassword = (EditTextNotNull) layout_popup.findViewById(R.id.password);

        etEmail.setEmailType(true);
        etPassword.setPasswordType(true);
        etEmail.setHint("Email");
        etPassword.setHint("Password");
        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Sign in");
        popupDialog.setCancelable(false);
        popupDialog.setNegativeButton("ยกเลิก", null);
//        popupDialog.setNeutralButton("สร้างบัญชี", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                popupDialog = new AlertDialog.Builder(context);
//                Popup_Register(true);
//            }
//        });
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                etEmail.checkNull();
                etPassword.checkNull();

                if (etEmail.checkNull() && etPassword.checkNull()) {
                    mAuth = FirebaseAuth.getInstance();
                    Login(etEmail.getText(), etPassword.getText());
//                    mAuth.signInWithEmailAndPassword(etEmail.getText(), etPassword.getText()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                if (mAuth.getCurrentUser().isEmailVerified()) {
//                                    Toast.makeText(context, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();
//                                    Log.d("signin", "================ Success");
//                                } else {
//                                    mAuth.getCurrentUser().sendEmailVerification();
//                                    Toast.makeText(context, "กรุณายืนยันอีเมล", Toast.LENGTH_SHORT).show();
//                                    Log.d("signin", "================ failed");
//                                    FirebaseAuth.getInstance().signOut();
//                                }
//
//                            } else {
//                                Toast.makeText(context, "เข้าสู่ระบบไม่สำเร็จ กรุณาลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
//                                Log.d("signin", "================ failed");
//                            }
//                        }
//                    });
                }


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    private void Login(String email, String password) {
//        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (mAuth.getCurrentUser().isEmailVerified()) {
                        Toast.makeText(context, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();
                        Log.d("signin", "================ Success");
                    } else {
                        mAuth.getCurrentUser().sendEmailVerification();
                        Toast.makeText(context, "กรุณายืนยันอีเมล", Toast.LENGTH_SHORT).show();
                        Log.d("signin", "================ failed");
                        FirebaseAuth.getInstance().signOut();
                    }

                } else {
                    Toast.makeText(context, "เข้าสู่ระบบไม่สำเร็จ กรุณาลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
                    Log.d("signin", "================ failed");
                }
            }
        });
    }

    public void Popup_ResetPassword() {
        layout_popup = inflater.inflate(R.layout.dialog_reset_password, null);
        final EditTextNotNull etEmail = (EditTextNotNull) layout_popup.findViewById(R.id.email);

        etEmail.setEmailType(true);
        etEmail.setHint("Email");
        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Reset password");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                etEmail.checkNull();

                if (etEmail.checkNull()) {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String emailAddress = etEmail.getText();

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Reset password", "Email sent.");
                                    }
                                }
                            });
                }

            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeContactName(final WrapFdbUser dataUser) {
        layout_popup = inflater.inflate(R.layout.dialog_contact, null);
        final EditTextNotNull firstname = (EditTextNotNull) layout_popup.findViewById(R.id.firstname);
        final EditTextNotNull lastname = (EditTextNotNull) layout_popup.findViewById(R.id.lastname);

        firstname.setHint("Firstname");
        lastname.setHint("Lastname");
        firstname.setText(dataUser.getData().getNameContact().split(" ")[0]);
        lastname.setText(dataUser.getData().getNameContact().split(" ")[1]);
        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Reset password");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mUserRef = mRootRef.child("user");

                mUserRef.child(dataUser.getKey()).child("nameContact").setValue(firstname.getText() + " " + lastname.getText());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeGender(final WrapFdbUser dataUser) {
        final String[] sex = new String[]{"MALE", "FEMALE"};
        int checkItem = 0;
        if (dataUser.getData().getGender().equals(sex[1])){
            checkItem = 1;
        }

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Gender");
        popupDialog.setSingleChoiceItems(sex, checkItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mUserRef = mRootRef.child("user");
                mUserRef.child(dataUser.getKey()).child("gender").setValue(sex[i]);
                dialogInterface.dismiss();
            }
        });
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangeDate(final WrapFdbUser dataUser) {
        layout_popup = inflater.inflate(R.layout.dialog_datepicker, null);
        final DatePicker dp = (DatePicker) layout_popup.findViewById(R.id.datePicker);
//        tv = (TextView) view;
        if (dataUser.getData().getBirth() != null) {
            String[] date = DateTimeMillis.MillisToDate(dataUser.getData().getBirth()).split("-");
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            dp.updateDate(year, month - 1, day);
        }
        popupDialog.setView(layout_popup);
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                String str = dp.getDayOfMonth() + "-" + (dp.getMonth() + 1) + "-" + dp.getYear();
//                Log.d("date", "======================================= " + str);
//                Log.d("date", "======================================= " + DateTimeMillis.DateToMillis(str));
//                Log.d("date", "======================================= " + DateTimeMillis.MillisToDate(DateTimeMillis.DateToMillis(str)));

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mUserRef = mRootRef.child("user");
                mUserRef.child(dataUser.getKey()).child("birth").setValue(DateTimeMillis.DateToMillis(str));

            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    public void Popup_ChangePhone(final WrapFdbUser dataUser) {
        layout_popup = inflater.inflate(R.layout.dialog_phone, null);
        final EditTextNotNull phone = (EditTextNotNull) layout_popup.findViewById(R.id.phone);

        phone.setText(dataUser.getData().getPhone());
        phone.setPhoneType(true);

        popupDialog.setView(layout_popup);
        popupDialog.setTitle("Phone");
        popupDialog.setNegativeButton("ยกเลิก", null);
        popupDialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mUserRef = mRootRef.child("user");

                mUserRef.child(dataUser.getKey()).child("phone").setValue(phone.getText());


            }
        });
        popupDialog.create();
        popupDialog.show();
    }

}
