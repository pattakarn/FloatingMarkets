package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbStockList;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbStockList;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_StockList;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ManageStockListActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Button btnAdd;
    int imageItem;

    WrapFdbProduct itemProduct = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_stock_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        mRootRef.child("stock-list").child(itemProduct.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbStockList value = postSnapshot.getValue(FdbStockList.class);
                    data.add(new WrapFdbStockList(key, value));
                }

                RV_Adapter_Manage_StockList adapterList = new RV_Adapter_Manage_StockList(data);
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
