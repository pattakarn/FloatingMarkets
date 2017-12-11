package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Zone_List;

public class ZoneActivity extends AppCompatActivity {

    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        Bundle bundle = getIntent().getExtras();
        String numberZone = bundle.getString("NumberZone", "");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("โซน " + numberZone);

        String listShop[];
        if (numberZone.equals("1")) {
            listShop = getResources().getStringArray(R.array.zone1_shop);
        } else if (numberZone.equals("2")) {
            listShop = getResources().getStringArray(R.array.zone2_shop);
        } else {
            listShop = getResources().getStringArray(R.array.zone_shop);
        }

//        RV_Adapter_Zone_Menu adapter = new RV_Adapter_Zone_Menu(this, listShop);
//        gridview.setAdapter(adapter);

        RV_Adapter_Zone_List adapter = new RV_Adapter_Zone_List(this, listShop);
        ListView listView = (ListView)findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
