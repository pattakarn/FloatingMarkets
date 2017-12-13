package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Travel;
import com.istyleglobalnetwork.floatingmarkets.data.DataTravelItem;

import java.util.ArrayList;

public class NetworkListActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(new DataTravelItem(R.drawable.network1, "ศูนย์ภูมิรักษ์ธรรมชาติ"));
        data.add(new DataTravelItem(R.drawable.network2, "โรงเรียนโพธิสารพิทยากร"));
        data.add(new DataTravelItem(R.drawable.network3, "กลุ่มเพราะเห็ด"));
        data.add(new DataTravelItem(R.drawable.network4, "สวนผักออแกร์นิค"));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Travel adapterList = new RV_Adapter_Travel(data);
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
}
