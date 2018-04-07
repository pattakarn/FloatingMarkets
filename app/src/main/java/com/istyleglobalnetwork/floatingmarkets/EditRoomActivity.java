package com.istyleglobalnetwork.floatingmarkets;

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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbRoom;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Edit_Room;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class EditRoomActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;

    WrapFdbMarket itemMarket = null;
    WrapFdbHotel itemHotel = null;
    WrapFdbRoom itemRoom = null;
    List<WrapFdbImage> itemImage = new ArrayList<WrapFdbImage>();

    DatabaseReference mRootRef;
    ArrayList<Object> dataItem = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

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

            itemHotel = Parcels.unwrap(bundle.getParcelable("itemTravel"));
            if (itemHotel != null) {
//                tvZone.setText(itemZone.getData().getNameZone());
            }

            itemRoom = Parcels.unwrap(bundle.getParcelable("itemRoom"));
            if (itemRoom != null) {
//                setData();
            } else {
                DatabaseReference mRoomRef = mRootRef.child("room");
                String newKeyRoom = mRoomRef.push().getKey();
                Log.d("newKeyProduct", "++++++++++++++ " + newKeyRoom);

                FdbRoom newRoom = new FdbRoom();
                itemRoom = new WrapFdbRoom(newKeyRoom, newRoom);
            }

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        setData();
    }

    private void setData() {
        mRootRef.child("room").child(itemRoom.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbRoom value = dataSnapshot.getValue(FdbRoom.class);

                dataItem = new ArrayList<Object>();
                List<String> dataHead = new ArrayList<String>();
                dataHead.add(itemMarket.getData().getNameMarket());
                dataHead.add(itemHotel.getData().getNameHotel());
                dataHead.add(itemRoom.getData().getNameRoom());
                dataItem.add(dataHead);

                List<Object> temp = new ArrayList<Object>();
                if (value != null)
                    itemRoom = new WrapFdbRoom(key, value);
                itemRoom.getData().setMarketID(itemMarket.getKey());
                itemRoom.getData().setHotelID(itemHotel.getKey());
                temp.add(itemRoom);
                temp.add(itemHotel);
                dataItem.add(temp);

                setListImage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setListImage() {
        mRootRef.child("item-photo").child(itemRoom.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemImage = new ArrayList<WrapFdbImage>();

                if (dataItem.size() == 3) {
                    dataItem.remove(2);
                }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
                }

                List<Object> temp = new ArrayList<Object>();
                temp.add(itemRoom);
                temp.add(itemImage);
                dataItem.add(temp);

                RV_Adapter_Edit_Room adapterList = new RV_Adapter_Edit_Room(dataItem);
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
