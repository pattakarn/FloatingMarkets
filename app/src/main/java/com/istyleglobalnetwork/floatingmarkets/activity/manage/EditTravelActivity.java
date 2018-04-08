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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbTravel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbTravel;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Edit_Travel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class EditTravelActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;

    WrapFdbMarket itemMarket = null;
    WrapFdbTravel itemTravel = null;
    List<WrapFdbImage> itemImage = new ArrayList<WrapFdbImage>();

    DatabaseReference mRootRef;
    ArrayList<Object> dataItem = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_travel);

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

            itemTravel = Parcels.unwrap(bundle.getParcelable("itemTravel"));
            if (itemTravel != null) {

            } else {
                DatabaseReference mTravelRef = mRootRef.child("travel");
                String newKeyTravel = mTravelRef.push().getKey();

                FdbTravel newTravel = new FdbTravel();
                itemTravel = new WrapFdbTravel(newKeyTravel, newTravel);
            }


        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        setData();
    }

    private void setData() {
        mRootRef.child("travel").child(itemTravel.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbTravel value = dataSnapshot.getValue(FdbTravel.class);

                dataItem = new ArrayList<Object>();
                List<String> dataHead = new ArrayList<String>();
                dataHead.add(itemMarket.getData().getNameMarket());
                dataItem.add(dataHead);

                List<Object> temp = new ArrayList<Object>();
                if (value != null)
                    itemTravel = new WrapFdbTravel(key, value);
                itemTravel.getData().setMarketID(itemMarket.getKey());
                temp.add(itemTravel);
                temp.add(itemMarket);
                dataItem.add(temp);
                dataItem.add(temp);
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
        mRootRef.child("item-award").child(itemTravel.getKey()).addValueEventListener(new ValueEventListener() {
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
                temp.add(itemTravel);
                temp.add(dataAward);
                dataItem.set(3, temp);
                setListImage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setListImage() {
        mRootRef.child("item-photo").child(itemTravel.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemImage = new ArrayList<WrapFdbImage>();

                if (dataItem.size() == 6) {
                    dataItem.remove(5);
                }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
                }

                List<Object> temp = new ArrayList<Object>();
                temp.add(itemTravel);
                temp.add(itemImage);
                dataItem.add(temp);


                RV_Adapter_Edit_Travel adapterList = new RV_Adapter_Edit_Travel(dataItem);
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
