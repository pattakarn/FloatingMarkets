package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_List_Service;

public class ServiceListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int[] imageService = {R.drawable.service1, R.drawable.service2, R.drawable.service3, R.drawable.service4, R.drawable.service5};
        String[] listService = {"จัดอบรม ประชุม สัมนา", "ท่องเที่ยวทางน้ำ", "สถานที่จัดงานอีเว้นท์", "สอนพายเรือ", "สอนรำไทย"};

        RV_Adapter_List_Service adapter = new RV_Adapter_List_Service(this, listService, imageService);

        ListView listView = (ListView)findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
