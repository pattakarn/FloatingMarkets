package com.istyleglobalnetwork.floatingmarkets.activity.manage;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Edit_Product;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class EditProductActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;

    WrapFdbZone itemZone = null;
    WrapFdbMarket itemMarket = null;
    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;
    List<WrapFdbImage> itemImage = new ArrayList<WrapFdbImage>();

    DatabaseReference mRootRef;
    ArrayList<Object> dataItem = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

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

            itemMarket = Parcels.unwrap(bundle.getParcelable("itemMarket"));
            if (itemMarket != null) {
//                tvMarket.setText(itemMarket.getData().getNameMarket());
            }

            itemZone = Parcels.unwrap(bundle.getParcelable("itemZone"));
            if (itemZone != null) {
//                tvZone.setText(itemZone.getData().getNameZone());
            }

            itemShop = Parcels.unwrap(bundle.getParcelable("itemShop"));
            if (itemShop != null) {
//                tvShop.setText(itemShop.getData().getNameShop());
            }

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));
            if (itemProduct != null) {
//                setData();
            } else {
                DatabaseReference mProductRef = mRootRef.child("product");
                String newKeyProduct = mProductRef.push().getKey();
                Log.d("newKeyProduct", "++++++++++++++ " + newKeyProduct);

                FdbProduct newProduct = new FdbProduct();
                itemProduct = new WrapFdbProduct(newKeyProduct, newProduct);
            }

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        setData();

    }

    private void setData() {
        mRootRef.child("product").child(itemProduct.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbProduct value = dataSnapshot.getValue(FdbProduct.class);

                dataItem = new ArrayList<Object>();
                List<String> dataHead = new ArrayList<String>();
                dataHead.add(itemMarket.getData().getNameMarket());
                dataHead.add(itemZone.getData().getNameZone());
                dataHead.add(itemShop.getData().getNameShop());
                dataItem.add(dataHead);

                List<Object> temp = new ArrayList<Object>();
                if (value != null)
                    itemProduct = new WrapFdbProduct(key, value);
                itemProduct.getData().setMarketID(itemMarket.getKey());
                itemProduct.getData().setZoneID(itemZone.getKey());
                itemProduct.getData().setShopID(itemShop.getKey());
                temp.add(itemProduct);
                temp.add(itemShop);
                dataItem.add(temp);

                setListImage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setListImage() {
        mRootRef.child("item-photo").child(itemProduct.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemImage = new ArrayList<WrapFdbImage>();

                if (dataItem.size() == 3) {
                    dataItem.remove(2);
                }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
                }

                List<Object> temp = new ArrayList<Object>();
                temp.add(itemProduct);
                temp.add(itemImage);
                dataItem.add(temp);

                RV_Adapter_Edit_Product adapterList = new RV_Adapter_Edit_Product(dataItem);
                rv.setAdapter(adapterList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initInstances() {
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

    @Override
    protected void onResume() {
        super.onResume();
        CountDownTimer cdt = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Tick
            }

            public void onFinish() {
                // Finish
                setListImage();
            }
        }.start();

    }

}
