package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbZone;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Zone;

import java.util.ArrayList;
import java.util.List;

public class HomeZoneActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_zone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(glm);

        final ArrayList<Object> dataIv = new ArrayList<Object>();
        dataIv.add(R.drawable.zone_1);
        dataIv.add(R.drawable.zone_2);
        dataIv.add(R.drawable.zone_3);
        dataIv.add(R.drawable.zone_4);
        dataIv.add(R.drawable.zone_5);
        dataIv.add(R.drawable.zone_6);
        dataIv.add(R.drawable.zone_7);
        dataIv.add(R.drawable.zone_8);


        String market = "-L2JZyVxALaIcBRFeyHH";
        Log.d("test", "+++++++++++++++++++++++++++++++++++");
        mRootRef.child("market-zone").child(market).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbZone value = postSnapshot.getValue(FdbZone.class);
                    data.add(new WrapFdbZone(key, value));
                    Log.d("test", "+++++++++++++++++++++++++++++++++++ " + value.getNameZone());
                }


                RV_Adapter_Zone adapterList = new RV_Adapter_Zone(dataIv, data);
                rv.setAdapter(adapterList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        ArrayList<Object> data = new ArrayList<Object>();
//        data.add(R.drawable.zone_1);
//        data.add(R.drawable.zone_2);
//        data.add(R.drawable.zone_3);
//        data.add(R.drawable.zone_4);
//        data.add(R.drawable.zone_5);
//        data.add(R.drawable.zone_6);
//        data.add(R.drawable.zone_7);
//        data.add(R.drawable.zone_8);
//
//        GridLayoutManager glm = new GridLayoutManager(this, 2);
//        glm.setOrientation(LinearLayoutManager.VERTICAL);
//        rv.setLayoutManager(glm);
//        RV_Adapter_Zone adapterList = new RV_Adapter_Zone(data);
//        rv.setAdapter(adapterList);
    }

    private void initInstances() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);

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
