package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Dashboard_Item_Product;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Dashboard_Item_Shop;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Dashboard_Item_Zone;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductOrder;
import com.istyleglobalnetwork.floatingmarkets.data.DataShopProductOrder;

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
            }

            itemZone = Parcels.unwrap(bundle.getParcelable("itemZone"));
            if (itemZone != null) {
//                tvZone.setText(itemZone.getData().getNameZone());
                itemKey = itemZone.getKey();
                mode = "zone";
                tvTitle.setText(itemZone.getData().getNameZone());
                setZoneShop();
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
                        setZoneShop();
                        break;
                }

            }
        });


//        LineChart chart = (LineChart) findViewById(R.id.chart);
//        BarChart chart = (BarChart) findViewById(R.id.barchart);

//        List<Entry> entries = new ArrayList<Entry>();
//        for (int i = 0; i<5;i++){
//            entries.add(new BarEntry(i, i*10));
//        }
//        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
//        dataSet.setColor(Color.GREEN);
//        dataSet.setValueTextColor(Color.RED);
//
//        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//        dataSets.add(dataSet);
//
//        LineData lineData = new LineData(dataSet);
//        chart.setData(lineData);
//        chart.invalidate();

//        List<Entry> valsComp1 = new ArrayList<Entry>();
//        List<Entry> valsComp2 = new ArrayList<Entry>();
//        Entry c1e1 = new Entry(0f, 100000f); // 0 == quarter 1
//        valsComp1.add(c1e1);
//        Entry c1e2 = new Entry(1f, 140000f); // 1 == quarter 2 ...
//        valsComp1.add(c1e2);
//        // and so on ...
//
//        Entry c2e1 = new Entry(0f, 130000f); // 0 == quarter 1
//        valsComp2.add(c2e1);
//        Entry c2e2 = new Entry(1f, 115000f); // 1 == quarter 2 ...
//        valsComp2.add(c2e2);
//
//        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
//        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        setComp1.setColor(Color.RED);
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2");
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//        dataSets.add(setComp1);
//        dataSets.add(setComp2);
//
//        LineData data = new LineData(dataSets);
//        chart.setData(data);
//        chart.invalidate(); // refresh

//        final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4", "Q5" };
//        IAxisValueFormatter formatter = new IAxisValueFormatter() {
//
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return quarters[(int) value];
//            }
//
//            // we don't draw numbers, so no decimal digits needed
//        };
//
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setGranularity(1); // minimum axis-step (interval) is 1
//        xAxis.setValueFormatter(formatter);

    }

    public void setProductOrder() {
        dataList = new ArrayList<Object>();
        mRootRef.child("order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<WrapFdbOrder> data = new ArrayList<WrapFdbOrder>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbOrder value = postSnapshot.getValue(FdbOrder.class);
                    if (value.getProductID().equals(itemKey)) {
                        data.add(new WrapFdbOrder(key, value));
                    }

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

                RV_Adapter_Dashboard_Item_Shop adapterList = new RV_Adapter_Dashboard_Item_Shop(dataList);
                rv.setAdapter(adapterList);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setZoneShop() {
        mRootRef.child("zone-shop").child(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DataShopProductOrder> dataShopProductOrders = new ArrayList<DataShopProductOrder>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbShop value = postSnapshot.getValue(FdbShop.class);

                    dataShopProductOrders.add(new DataShopProductOrder(new WrapFdbShop(key, value)));

                }

                setZoneShopProduct(dataShopProductOrders);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setZoneShopProduct(final List<DataShopProductOrder> dataShopProductOrders) {
        countRound = 0;
        for (int i = 0; i < dataShopProductOrders.size(); i++) {
            count = i;
            String shopKey = dataShopProductOrders.get(i).getShop().getKey();
//            Log.d("shop-product key", shopKey);
            mRootRef.child("shop-product").child(shopKey).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String key = postSnapshot.getKey();
                        FdbProduct value = postSnapshot.getValue(FdbProduct.class);

//                        Log.d("shop-product key", value.getNameProduct() + " - " + key);
                        dataShopProductOrders.get(count).getProductOrders().add(new DataProductOrder(new WrapFdbProduct(key, value)));
//                    for (int i = 0; i < dataShopProductOrders.size(); i++) {
//                        Log.d("shop-product key", dataShopProductOrders.get(i).getShop().getKey() + " - " + key);
//                        if (dataShopProductOrders.get(i).getShop().getKey().equals(key)) {
//                            Log.d("shop-product", value.getNameProduct());
//                            dataShopProductOrders.get(i).getProductOrders().add(new DataProductOrder(new WrapFdbProduct(key, value)));
//                        }
//                    }
                    }

                    countRound+=1;
                    if (countRound == dataShopProductOrders.size()) {
                        setZoneShopProductOrder(dataShopProductOrders);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void setZoneShopProductOrder(final List<DataShopProductOrder> dataShopProductOrders) {
        dataList = new ArrayList<Object>();
        mRootRef.child("order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbOrder value = postSnapshot.getValue(FdbOrder.class);

//                    Log.d("order key", value.getProductID() + " - " + dataShopProductOrders.get(i).getProductOrders().get(j).getProduct().getKey());

                    for (int i = 0; i < dataShopProductOrders.size(); i++) {
//                        Log.d("order - shop", value.getProductID() + " - " + dataShopProductOrders.get(i).getShop().getData().getNameShop());
                        for (int j = 0; j < dataShopProductOrders.get(i).getProductOrders().size(); j++) {
//                            Log.d("order key", value.getProductID() + " - " + dataShopProductOrders.get(i).getProductOrders().get(j).getProduct().getKey());
                            if (dataShopProductOrders.get(i).getProductOrders().get(j).getProduct().getKey().equals(value.getProductID())) {
                                WrapFdbOrder tempOrder = new WrapFdbOrder(key, value);
                                dataShopProductOrders.get(i).getProductOrders().get(j).getOrder().add(tempOrder);
                            }
                        }
                    }
                }

                dataList.add(dataShopProductOrders);

                RV_Adapter_Dashboard_Item_Zone adapterList = new RV_Adapter_Dashboard_Item_Zone(dataList);
                rv.setAdapter(adapterList);
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
