package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Zone;

import java.util.ArrayList;

public class HomeZoneActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_zone);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(R.drawable.zone_1);
        data.add(R.drawable.zone_2);
        data.add(R.drawable.zone_3);
        data.add(R.drawable.zone_4);
        data.add(R.drawable.zone_5);
        data.add(R.drawable.zone_6);
        data.add(R.drawable.zone_7);
        data.add(R.drawable.zone_8);

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(glm);
        RV_Adapter_Zone adapterList = new RV_Adapter_Zone(data);
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
