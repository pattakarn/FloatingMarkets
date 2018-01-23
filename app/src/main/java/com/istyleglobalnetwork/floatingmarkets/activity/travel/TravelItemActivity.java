package com.istyleglobalnetwork.floatingmarkets.activity.travel;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        nameItem = bundle.getString("NameTravel", "");
        imageItem = bundle.getInt("ImageTravel");

        initInstances();
        tvTitle.setText(nameItem);

        DataTravelItem travelItem = new DataTravelItem(imageItem, nameItem);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(travelItem);
        data.add("location");
        data.add("time");
        data.add("contact");
        data.add("rating");
        data.add("สวนพฤษศาสตร์ของประเทศสิงคโปร์(Singapore Botanic Garden)เป็นสวนสาธารณะขนาดใหญ่ที่เก่าแก่ มีอายุมากกว่า 150 ปีแล้วและเป็นสวนที่ได้รับการขึ้นทะเบียนเป็นมรดกโลกจากยูเนสโก(UNESCO World Heritage)ด้วย เป็นสวนที่ได้รับการออกแบบและบริหารจัดการเป็นอย่างดี จนได้รับรางวัลมากมายเช่น Best Garden in Asia จากเว็บ TripAdvisor ติดกันมาตั้งแต่ปี 2012 และอื่นๆอีกหลายรางวัล ภายในแบ่งออกเป็นโซนต่างๆที่น่าสนใจมากมายแต่ที่เป็นไฮไลท์สำคัญของสวนแห่งนี้คือ สวนกล้วยไม้แห่งชาติ(National Orchid Garden) เป็นทั้งสถานที่เรียนรู้ และพักผ่อนหย่อนใจของชาวสิงคโปร์และชาวต่างชาติ\n" +
                "\n" +
                "สวนพฤษศาสตร์(Singapore Botanic Garden)สร้างขึ้นตั้งแต่ปีค.ศ. 1859 ในปัจจุบันเป็นสถานีที่สำคัญในการศึกษาเกี่ยวกับพืชในเขตเมืองร้อน โดยเฉพาะกล้วยไม้ ซึ่งเป็นดอกไม้ประจำชาติสิงคโปร์ บนพื้นที่กว่า 500 ไร่ เป็นรูปทรงยาวจากเหนือไปใต้ มีระยะทางที่ยาวที่สุด 2.5 กิโลเมตร มีพืชพันธ์ต่างๆมากกว่า 10,000 สายพันธ์ และมีผู้มาเยี่ยมชมมากกว่า 4.5 ล้านคนต่อปี และเพิ่งได้รับการลงทะเบียนเป็นมรดกโลกเมื่อปี 2015 นี่เอง นอกจากนี้ยังเป็นสวนเพียงแห่งเดียวของโลกที่ปิดดึกมากคือปิดตอนเที่ยงคืน");

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
