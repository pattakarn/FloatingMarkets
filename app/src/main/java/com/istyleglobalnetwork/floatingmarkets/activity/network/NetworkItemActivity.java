package com.istyleglobalnetwork.floatingmarkets.activity.network;

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
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Network_Item;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class NetworkItemActivity extends AppCompatActivity {

    ArrayList<Object> data = new ArrayList<Object>();
    List<Object> itemImage = new ArrayList<Object>();

    TextView tvTitle;
    RecyclerView rv;

    WrapFdbNetwork itemNetwork = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_item);

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

            itemNetwork = Parcels.unwrap(bundle.getParcelable("itemNetwork"));
            if (itemNetwork != null) {
                tvTitle.setText(itemNetwork.getData().getNameNetwork());
            }

        }

//        int[] image = {R.drawable.network_1, R.drawable.network_2, R.drawable.network_3};

//        DataNetworkItem networkItem = new DataNetworkItem(image, nameItem);
//
//        ArrayList<Object> data = new ArrayList<Object>();
//        data.add(networkItem);
//        data.add("location");
//        data.add("contact");
//        data.add("rating");
//        data.add("ตั้งอยู่บริเวณบ้านท่าด่าน ตำบลหินตั้ง อำเภอเมือง จังหวัดนครนายก เป็นศูนย์นิทรรศการที่รวบรวมข้อมูล และกิจกรรมที่แสดงให้เห็นโครงการอันเนื่องอันเนื่องมาจากพระราชดำริ ของพระบาทสมเด็จพระเจ้าอยู่หัว อาทิ ด้านการเกษตร ด้านปศุสัตว์ ด้านสิ่งแวดล้อม และด้านพลังงาน ที่ทรงมุ่งการพัฒนาด้วยการแก้ไขปรับปรุงคุณภาพของ คน ดิน น้ำ ป่า อย่างเป็นระบบ");

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
//        RV_Adapter_Network_Item adapterList = new RV_Adapter_Network_Item(data);
//        rv.setAdapter(adapterList);
        setListImage();
    }

    private void setListImage() {
        String networkKey = itemNetwork.getKey();
        mRootRef.child("item-photo").child(networkKey).addListenerForSingleValueEvent(new ValueEventListener() {
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
        mRootRef.child("item-award").child(itemNetwork.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<WrapFdbAward> dataAward = new ArrayList<WrapFdbAward>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbAward value = postSnapshot.getValue(FdbAward.class);
                    dataAward.add(new WrapFdbAward(key, value));

                    Log.d("Award", "+++++++++++++++++++++++++++++++++++++ ");

                }

                data.add(itemNetwork); // location
                data.add(itemNetwork); // contact
                data.add(itemNetwork); // rating
//                data.add(dataAward);
                data.add(itemNetwork.getData().getNameNetwork());

                RV_Adapter_Network_Item adapterList = new RV_Adapter_Network_Item(itemNetwork, data, getLayoutInflater());
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
