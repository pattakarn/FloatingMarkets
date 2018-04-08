package com.istyleglobalnetwork.floatingmarkets.activity.service;

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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbService;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbService;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceListActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;
    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

//        ArrayList<Object> data = new ArrayList<Object>();
//        data.add(new DataTravelItem(R.drawable.service1, "จัดอบรม ประชุม สัมนา"));
//        data.add(new DataTravelItem(R.drawable.service2, "ท่องเที่ยวทางน้ำ"));
//        data.add(new DataTravelItem(R.drawable.service3, "สถานที่จัดงานอีเว้นท์"));
//        data.add(new DataTravelItem(R.drawable.service4, "สอนพายเรือ"));
//        data.add(new DataTravelItem(R.drawable.service5, "สอนรำไทย"));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
//        RV_Adapter_Service adapterList = new RV_Adapter_Service(data);
//        rv.setAdapter(adapterList);
        setListItem();
    }

    private void setListItem() {

        String market = "-L2JZyVxALaIcBRFeyHH";
        mRootRef.child("market-service").child(market).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbService value = postSnapshot.getValue(FdbService.class);
                    data.add(new WrapFdbService(key, value));
                }

                RV_Adapter_Service adapterList = new RV_Adapter_Service(data);
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
