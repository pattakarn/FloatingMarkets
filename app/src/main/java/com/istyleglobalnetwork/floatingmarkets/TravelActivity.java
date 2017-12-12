package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_List_Travel;

public class TravelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int[] imageTravel = {R.drawable.tra1, R.drawable.tra2, R.drawable.tra3, R.drawable.tra4};
        String[] listTravel = {"สวนกล้วยไม้ลุงนิยม", "สวนแก้วมังกร", "ศูนย์การเรียนรู้การเพิ่มประสิทธิภาพการผลิตสินค้าเกษตร", "ศูนย์การเรียนรู้เห็ดแบบครบวงจร"};

        RV_Adapter_List_Travel adapter = new RV_Adapter_List_Travel(this, listTravel, imageTravel);

        ListView listView = (ListView)findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
