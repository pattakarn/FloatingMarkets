package com.istyleglobalnetwork.floatingmarkets.activity.hotel;

import android.os.Bundle;
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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbRoom;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Hotel_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataRoomItem;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class HotelItemActivity extends AppCompatActivity {

    String nameHotel;
    ArrayList<Object> data = new ArrayList<Object>();
    List<Object> itemImage = new ArrayList<Object>();

    TextView tvTitle;
    RecyclerView rv;

    WrapFdbHotel itemHotel = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_item);

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

            itemHotel = Parcels.unwrap(bundle.getParcelable("itemHotel"));
            if (itemHotel != null) {
                tvTitle.setText(itemHotel.getData().getNameHotel());
            }

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        setListImage();

//        Bundle bundle = getIntent().getExtras();
//        nameHotel = bundle.getString("NameHotel", "");
//        imageHotel = bundle.getInt("ImageHotel");
//
//        initInstances();
//        tvTitle.setText(nameHotel);
//
//        DataImageHotel dataImageHotel = new DataImageHotel(imageHotel, nameHotel, "4");



//        ArrayList<Object> data = new ArrayList<Object>();
//        data.add(dataImageHotel);
//        data.add("contact");
//        data.add("location");
//        data.add(nameHotel);
//        data.add("Twin - Non - Smoking");
//        data.add("Standard Double Room");
//        data.add("Standard Twin Room");
//        data.add("Triple - Non - Smoking");
//
//
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rv.setLayoutManager(llm);
//        RV_Adapter_Hotel_Item adapterList = new RV_Adapter_Hotel_Item(data);
//        rv.setAdapter(adapterList);
    }

    private void setListImage() {
        String shop = itemHotel.getKey();
        mRootRef.child("item-photo").child(shop).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemImage = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
                }

                data.add(itemImage);
                setAward();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setAward() {
        mRootRef.child("item-award").child(itemHotel.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<WrapFdbAward> dataAward = new ArrayList<WrapFdbAward>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbAward value = postSnapshot.getValue(FdbAward.class);
                    dataAward.add(new WrapFdbAward(key, value));

                    Log.d("Award", "+++++++++++++++++++++++++++++++++++++ ");

                }

                data.add(itemHotel);
                data.add(dataAward);
                data.add(itemHotel); // contact
                data.add(itemHotel); // rating
                setListProduct();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void setListProduct() {
        String hotelKey = itemHotel.getKey();
        mRootRef.child("hotel-room").child(hotelKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbRoom value = postSnapshot.getValue(FdbRoom.class);
//                    data.add(new WrapFdbProduct(key, value));
                    WrapFdbRoom tempRoom = new WrapFdbRoom(key, value);
                    data.add(new DataRoomItem(itemHotel, tempRoom, R.drawable.ice1));
                }

                RV_Adapter_Hotel_Item adapterList = new RV_Adapter_Hotel_Item(itemHotel, data, getLayoutInflater());
                rv.setAdapter(adapterList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
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
}
