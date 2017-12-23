package com.istyleglobalnetwork.floatingmarkets.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.CartListActivity;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Shop;
import com.istyleglobalnetwork.floatingmarkets.data.DataShopItem;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        Bundle bundle = getIntent().getExtras();
        String numberZone = bundle.getString("NumberZone", "");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();

        tv_title.setText("โซน " + numberZone);

        String listShop[];
        if (numberZone.equals("1")) {
            listShop = getResources().getStringArray(R.array.zone1_shop);
        } else if (numberZone.equals("2")) {
            listShop = getResources().getStringArray(R.array.zone2_shop);
        } else {
            listShop = getResources().getStringArray(R.array.zone_shop);
        }

        ArrayList<Object> data = new ArrayList<Object>();

        for (int i=0; i< listShop.length; i++){
            data.add(new DataShopItem(R.drawable.floating_market_logo, listShop[i]));
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Shop adapterList = new RV_Adapter_Shop(data);
        rv.setAdapter(adapterList);
    }

    private void initInstances() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
//        iv_company = (ImageView) rootView.findViewById(R.id.iv_company);
//        iv_company.setOnClickListener(this);

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
                Intent intent = new Intent(ShopListActivity.this, CartListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
