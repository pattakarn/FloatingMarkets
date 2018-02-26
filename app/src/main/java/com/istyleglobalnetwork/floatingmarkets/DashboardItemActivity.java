package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Dashboard_Item_Market;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Dashboard_Item_Product;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Dashboard_Item_Shop;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Dashboard_Item_Zone;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductOrder;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class DashboardItemActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tvTitle;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Object> dataList;

    WrapFdbZone itemZone = null;
    WrapFdbMarket itemMarket = null;
    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;
    String itemKey = "";
    String mode = "";

    DatabaseReference mRootRef;
    int count;
    int countRound;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_item);

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
                itemKey = itemMarket.getKey();
                mode = "market";
                tvTitle.setText(itemMarket.getData().getNameMarket());
                setMarketProduct();
            }

            itemZone = Parcels.unwrap(bundle.getParcelable("itemZone"));
            if (itemZone != null) {
//                tvZone.setText(itemZone.getData().getNameZone());
                itemKey = itemZone.getKey();
                mode = "zone";
                tvTitle.setText(itemZone.getData().getNameZone());
                setZoneProduct();
            }

            itemShop = Parcels.unwrap(bundle.getParcelable("itemShop"));
            if (itemShop != null) {
//                tvShop.setText(itemShop.getData().getNameShop());
                itemKey = itemShop.getKey();
                mode = "shop";
                tvTitle.setText(itemShop.getData().getNameShop());
                setShopProduct();
            }

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));
            if (itemProduct != null) {
//                etName.setText(itemProduct.getData().getNameProduct());
//                setListImage();
                itemKey = itemProduct.getKey();
                mode = "product";
                tvTitle.setText(itemProduct.getData().getNameProduct());
                setProductOrder();
            } else {
            }


        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switch (mode) {
                    case "product":
                        setProductOrder();
                        break;
                    case "shop":
                        setShopProduct();
                        break;
                    case "zone":
                        setZoneProduct();
                        break;
                    case "market":
                        setMarketProduct();
                        break;
                }

            }
        });

    }

    public void setProductOrder() {
        dataList = new ArrayList<Object>();
        mRootRef.child("order").orderByChild("productID").equalTo(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<WrapFdbOrder> data = new ArrayList<WrapFdbOrder>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbOrder value = postSnapshot.getValue(FdbOrder.class);
//                    if (value.getProductID().equals(itemKey)) {
                    data.add(new WrapFdbOrder(key, value));
//                    }

                }

                dataList.add(data);
                dataList.add(data);

                RV_Adapter_Dashboard_Item_Product adapterList = new RV_Adapter_Dashboard_Item_Product(dataList);
                rv.setAdapter(adapterList);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setShopProduct() {
        dataList = new ArrayList<Object>();
        mRootRef.child("shop-product").child(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<WrapFdbProduct> dataProduct = new ArrayList<WrapFdbProduct>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbProduct value = postSnapshot.getValue(FdbProduct.class);
                    dataProduct.add(new WrapFdbProduct(key, value));

                }

                setShopProductOrder(dataProduct);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setShopProductOrder(final List<WrapFdbProduct> listProduct) {
        dataList = new ArrayList<Object>();
        mRootRef.child("order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DataProductOrder> dataProductOrders = new ArrayList<DataProductOrder>();

                for (WrapFdbProduct product : listProduct) {
                    dataProductOrders.add(new DataProductOrder(product));
                }

                List<WrapFdbOrder> data = new ArrayList<WrapFdbOrder>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbOrder value = postSnapshot.getValue(FdbOrder.class);

                    for (int i = 0; i < dataProductOrders.size(); i++) {
                        if (dataProductOrders.get(i).getProduct().getKey().equals(value.getProductID())) {
                            dataProductOrders.get(i).getOrder().add(new WrapFdbOrder(key, value));
                        }
                    }

                }

                dataList.add(dataProductOrders);
                dataList.add(dataProductOrders);

                RV_Adapter_Dashboard_Item_Shop adapterList = new RV_Adapter_Dashboard_Item_Shop(dataList);
                rv.setAdapter(adapterList);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setZoneProduct() {
        mRootRef.child("product").orderByChild("zoneID").equalTo(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DataProductOrder> productOrders = new ArrayList<DataProductOrder>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbProduct value = postSnapshot.getValue(FdbProduct.class);

                    Log.d("setZoneProduct", "================== " + value.getNameProduct());
                    productOrders.add(new DataProductOrder(new WrapFdbProduct(key, value)));
//                    dataShopProductOrders.add(new DataShopProductOrder(new WrapFdbShop(key, value)));

                }

//                setZoneShopProduct(dataShopProductOrders);
                setZoneProductOrder(productOrders);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setZoneProductOrder(final List<DataProductOrder> productOrders) {
        dataList = new ArrayList<Object>();
        countRound = 0;
        for (int i = 0; i < productOrders.size(); i++) {
            index = i;
            final String productKey = productOrders.get(index).getProduct().getKey();
            mRootRef.child("order").orderByChild("productID").equalTo(productKey).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String key = postSnapshot.getKey();
                        FdbOrder value = postSnapshot.getValue(FdbOrder.class);

                        Log.d("Order ID", "================== " + key);
                        productOrders.get(index).getOrder().add(new WrapFdbOrder(key, value));

                    }

                    countRound++;
                    if (countRound == productOrders.size()) {
                        Log.d("countRound", "================== " + countRound);
                        dataList.add(productOrders);
                        dataList.add(productOrders);
                        RV_Adapter_Dashboard_Item_Zone adapterList = new RV_Adapter_Dashboard_Item_Zone(dataList);
                        rv.setAdapter(adapterList);
                        swipeRefreshLayout.setRefreshing(false);
                    }
//
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (productOrders.size() == 0) {
            dataList.add(productOrders);
            dataList.add(productOrders);
            RV_Adapter_Dashboard_Item_Zone adapterList = new RV_Adapter_Dashboard_Item_Zone(dataList);
            rv.setAdapter(adapterList);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public void setMarketProduct() {
        mRootRef.child("product").orderByChild("marketID").equalTo(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DataProductOrder> productOrders = new ArrayList<DataProductOrder>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbProduct value = postSnapshot.getValue(FdbProduct.class);

                    Log.d("setMarketProduct", "================== " + value.getNameProduct());
                    productOrders.add(new DataProductOrder(new WrapFdbProduct(key, value)));
//                    dataShopProductOrders.add(new DataShopProductOrder(new WrapFdbShop(key, value)));

                }

//                setZoneShopProduct(dataShopProductOrders);
                setMarketProductOrder(productOrders);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setMarketProductOrder(final List<DataProductOrder> productOrders) {
        dataList = new ArrayList<Object>();
        countRound = 0;
        for (int i = 0; i < productOrders.size(); i++) {
            index = i;
            final String productKey = productOrders.get(index).getProduct().getKey();
            mRootRef.child("order").orderByChild("productID").equalTo(productKey).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String key = postSnapshot.getKey();
                        FdbOrder value = postSnapshot.getValue(FdbOrder.class);

                        Log.d("Order ID", "================== " + key);
                        productOrders.get(index).getOrder().add(new WrapFdbOrder(key, value));

                    }

                    countRound++;
                    if (countRound == productOrders.size()) {
                        Log.d("countRound", "================== " + countRound);
                        dataList.add(productOrders);
                        dataList.add(productOrders);
                        RV_Adapter_Dashboard_Item_Market adapterList = new RV_Adapter_Dashboard_Item_Market(dataList);
                        rv.setAdapter(adapterList);
                        swipeRefreshLayout.setRefreshing(false);
                    }
//
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (productOrders.size() == 0) {
            dataList.add(productOrders);
            dataList.add(productOrders);
            RV_Adapter_Dashboard_Item_Market adapterList = new RV_Adapter_Dashboard_Item_Market(dataList);
            rv.setAdapter(adapterList);
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    private void initInstances() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
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
