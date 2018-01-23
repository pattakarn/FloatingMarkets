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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbZone;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_Product;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ManageProductActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Spinner spinMarket;
    Spinner spinZone;
    Spinner spinShop;
    Button btnAdd;
    int imageItem;

    List<WrapFdbMarket> dataMarket = new ArrayList<WrapFdbMarket>();
    List<WrapFdbZone> dataZone = new ArrayList<WrapFdbZone>();
    List<WrapFdbShop> dataShop = new ArrayList<WrapFdbShop>();

    List<String> listMarket = new ArrayList<String>();
    List<String> listZone = new ArrayList<String>();
    List<String> listShop = new ArrayList<String>();


    String selectKey;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);

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
                } else if (spinShop.getSelectedItemPosition() == -1) {
                    Toast.makeText(getApplicationContext(), "Pleace select shop.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ManageProductActivity.this, EditProductActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("itemMarket", Parcels.wrap(dataMarket.get(spinMarket.getSelectedItemPosition())));
                    bundle.putParcelable("itemZone", Parcels.wrap(dataZone.get(spinZone.getSelectedItemPosition())));
                    bundle.putParcelable("itemShop", Parcels.wrap(dataShop.get(spinShop.getSelectedItemPosition())));
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
                setListZone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setListZone() {
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
                            setListShop();
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
                    setListShop();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            setListShop();
        }
    }

    private void setListShop() {
        dataShop = new ArrayList<WrapFdbShop>();
        listShop = new ArrayList<String>();
        spinShop.setAdapter(null);

        if (spinZone != null) {
            int selectItem = spinZone.getSelectedItemPosition();
            if (selectItem != -1) {
                String zone = dataZone.get(selectItem).getKey();
                mRootRef.child("zone-shop").child(zone).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String key = postSnapshot.getKey();
                            FdbShop value = postSnapshot.getValue(FdbShop.class);
                            listShop.add(value.getNameShop());
                            dataShop.add(new WrapFdbShop(key, value));
                        }

//                RV_Adapter_Manage_Market adapterList = new RV_Adapter_Manage_Market(data);
//                rv.setAdapter(adapterList);
                        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listShop);
                        spinShop.setAdapter(adapterList);
                        spinShop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        if (spinShop != null) {
            int selectItem = spinShop.getSelectedItemPosition();
            if (selectItem != -1) {
                String shop = dataShop.get(spinShop.getSelectedItemPosition()).getKey();
                mRootRef.child("shop-product").child(shop).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Object> data = new ArrayList<Object>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            String key = postSnapshot.getKey();
                            FdbProduct value = postSnapshot.getValue(FdbProduct.class);
                            data.add(new WrapFdbProduct(key, value));
                        }

                        tvTitle.setText("สินค้า (" + data.size() + ")");
                        WrapFdbMarket tempMarket = dataMarket.get(spinMarket.getSelectedItemPosition());
                        WrapFdbZone tempZone = dataZone.get(spinZone.getSelectedItemPosition());
                        WrapFdbShop tempShop = dataShop.get(spinShop.getSelectedItemPosition());
                        RV_Adapter_Manage_Product adapterList = new RV_Adapter_Manage_Product(data, tempMarket, tempZone, tempShop);
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
        spinShop = (Spinner) findViewById(R.id.spin_shop);
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
