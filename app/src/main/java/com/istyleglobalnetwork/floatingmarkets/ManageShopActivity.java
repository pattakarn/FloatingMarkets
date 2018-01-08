package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_Shop;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ManageShopActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    MaterialSpinner spinner;
    MaterialSpinner spinner2;
    Button btnAdd;
    int imageItem;

    List<WrapFdbMarket> dataMarket = new ArrayList<WrapFdbMarket>();
    List<WrapFdbZone> dataZone = new ArrayList<WrapFdbZone>();
    List<String> listMarket = new ArrayList<String>();
    List<String> listZone = new ArrayList<String>();


    String selectKey;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shop);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

//        spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
//        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
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
                Intent intent = new Intent(ManageShopActivity.this, EditShopActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemMarket", Parcels.wrap(dataMarket.get(spinner.getSelectedIndex())));
                bundle.putParcelable("itemZone", Parcels.wrap(dataZone.get(spinner2.getSelectedIndex())));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setListMarket(){

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
                spinner.setItems(listMarket);
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                        Log.d("spinner", "============= " + listMarketKey.get(position));
//                        Snackbar.make(view, "Clicked ", Snackbar.LENGTH_LONG).show();
                        setListZone();

                    }
                });
                spinner.setSelectedIndex(0);
                setListZone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setListZone(){
        String market = dataMarket.get(spinner.getSelectedIndex()).getKey();
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
                spinner2.setItems(listZone);
                spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                        Log.d("spinner", "============= " + listMarketKey.get(position));
//                        Snackbar.make(view, "Clicked ", Snackbar.LENGTH_LONG).show();
                        setListItem();

                    }
                });
                spinner2.setSelectedIndex(0);
                setListItem();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setListItem() {
        String zone = dataZone.get(spinner2.getSelectedIndex()).getKey();
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
                RV_Adapter_Manage_Shop adapterList = new RV_Adapter_Manage_Shop(data, dataZone.get(spinner2.getSelectedIndex()));
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
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner2 = (MaterialSpinner) findViewById(R.id.spinner2);
        btnAdd = (Button) findViewById(R.id.btn_add);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
