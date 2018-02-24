package com.istyleglobalnetwork.floatingmarkets;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbStock;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbStockList;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Product_Cart;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tvTitle;
    TextView tvTotal;
    Button btnConfirm;
    SwipeRefreshLayout swipeRefreshLayout;

    List<WrapFdbOrder> dataOrder;
    List<WrapFdbImage> dataPhoto;
    int total = 0;

    DatabaseReference mRootRef;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

//        Bundle bundle = getIntent().getExtras();
//        String numberZone = bundle.getString("NumberZone", "");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        tvTitle.setText("My Cart");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

//        ArrayList<Object> data = new ArrayList<Object>();
//        data.add("1");
//        data.add("2");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setListOrder();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(CartListActivity.this, LoginActivity2.class);
            startActivity(intent);
        } else {

            setListOrder();

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(CartListActivity.this);
                    builder.setMessage("ยืนยันการสั่งซื้อ ?");
                    builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(CartListActivity.this,
                                    "ขอบคุณครับ", Toast.LENGTH_SHORT).show();
                            setConfirm();
                            finish();
                        }
                    });
                    builder.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                        }
                    });
                    builder.show();


                }
            });
        }


    }

    private void setListOrder() {
        mRootRef.child("user-order").child(currentUser.getUid()).orderByChild("status").equalTo("standby").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataOrder = new ArrayList<WrapFdbOrder>();
                dataPhoto = new ArrayList<WrapFdbImage>();
                total = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final String key = postSnapshot.getKey();
                    FdbOrder value = postSnapshot.getValue(FdbOrder.class);
                    dataOrder.add(new WrapFdbOrder(key, value));
                    total += value.getPrice();
                }

                RV_Adapter_Product_Cart adapterList = new RV_Adapter_Product_Cart(dataOrder);
                rv.setAdapter(adapterList);
                tvTotal.setText(total + " บาท");
//                RV_Adapter_Manage_Market adapterList = new RV_Adapter_Manage_Market(data);
//                rv.setAdapter(adapterList);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setConfirm() {
        for (int i = 0; i < dataOrder.size(); i++) {
            DatabaseReference mOrderRef = mRootRef.child("order");
            DatabaseReference mUserOrderRef = mRootRef.child("user-order");
            DatabaseReference mStockListRef = mRootRef.child("stock-list");

            FdbOrder order = dataOrder.get(i).getData();
            order.setStatus("order");
            mOrderRef.child(dataOrder.get(i).getKey()).setValue(order);
            mUserOrderRef.child(currentUser.getUid()).child(dataOrder.get(i).getKey()).setValue(order);

            String keyStockList = mStockListRef.child(order.getProductID()).push().getKey();
            FdbStockList dataStockList = new FdbStockList();
            dataStockList.setQuantity(order.getQuantity());
            dataStockList.setMark("sell");
            mStockListRef.child(order.getProductID()).child(keyStockList).setValue(dataStockList);

            updateStock(order.getProductID(), order.getQuantity());
        }
    }

    private void updateStock(final String productID, final int orderQuantity) {
        final DatabaseReference mStockRef = mRootRef.child("stock");
        mRootRef.child("stock").child(productID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbStock value = dataSnapshot.getValue(FdbStock.class);

                int sumQuantity = value.getQuantity() - orderQuantity;
                value.setQuantity(sumQuantity);
                mStockRef.child(productID).setValue(value);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initInstances() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        rv = (RecyclerView) findViewById(R.id.rv);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
//        iv_company = (ImageView) rootView.findViewById(R.id.iv_company);
//        iv_company.setOnClickListener(this);

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
