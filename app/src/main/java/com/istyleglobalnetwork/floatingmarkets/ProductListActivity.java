package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Product_List;

public class ProductListActivity extends AppCompatActivity {

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

        final int[] imageItem = {R.drawable.ice1, R.drawable.ice2, R.drawable.ice3};
        final String[] listItem = {"ไอติมโบรานใส่ถ้วย", "ไอติมโบรานใส่ขนมปัง", "ไอติมโบรานใส่มะพร้าว"};
        RV_Adapter_Product_List adapter = new RV_Adapter_Product_List(this, listItem, imageItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), ProductItemActivity.class);
                intent.putExtra("NameShop", nameShop);
                intent.putExtra("NameItem", listItem[i]);
                intent.putExtra("ImageItem", imageItem[i]);
                startActivity(intent);
            }
        });
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
