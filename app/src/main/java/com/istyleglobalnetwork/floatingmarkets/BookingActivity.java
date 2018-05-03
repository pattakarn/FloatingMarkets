package com.istyleglobalnetwork.floatingmarkets;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbBooking;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbBookingList;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.QuantityViewGroup;
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    TextView tvTitle;
    ImageView ivProduct;
    TextView tvName;
    TextView tvDetail;
    QuantityViewGroup qvg;
    LinearLayout llDate;
    TextView tvStart;
    TextView tvEnd;
    TextView tvTotal;
    Button btnConfirm;
    RadioGroup radioGroup;
    RadioButton rbYes, rbNo;

    String checkIn;
    String checkOut;

    WrapFdbHotel itemHotel = null;
    WrapFdbRoom itemRoom = null;
    ArrayList<Object> data = new ArrayList<Object>();
    List<Object> itemImage = new ArrayList<Object>();
    List<String> dayBooking = new ArrayList<String>();

    DatabaseReference mRootRef;
    private UploadTask mUploadTask;
    private StorageReference folderRef, imageRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        folderRef = storageRef.child("photos");

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            itemHotel = Parcels.unwrap(bundle.getParcelable("itemHotel"));
            itemRoom = Parcels.unwrap(bundle.getParcelable("itemRoom"));
            if (itemRoom != null) {
                tvName.setText(itemRoom.getData().getNameRoom());
                tvDetail.setText(itemRoom.getData().getPrice() + " บาท");
            }

        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        llDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmoothDateRangePickerFragment smoothDateRangePickerFragment = SmoothDateRangePickerFragment.newInstance(
                        new SmoothDateRangePickerFragment.OnDateRangeSetListener() {
                            @Override
                            public void onDateRangeSet(SmoothDateRangePickerFragment view,
                                                       int yearStart, int monthStart,
                                                       int dayStart, int yearEnd,
                                                       int monthEnd, int dayEnd) {
                                // grab the date range, do what you want


                                String showStart = dayStart + "-" + (monthStart + 1) + "-" + yearStart;
                                String showEnd = dayEnd + "-" + (monthEnd + 1) + "-" + yearEnd;
                                String start = yearStart + "-" + monthStart + "-" + dayStart;
                                String end = yearEnd + "-" + monthEnd + "-" + dayEnd;
                                List<Date> dateRange = DateTimeMillis.getRangeDates(start, end);
                                tvStart.setText("Check in : " + showStart);
                                tvEnd.setText("Check out : " + showEnd);
                                checkIn = showStart;
                                checkOut = showEnd;

                                for (Date dateData : dateRange) {
//                                    Log.d("Date ======", "======= " + DateTimeMillis.DateToString(dateData));
                                    dayBooking.add(DateTimeMillis.DateToString(dateData));
                                }

                                for (String dateData : dayBooking) {
                                    Log.d("List Date ======", "==================================== " + dateData);
                                }

                                tvTotal.setText((itemRoom.getData().getPrice() * qvg.getQuantity() * dayBooking.size()) + "");

                            }
                        });

                smoothDateRangePickerFragment.show(getFragmentManager(), "smoothDateRangePicker");
            }
        });

//        btnAdd.setVisibility(View.INVISIBLE);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dayBooking.size() > 0) {
                    if (!checkRadio().equals("")) {
                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(BookingActivity.this);
                        builder.setMessage("ยืนยันการจอง ?");
                        builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(BookingActivity.this,
                                        "ขอบคุณครับ", Toast.LENGTH_SHORT).show();
                                setConfirm();

//                        DatabaseReference mUserRef = mRootRef.child("user");
//                        mUserRef.child("V3QJxGYOpKd1ck6vHirhh0Ijbna2").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                String key = dataSnapshot.getKey();
//                                FdbUser value = dataSnapshot.getValue(FdbUser.class);
//
//                                FdbMessage message = new FdbMessage("user1", value.getEmail(), value.getToken(), "คำสั่งซื้อหมายเลข 1111", "สินค้า");
//                                mRootRef = FirebaseDatabase.getInstance().getReference();
//                                DatabaseReference mMessageRef = mRootRef.child("messages");
//
//                                String Messagekey = mMessageRef.push().getKey();
//                                mMessageRef.child(Messagekey).setValue(message);
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });

                                finish();
                            }
                        });
                        builder.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.dismiss();
                            }
                        });
                        builder.show();
                    } else {
                        Toast.makeText(BookingActivity.this,
                                "คุณต้องการบริการรถรับส่งหรือไม่ ?", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BookingActivity.this,
                            "กรุณาแจ้งวันที่คุณต้องการจอง", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private String checkRadio() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        String selectRadio = "";
        // find which radioButton is checked by id
        if (selectedId == rbYes.getId()) {
            selectRadio = "YES";
        } else if (selectedId == rbNo.getId()) {
            selectRadio = "NO";
        }
        return selectRadio;
    }


    private void setConfirm() {
        DatabaseReference mBookingRef = mRootRef.child("booking");
        DatabaseReference mUserBookingRef = mRootRef.child("user-booking");
        DatabaseReference mBookingListRef = mRootRef.child("booking-list");

        String selectRadio = checkRadio();
        int quantity = qvg.getQuantity();
        int price = itemRoom.getData().getPrice() * quantity * dayBooking.size();

        final FdbBooking booking = new FdbBooking();


        booking.setStatus("booking");
        booking.setHotelID(itemHotel.getKey());
        booking.setRoomID(itemRoom.getKey());
        booking.setUserID(mAuth.getCurrentUser().getUid());
        booking.setQuantity(quantity);
        booking.setDay(dayBooking.size());
        booking.setPrice(price);
        booking.setDate(DateTimeMillis.getDateMillisNow());
        booking.setTime(DateTimeMillis.getTimeMillisNow());
        booking.setCheckin(DateTimeMillis.DateToMillis(checkIn));
        booking.setCheckout(DateTimeMillis.DateToMillis(checkOut));
        booking.setCarService(selectRadio);

        mBookingRef.child(mBookingRef.push().getKey()).setValue(booking);
        mUserBookingRef.child(currentUser.getUid()).child(mBookingRef.push().getKey()).setValue(booking);
//
        String keyBookingList = mBookingListRef.child(booking.getRoomID()).push().getKey();
        FdbBookingList dataBookingList = new FdbBookingList();
        dataBookingList.setQuantity(booking.getQuantity());
        dataBookingList.setMark("booking");
        dataBookingList.setUserID(mAuth.getCurrentUser().getUid());
        dataBookingList.setDate(DateTimeMillis.getDateMillisNow());
        dataBookingList.setTime(DateTimeMillis.getTimeMillisNow());
        dataBookingList.setCheckin(DateTimeMillis.DateToMillis(checkIn));
        dataBookingList.setCheckout(DateTimeMillis.DateToMillis(checkOut));
        mBookingListRef.child(booking.getRoomID()).child(keyBookingList).setValue(dataBookingList);

//
//        mRootRef.child("product").child(order.getProductID()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                final String key = dataSnapshot.getKey();
//                FdbProduct value = dataSnapshot.getValue(FdbProduct.class);
//
//                FBAnalytics fbAnalytics = new FBAnalytics(CartListActivity.this);
//                fbAnalytics.addItem(key, value.getNameProduct(), order.getPrice(), order.getQuantity());
//                fbAnalytics.addUser(mAuth.getCurrentUser().getUid(), mAuth.getCurrentUser().getEmail());
//                fbAnalytics.EventCheckout();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


//            updateStock(order.getProductID(), order.getQuantity());
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivProduct = (ImageView) findViewById(R.id.iv_product);
        tvName = (TextView) findViewById(R.id.tv_productname);
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        qvg = (QuantityViewGroup) findViewById(R.id.qvg);
        llDate = (LinearLayout) findViewById(R.id.ll_date);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvEnd = (TextView) findViewById(R.id.tv_end);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        rbYes = (RadioButton) findViewById(R.id.radio_yes);
        rbNo = (RadioButton) findViewById(R.id.radio_no);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

