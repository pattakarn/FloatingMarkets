package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_List_Network;

public class NetworkListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int[] imageNetwork = {R.drawable.network1, R.drawable.network2, R.drawable.network3, R.drawable.network4};
        String[] listNetwork = {"ศูนย์ภูมิรักษ์ธรรมชาติ", "โรงเรียนโพธิสารพิทยากร", "กลุ่มเพราะเห็ด", "สวนผักออแกร์นิค"};

        RV_Adapter_List_Network adapter = new RV_Adapter_List_Network(this, listNetwork, imageNetwork);

        ListView listView = (ListView)findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
