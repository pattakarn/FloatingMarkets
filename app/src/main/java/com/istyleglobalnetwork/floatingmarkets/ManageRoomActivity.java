package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbRoom;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_Room;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ManageRoomActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Spinner spinMarket;
    Spinner spinHotel;
    Button btnAdd;
    int imageItem;

    List<WrapFdbMarket> dataMarket = new ArrayList<WrapFdbMarket>();
    List<WrapFdbHotel> dataHotel = new ArrayList<WrapFdbHotel>();

    List<String> listMarket = new ArrayList<String>();
    List<String> listHotel = new ArrayList<String>();

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

//        spinMarket.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
//        spinMarket.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
//            }
//        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
//        RV_Adapter_Product_Item adapterList = new RV_Adapter_Product_Item(data);
//        rv.setAdapter(adapterList);
//
        setListMarket();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinHotel.getSelectedItemPosition() == -1) {
                    Toast.makeText(getApplicationContext(), "Pleace select hotel.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ManageRoomActivity.this, EditRoomActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("itemMarket", Parcels.wrap(dataMarket.get(spinMarket.getSelectedItemPosition())));
                    bundle.putParcelable("itemTravel", Parcels.wrap(dataHotel.get(spinHotel.getSelectedItemPosition())));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void setListMarket() {

        mRootRef.child("market").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbMarket value = postSnapshot.getValue(FdbMarket.class);
                    listMarket.add(value.getNameMarket());
                    dataMarket.add(new WrapFdbMarket(key, value));
                }

//                RV_Adapter_Manage_Market adapterList = new RV_Adapter_Manage_Market(data);
//                rv.setAdapter(adapterList);
                ArrayAdapter<String> adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listMarket);
                spinMarket.setAdapter(adapterList);
                spinMarket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        setListHotel();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
//                spinMarket.setItems(listMarket);
//                spinMarket.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//                    @Override
//                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
////                        Log.d("spinMarket", "============= " + listMarketKey.get(position));
////                        Snackbar.make(view, "Clicked ", Snackbar.LENGTH_LONG).show();
//                        setListZone();
//
//                    }
//                });
//                spinMarket.setSelectedIndex(0);
                setListHotel();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setListHotel() {
        dataHotel = new ArrayList<WrapFdbHotel>();
        listHotel = new ArrayList<String>();
        spinHotel.setAdapter(null);

        if (spinMarket != null) {
            int selectItem = spinMarket.getSelectedItemPosition();
            if (selectItem != -1) {
                String market = dataMarket.get(selectItem).getKey();
                mRootRef.child("market-hotel").child(market).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String key = postSnapshot.getKey();
                            FdbHotel value = postSnapshot.getValue(FdbHotel.class);
                            listHotel.add(value.getNameHotel());
                            dataHotel.add(new WrapFdbHotel(key, value));
                        }

//                RV_Adapter_Manage_Market adapterList = new RV_Adapter_Manage_Market(data);
//                rv.setAdapter(adapterList);
                        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listHotel);
                        spinHotel.setAdapter(adapterList);
                        spinHotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                setListItem();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                        //                spinShop.setItems(listShop);
//                spinShop.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//                    @Override
//                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
////                        Log.d("spinMarket", "============= " + listMarketKey.get(position));
////                        Snackbar.make(view, "Clicked ", Snackbar.LENGTH_LONG).show();
//                        setListItem();
//
//                    }
//                });
//                spinShop.setSelectedIndex(0);
                        setListItem();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            } else {
                setListItem();
            }
        }
    }

    private void setListItem() {

        if (spinHotel != null) {
            int selectItem = spinHotel.getSelectedItemPosition();
            if (selectItem != -1) {
                String hotel = dataHotel.get(spinHotel.getSelectedItemPosition()).getKey();
                mRootRef.child("hotel-room").child(hotel).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Object> data = new ArrayList<Object>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String key = postSnapshot.getKey();
                            FdbRoom value = postSnapshot.getValue(FdbRoom.class);
                            data.add(new WrapFdbRoom(key, value));
                        }

                        tvTitle.setText("ห้องพัก (" + data.size() + ")");
                        WrapFdbMarket tempMarket = dataMarket.get(spinMarket.getSelectedItemPosition());
                        WrapFdbHotel tempHotel = dataHotel.get(spinHotel.getSelectedItemPosition());
                        RV_Adapter_Manage_Room adapterList = new RV_Adapter_Manage_Room(data, tempMarket, tempHotel);
                        rv.setAdapter(adapterList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else {
                rv.setAdapter(null);
            }
        }
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
        spinMarket = (Spinner) findViewById(R.id.spin_market);
        spinHotel = (Spinner) findViewById(R.id.spin_hotel);
        btnAdd = (Button) findViewById(R.id.btn_add);

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
