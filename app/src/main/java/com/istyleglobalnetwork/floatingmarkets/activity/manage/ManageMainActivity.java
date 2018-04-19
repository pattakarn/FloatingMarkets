package com.istyleglobalnetwork.floatingmarkets.activity.manage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Manage_Main;
import com.istyleglobalnetwork.floatingmarkets.data.DataMainMenu;

import java.util.ArrayList;

public class ManageMainActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();

        ArrayList<Object> data = new ArrayList<Object>();
        data.add( "ตลาด & ร้านค้า");
        data.add(new DataMainMenu(ManageMarketActivity.class, "ตลาด"));
        data.add(new DataMainMenu(ManageZoneActivity.class, "โซน"));
        data.add(new DataMainMenu(ManageShopActivity.class, "ร้านค้า"));
        data.add(new DataMainMenu(ManageProductActivity.class, "สินค้า"));
        data.add(new DataMainMenu(ManageStockActivity.class, "สต๊อค"));

        data.add( "ที่พัก");
        data.add(new DataMainMenu(ManageHotelActivity.class, "ที่พัก"));
        data.add(new DataMainMenu(ManageRoomActivity.class, "ห้องพัก"));

        data.add( "ท่องเที่ยว & เครือข่าย & บริการ");
        data.add(new DataMainMenu(ManageTravelActivity.class, "ท่องเที่ยว"));
        data.add(new DataMainMenu(ManageNetworkActivity.class, "เครือข่าย"));
        data.add(new DataMainMenu(ManageServiceActivity.class, "บริการ"));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Manage_Main adapterList = new RV_Adapter_Manage_Main(data);
        rv.setAdapter(adapterList);
    }

    private void initInstances() {
        tv_title = (TextView) findViewById(R.id.tv_title);
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
}
