package com.istyleglobalnetwork.floatingmarkets.activity.service;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbService;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Service_Item;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemActivity extends AppCompatActivity {

    ArrayList<Object> data = new ArrayList<Object>();
    List<Object> itemImage = new ArrayList<Object>();

    TextView tvTitle;
    RecyclerView rv;

    WrapFdbService itemService = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            itemService = Parcels.unwrap(bundle.getParcelable("itemService"));
            if (itemService != null) {
                tvTitle.setText(itemService.getData().getNameService());
            }

        }


//        int[] image;
//        switch (nameItem) {
//            case "จัดอบรม ประชุม สัมนา":
//            case "ท่องเที่ยวทางน้ำ":
//                image = new int[]{R.drawable.train1, R.drawable.train2};
//                break;
//            case "สถานที่จัดงานอีเว้นท์":
//                image = new int[]{R.drawable.event1, R.drawable.event2, R.drawable.event3, R.drawable.event4};
//                break;
//            case "สอนพายเรือ":
//                image = new int[]{R.drawable.boat1, R.drawable.boat2, R.drawable.boat3};
//                break;
//            case "สอนรำไทย":
//                image = new int[]{R.drawable.dance1, R.drawable.dance2, R.drawable.dance3};
//                break;
//            default:
//                image = new int[]{R.drawable.train1, R.drawable.train2};
//                break;
//        }
//
//        DataServiceItem travelItem = new DataServiceItem(image, nameItem);
//
//        ArrayList<Object> data = new ArrayList<Object>();
//        data.add(travelItem);
//        data.add("location");
//        data.add("contact");
//        data.add("rating");
//        data.add("สวนพฤษศาสตร์ของประเทศสิงคโปร์(Singapore Botanic Garden)เป็นสวนสาธารณะขนาดใหญ่ที่เก่าแก่ มีอายุมากกว่า 150 ปีแล้วและเป็นสวนที่ได้รับการขึ้นทะเบียนเป็นมรดกโลกจากยูเนสโก(UNESCO World Heritage)ด้วย เป็นสวนที่ได้รับการออกแบบและบริหารจัดการเป็นอย่างดี จนได้รับรางวัลมากมายเช่น Best Garden in Asia จากเว็บ TripAdvisor ติดกันมาตั้งแต่ปี 2012 และอื่นๆอีกหลายรางวัล ภายในแบ่งออกเป็นโซนต่างๆที่น่าสนใจมากมายแต่ที่เป็นไฮไลท์สำคัญของสวนแห่งนี้คือ สวนกล้วยไม้แห่งชาติ(National Orchid Garden) เป็นทั้งสถานที่เรียนรู้ และพักผ่อนหย่อนใจของชาวสิงคโปร์และชาวต่างชาติ\n" +
//                "\n" +
//                "สวนพฤษศาสตร์(Singapore Botanic Garden)สร้างขึ้นตั้งแต่ปีค.ศ. 1859 ในปัจจุบันเป็นสถานีที่สำคัญในการศึกษาเกี่ยวกับพืชในเขตเมืองร้อน โดยเฉพาะกล้วยไม้ ซึ่งเป็นดอกไม้ประจำชาติสิงคโปร์ บนพื้นที่กว่า 500 ไร่ เป็นรูปทรงยาวจากเหนือไปใต้ มีระยะทางที่ยาวที่สุด 2.5 กิโลเมตร มีพืชพันธ์ต่างๆมากกว่า 10,000 สายพันธ์ และมีผู้มาเยี่ยมชมมากกว่า 4.5 ล้านคนต่อปี และเพิ่งได้รับการลงทะเบียนเป็นมรดกโลกเมื่อปี 2015 นี่เอง นอกจากนี้ยังเป็นสวนเพียงแห่งเดียวของโลกที่ปิดดึกมากคือปิดตอนเที่ยงคืน");

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
//        RV_Adapter_Service_Item adapterList = new RV_Adapter_Service_Item(data);
//        rv.setAdapter(adapterList);
        setListImage();
    }

    private void setListImage() {
        String serviceKey = itemService.getKey();
        mRootRef.child("item-photo").child(serviceKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemImage = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
                }

                data.add(itemImage);
                setAward();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setAward() {
        mRootRef.child("item-award").child(itemService.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<WrapFdbAward> dataAward = new ArrayList<WrapFdbAward>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbAward value = postSnapshot.getValue(FdbAward.class);
                    dataAward.add(new WrapFdbAward(key, value));

                    Log.d("Award", "+++++++++++++++++++++++++++++++++++++ ");

                }

                data.add(itemService); // location
                data.add(itemService); // contact
                data.add(dataAward);
                data.add(itemService.getData().getNameService());

                RV_Adapter_Service_Item adapterList = new RV_Adapter_Service_Item(itemService, data, getLayoutInflater());
                rv.setAdapter(adapterList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
