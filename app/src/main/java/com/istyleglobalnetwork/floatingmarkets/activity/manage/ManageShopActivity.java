package com.istyleglobalnetwork.floatingmarkets.activity.manage;

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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbZone;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_Shop;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ManageShopActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Spinner spinMarket;
    Spinner spinZone;
    Button btnAdd;
    int imageItem;

    List<WrapFdbMarket> dataMarket = new ArrayList<WrapFdbMarket>();
    List<WrapFdbZone> dataZone = new ArrayList<WrapFdbZone>();
    List<String> listMarket = new ArrayList<String>();
    List<String> listZone = new ArrayList<String>();

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shop);

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
                if (spinZone.getSelectedItemPosition() == -1) {
                    Toast.makeText(getApplicationContext(), "Pleace select zone.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ManageShopActivity.this, EditHotelActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("itemMarket", Parcels.wrap(dataMarket.get(spinMarket.getSelectedItemPosition())));
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
                        setListZone();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                setListZone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setListZone() {
//        String market = dataMarket.get(spinner.getSelectedIndex()).getKey();
//        mRootRef.child("market-zone").child(market).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    String key = postSnapshot.getKey();
//                    FdbZone value = postSnapshot.getValue(FdbZone.class);
//                    listZone.add(value.getNameZone());
//                    dataZone.add(new WrapFdbZone(key, value));
//                }
//
////                RV_Adapter_Manage_Market adapterList = new RV_Adapter_Manage_Market(data);
////                rv.setAdapter(adapterList);
//                spinner2.setItems(listZone);
//                spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//                    @Override
//                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
////                        Log.d("spinMarket", "============= " + listMarketKey.get(position));
////                        Snackbar.make(view, "Clicked ", Snackbar.LENGTH_LONG).show();
//                        setListItem();
//
//                    }
//                });
//                spinner2.setSelectedIndex(0);
//                setListItem();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        dataZone = new ArrayList<WrapFdbZone>();
        listZone = new ArrayList<String>();
        spinZone.setAdapter(null);

        int selectItem = spinMarket.getSelectedItemPosition();
        if (selectItem != -1) {
            String market = dataMarket.get(selectItem).getKey();
            mRootRef.child("market-zone").child(market).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String key = postSnapshot.getKey();
                        FdbZone value = postSnapshot.getValue(FdbZone.class);
                        listZone.add(value.getNameZone());
                        dataZone.add(new WrapFdbZone(key, value));
                    }

//                RV_Adapter_Manage_Market adapterList = new RV_Adapter_Manage_Market(data);
//                rv.setAdapter(adapterList);
                    ArrayAdapter<String> adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listZone);
                    spinZone.setAdapter(adapterList);
                    spinZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            setListItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
//                spinZone.setItems(listZone);
//                spinZone.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//                    @Override
//                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
////                        Log.d("spinMarket", "============= " + listMarketKey.get(position));
////                        Snackbar.make(view, "Clicked ", Snackbar.LENGTH_LONG).show();
//                        setListShop();
//
//                    }
//                });
//                spinZone.setSelectedIndex(0);
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

    private void setListItem() {
//        String zone = dataZone.get(spinner2.getSelectedIndex()).getKey();
//        mRootRef.child("zone-shop").child(zone).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                List<Object> data = new ArrayList<Object>();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    String key = postSnapshot.getKey();
//                    FdbShop value = postSnapshot.getValue(FdbShop.class);
//                    data.add(new WrapFdbShop(key, value));
//                }
//
//                tvTitle.setText("ร้านค้า (" + data.size() + ")");
//                WrapFdbMarket tempMarket = dataMarket.get(spinner.getSelectedIndex());
//                WrapFdbZone tempZone = dataZone.get(spinner.getSelectedIndex());
//                RV_Adapter_Manage_Shop adapterList = new RV_Adapter_Manage_Shop(data, tempMarket, tempZone);
//                rv.setAdapter(adapterList);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        if (spinZone != null) {
            int selectItem = spinZone.getSelectedItemPosition();
            if (selectItem != -1) {
                String zone = dataZone.get(spinZone.getSelectedItemPosition()).getKey();
                mRootRef.child("zone-shop").child(zone).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Object> data = new ArrayList<Object>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String key = postSnapshot.getKey();
                            FdbShop value = postSnapshot.getValue(FdbShop.class);
                            data.add(new WrapFdbShop(key, value));

                        }

                        tvTitle.setText("ร้านค้า (" + data.size() + ")");
                        WrapFdbMarket tempMarket = dataMarket.get(spinMarket.getSelectedItemPosition());
                        WrapFdbZone tempZone = dataZone.get(spinZone.getSelectedItemPosition());
                        RV_Adapter_Manage_Shop adapterList = new RV_Adapter_Manage_Shop(data, tempMarket, tempZone);
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
        spinZone = (Spinner) findViewById(R.id.spin_zone);
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
