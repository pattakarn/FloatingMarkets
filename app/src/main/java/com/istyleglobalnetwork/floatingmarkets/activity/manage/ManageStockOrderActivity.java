package com.istyleglobalnetwork.floatingmarkets.activity.manage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_StockOrder;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ManageStockOrderActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Button btnAdd;
    int imageItem;

    WrapFdbProduct itemProduct = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_stock_order);

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

//        WrapFdbMarket itemMarket = Parcels.unwrap(getIntent().getExtras().getParcelableExtra("itemMarket"));
        if (bundle != null) {

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));

        }
        tvTitle.setText(itemProduct.getData().getNameProduct());

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
        setListItem();
    }

    private void setListItem() {

        mRootRef.child("order").orderByChild("productID").equalTo(itemProduct.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbOrder value = postSnapshot.getValue(FdbOrder.class);
                    data.add(new WrapFdbOrder(key, value));
                }

                RV_Adapter_Manage_StockOrder adapterList = new RV_Adapter_Manage_StockOrder(data);
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
