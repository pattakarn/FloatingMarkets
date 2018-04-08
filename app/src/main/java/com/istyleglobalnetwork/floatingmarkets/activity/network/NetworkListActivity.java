package com.istyleglobalnetwork.floatingmarkets.activity.network;

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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Network;

import java.util.ArrayList;
import java.util.List;

public class NetworkListActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;
    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_list);

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
//        data.add(new DataTravelItem(R.drawable.network1, "ศูนย์ภูมิรักษ์ธรรมชาติ"));
//        data.add(new DataTravelItem(R.drawable.network2, "โรงเรียนโพธิสารพิทยากร"));
//        data.add(new DataTravelItem(R.drawable.network3, "กลุ่มเพราะเห็ด"));
//        data.add(new DataTravelItem(R.drawable.network4, "สวนผักออแกร์นิค"));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
//        RV_Adapter_Network adapterList = new RV_Adapter_Network(data);
//        rv.setAdapter(adapterList);
        setListItem();
    }

    private void setListItem() {

        String market = "-L2JZyVxALaIcBRFeyHH";
        mRootRef.child("market-network").child(market).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbNetwork value = postSnapshot.getValue(FdbNetwork.class);
                    data.add(new WrapFdbNetwork(key, value));
                }

                RV_Adapter_Network adapterList = new RV_Adapter_Network(data);
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
