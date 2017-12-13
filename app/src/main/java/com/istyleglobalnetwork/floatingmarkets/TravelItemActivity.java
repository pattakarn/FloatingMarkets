package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Travel_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataTravelItem;

import java.util.ArrayList;

public class TravelItemActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    String nameItem;
    int imageItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        nameItem = bundle.getString("NameTravel", "");
        imageItem = bundle.getInt("ImageTravel");

        initInstances();
        tvTitle.setText(nameItem);

        DataTravelItem travelItem = new DataTravelItem(imageItem, nameItem);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(travelItem);
        data.add("contact");
        data.add("location");
        data.add("rating");
        data.add("ไอศรีมที่เป็น Signature ของตลาดน้ำคลองลัดมะยม ใครที่มาตลาดน้ำแล้วไม่ได้กิน ถือว่าพลาดมากครับ");

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Travel_Item adapterList = new RV_Adapter_Travel_Item(data);
        rv.setAdapter(adapterList);
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
