package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.activity.hotel.HotelListActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.network.NetworkListActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.service.ServiceListActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.travel.TravelActivity;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Main;
import com.istyleglobalnetwork.floatingmarkets.data.DataMainMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();
//        setSupportActionBar(toolbar);

//        RV_Adapter_Main_Menu adapter = new RV_Adapter_Main_Menu(this);
//        gridview.setAdapter(adapter);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(new DataMainMenu(InformationActivity.class, "ประวัติ"));
        data.add(new DataMainMenu(HomeZoneActivity.class, "โซน"));
        data.add(new DataMainMenu(TravelActivity.class, "สถานที่ท่องเที่ยว"));
        data.add(new DataMainMenu(MapsActivity.class, "แผนที่"));
        data.add(new DataMainMenu(NetworkListActivity.class, "เครือข่าย"));
        data.add(new DataMainMenu(ServiceListActivity.class, "บริการ"));
        data.add(new DataMainMenu(CarParkActivity.class, "ลานจอดรถ"));
        data.add(new DataMainMenu(HotelListActivity.class, "ที่พัก"));
        data.add(new DataMainMenu(ContactUsActivity.class, "ติดต่อเรา"));
        data.add(new DataMainMenu(null, "Dashboard"));

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

}
