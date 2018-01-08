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
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_Market;

import java.util.ArrayList;
import java.util.List;

public class ManageMarketActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Button btnAdd;
    int imageItem;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_market);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();

//        Bundle bundle = getIntent().getExtras();
//        nameShop = bundle.getString("NameShop", "");
//        nameItem = bundle.getString("NameItem", "");
//        imageItem = bundle.getInt("ImageItem");
//
//        initInstances();
//        tvTitle.setText(nameItem);
//
//        DataProductItem productItem = new DataProductItem(nameShop, imageItem, nameItem);
//
//        ArrayList<Object> data = new ArrayList<Object>();
//        data.add(productItem);
//        data.add(nameShop);
//        data.add("ไอศรีมที่เป็น Signature ของตลาดน้ำคลองลัดมะยม ใครที่มาตลาดน้ำแล้วไม่ได้กิน ถือว่าพลาดมากครับ");
//        data.add(nameItem);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
//        RV_Adapter_Product_Item adapterList = new RV_Adapter_Product_Item(data);
//        rv.setAdapter(adapterList);
//
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("market").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbMarket value = postSnapshot.getValue(FdbMarket.class);
                    data.add(new WrapFdbMarket(key, value));
                }

                tvTitle.setText("ตลาด (" + data.size() + ")");
                RV_Adapter_Manage_Market adapterList = new RV_Adapter_Manage_Market(data);
                rv.setAdapter(adapterList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageMarketActivity.this, EditMarketActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
        btnAdd = (Button) findViewById(R.id.btn_add);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
