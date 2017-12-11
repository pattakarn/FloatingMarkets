package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Zone_Menu;

public class HomeZoneActivity extends AppCompatActivity {

    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_zone);

        initInstances();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RV_Adapter_Zone_Menu adapter = new RV_Adapter_Zone_Menu(this);
        gridview.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void initInstances() {
        gridview = (GridView) findViewById(R.id.gridView);
//        iv_company = (ImageView) rootView.findViewById(R.id.iv_company);
//        iv_company.setOnClickListener(this);

    }
}
