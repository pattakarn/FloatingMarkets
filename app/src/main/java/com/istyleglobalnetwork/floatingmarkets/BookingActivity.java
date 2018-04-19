package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.QuantityViewGroup;
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;

import org.parceler.Parcels;

import java.util.ArrayList;
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
    Button btnAdd;

    WrapFdbHotel itemHotel = null;
    WrapFdbRoom itemRoom = null;
    ArrayList<Object> data = new ArrayList<Object>();
    List<Object> itemImage = new ArrayList<Object>();

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
                                String startDate = dayStart + "-" + (monthStart+1) + "-" + yearStart;
                                String endDate = dayEnd + "-" + (monthEnd+1) + "-" + yearEnd;
                                tvStart.setText("Start : " + startDate);
                                tvEnd.setText("End : " + endDate);
                            }
                        });

                smoothDateRangePickerFragment.show(getFragmentManager(), "smoothDateRangePicker");
            }
        });

        btnAdd.setVisibility(View.INVISIBLE);

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
        btnAdd = (Button) findViewById(R.id.btn_add_cart);

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

