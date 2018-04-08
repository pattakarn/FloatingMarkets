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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_Network;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ManageNetworkActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Spinner spinMarket;
    Button btnAdd;
    int imageItem;

    List<WrapFdbMarket> dataMarket = new ArrayList<WrapFdbMarket>();
    List<String> listMarket = new ArrayList<String>();

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_network);

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
                Intent intent = new Intent(ManageNetworkActivity.this, EditNetworkActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemMarket", Parcels.wrap(dataMarket.get(spinMarket.getSelectedItemPosition())));
                intent.putExtras(bundle);
                startActivity(intent);
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
                        setListItem();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                setListItem();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setListItem() {

        String market = dataMarket.get(spinMarket.getSelectedItemPosition()).getKey();
        mRootRef.child("market-network").child(market).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbNetwork value = postSnapshot.getValue(FdbNetwork.class);
                    data.add(new WrapFdbNetwork(key, value));

                }

                tvTitle.setText("เครือข่าย (" + data.size() + ")");
                RV_Adapter_Manage_Network adapterList = new RV_Adapter_Manage_Network(data, dataMarket.get(spinMarket.getSelectedItemPosition()));
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
        spinMarket = (Spinner) findViewById(R.id.spin_market);
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
