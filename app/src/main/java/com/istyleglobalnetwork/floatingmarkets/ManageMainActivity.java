package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Main;
import com.istyleglobalnetwork.floatingmarkets.data.DataMainMenu;

import java.util.ArrayList;

public class ManageMainActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(new DataMainMenu(ManageMarketActivity.class, "ตลาด"));
        data.add(new DataMainMenu(ManageZoneActivity.class, "โซน"));
        data.add(new DataMainMenu(ManageShopActivity.class, "ร้านค้า"));
        data.add(new DataMainMenu(ManageProductActivity.class, "สินค้า"));
        data.add(new DataMainMenu(ManageStockActivity.class, "สต๊อค"));

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(glm);
        RV_Adapter_Main adapterList = new RV_Adapter_Main(data);
        rv.setAdapter(adapterList);
    }

    private void initInstances() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
