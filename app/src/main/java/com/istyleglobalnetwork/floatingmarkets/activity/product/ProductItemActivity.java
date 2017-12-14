package com.istyleglobalnetwork.floatingmarkets.activity.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Product_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductItem;

import java.util.ArrayList;

public class ProductItemActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    String nameShop;
    String nameItem;
    int imageItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        nameShop = bundle.getString("NameShop", "");
        nameItem = bundle.getString("NameItem", "");
        imageItem = bundle.getInt("ImageItem");

        initInstances();
        tvTitle.setText(nameItem);

        DataProductItem productItem = new DataProductItem(nameShop, imageItem, nameItem);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(productItem);
        data.add(nameShop);
        data.add("ไอศรีมที่เป็น Signature ของตลาดน้ำคลองลัดมะยม ใครที่มาตลาดน้ำแล้วไม่ได้กิน ถือว่าพลาดมากครับ");
        data.add(nameItem);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Product_Item adapterList = new RV_Adapter_Product_Item(data);
        rv.setAdapter(adapterList);
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
