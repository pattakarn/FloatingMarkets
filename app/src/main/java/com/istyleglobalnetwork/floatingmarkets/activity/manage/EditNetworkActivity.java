package com.istyleglobalnetwork.floatingmarkets.activity.manage;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Edit_Network;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class EditNetworkActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;

    WrapFdbMarket itemMarket = null;
    WrapFdbNetwork itemNetwork = null;
    List<WrapFdbImage> itemImage = new ArrayList<WrapFdbImage>();

    DatabaseReference mRootRef;
    ArrayList<Object> dataItem = new ArrayList<Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_network);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            itemMarket = Parcels.unwrap(bundle.getParcelable("itemMarket"));
            if (itemMarket != null) {
//                tvMarket.setText(itemMarket.getData().getNameMarket());
            }

            itemNetwork = Parcels.unwrap(bundle.getParcelable("itemNetwork"));
            if (itemNetwork != null) {

            } else {
                DatabaseReference mNetworkRef = mRootRef.child("network");
                String newKeyNetwork = mNetworkRef.push().getKey();

                FdbNetwork newNetwork = new FdbNetwork();
                itemNetwork = new WrapFdbNetwork(newKeyNetwork, newNetwork);
            }


        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        setData();
    }

    private void setData() {
        mRootRef.child("network").child(itemNetwork.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbNetwork value = dataSnapshot.getValue(FdbNetwork.class);

                dataItem = new ArrayList<Object>();
                List<String> dataHead = new ArrayList<String>();
                dataHead.add(itemMarket.getData().getNameMarket());
                dataItem.add(dataHead);

                List<Object> temp = new ArrayList<Object>();
                if (value != null)
                    itemNetwork = new WrapFdbNetwork(key, value);
                itemNetwork.getData().setMarketID(itemMarket.getKey());
                temp.add(itemNetwork);
                temp.add(itemMarket);
                dataItem.add(temp);
                dataItem.add(temp);

//                setListImage();
                setAward();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setAward() {
        mRootRef.child("item-award").child(itemNetwork.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<WrapFdbAward> dataAward = new ArrayList<WrapFdbAward>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbAward value = postSnapshot.getValue(FdbAward.class);
                    dataAward.add(new WrapFdbAward(key, value));

                    Log.d("Award", "+++++++++++++++++++++++++++++++++++++ ");

                }
                List<Object> temp = new ArrayList<Object>();
                temp.add(itemNetwork);
                temp.add(dataAward);
                dataItem.set(2, temp);
                setListImage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setListImage() {
        mRootRef.child("item-photo").child(itemNetwork.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemImage = new ArrayList<WrapFdbImage>();

                if (dataItem.size() == 4) {
                    dataItem.remove(3);
                }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
                }

                List<Object> temp = new ArrayList<Object>();
                temp.add(itemNetwork);
                temp.add(itemImage);
                dataItem.add(temp);


                RV_Adapter_Edit_Network adapterList = new RV_Adapter_Edit_Network(dataItem);
                rv.setAdapter(adapterList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initInstances() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
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

    @Override
    protected void onResume() {
        super.onResume();
        CountDownTimer cdt = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Tick
            }

            public void onFinish() {
                // Finish
                setListImage();
            }
        }.start();

    }
}
