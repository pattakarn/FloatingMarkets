package com.istyleglobalnetwork.floatingmarkets.activity.hotel;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelListActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;
    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        tv_title.setText("โรงแรม");

//        ArrayList<Object> data = new ArrayList<Object>();
//        data.add(new DataHotelItem(R.drawable.hotel1, "HOTEL CEZAR"));
//        data.add(new DataHotelItem(R.drawable.hotel2, "HOTEL CORDOBA CENTER"));
//        data.add(new DataHotelItem(R.drawable.hotel3, "Mhonsa Hotel"));
//        data.add(new DataHotelItem(R.drawable.hotel4, "DoubleTree By Hilten"));
//        data.add(new DataHotelItem(R.drawable.hotel5, "GATEWAY"));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        setListItem();
//        RV_Adapter_Hotel adapterList = new RV_Adapter_Hotel(data);
//        rv.setAdapter(adapterList);
    }

    private void setListItem() {

        String market = "-L2JZyVxALaIcBRFeyHH";
        mRootRef.child("market-hotel").child(market).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbHotel value = postSnapshot.getValue(FdbHotel.class);
                    data.add(new WrapFdbHotel(key, value));
                }

                RV_Adapter_Hotel adapterList = new RV_Adapter_Hotel(data);
                rv.setAdapter(adapterList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initInstances() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
//        iv_company = (ImageView) rootView.findViewById(R.id.iv_company);
//        iv_company.setOnClickListener(this);

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
