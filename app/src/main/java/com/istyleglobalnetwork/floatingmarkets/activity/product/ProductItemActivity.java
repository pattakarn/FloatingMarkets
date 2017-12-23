package com.istyleglobalnetwork.floatingmarkets.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.CartListActivity;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Product_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductItem;

import java.util.ArrayList;

public class ProductItemActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    String nameShop;
    String nameItem;
    Button btnBuy;
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

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductItemActivity.this, CartListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
        btnBuy = (Button) findViewById(R.id.btn_buy);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_cart:
                Intent intent = new Intent(ProductItemActivity.this, CartListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
