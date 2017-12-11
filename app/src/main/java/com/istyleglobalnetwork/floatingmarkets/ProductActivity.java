package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Product;

public class ProductActivity extends AppCompatActivity {

    TextView tv_title;
    ListView listView;
    String nameShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        nameShop = bundle.getString("NameShop", "");

        initInstances();

        tv_title.setText(nameShop + "\nสินค้า");

        String[] listShop = {"ไอติมโบรานใส่ถ้วย", "ไอติมโบรานใส่ขนมปัง", "ไอติมโบรานใส่มะพร้าว"};
        RV_Adapter_Product adapter = new RV_Adapter_Product(this, listShop);
        listView.setAdapter(adapter);
    }

    private void initInstances() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        listView = (ListView) findViewById(R.id.lv);
//        iv_company = (ImageView) rootView.findViewById(R.id.iv_company);
//        iv_company.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
