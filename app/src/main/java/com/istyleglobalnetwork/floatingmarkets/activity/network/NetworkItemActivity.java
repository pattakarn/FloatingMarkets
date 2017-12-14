package com.istyleglobalnetwork.floatingmarkets.activity.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Network_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataNetworkItem;

import java.util.ArrayList;

public class NetworkItemActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    String nameItem;
    int imageItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        nameItem = bundle.getString("NameTravel", "");
        imageItem = bundle.getInt("ImageTravel");

        initInstances();
        tvTitle.setText(nameItem);

        int[] image = {R.drawable.network_1, R.drawable.network_2, R.drawable.network_3};

        DataNetworkItem networkItem = new DataNetworkItem(image, nameItem);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(networkItem);
        data.add("location");
        data.add("contact");
        data.add("rating");
        data.add("ตั้งอยู่บริเวณบ้านท่าด่าน ตำบลหินตั้ง อำเภอเมือง จังหวัดนครนายก เป็นศูนย์นิทรรศการที่รวบรวมข้อมูล และกิจกรรมที่แสดงให้เห็นโครงการอันเนื่องอันเนื่องมาจากพระราชดำริ ของพระบาทสมเด็จพระเจ้าอยู่หัว อาทิ ด้านการเกษตร ด้านปศุสัตว์ ด้านสิ่งแวดล้อม และด้านพลังงาน ที่ทรงมุ่งการพัฒนาด้วยการแก้ไขปรับปรุงคุณภาพของ คน ดิน น้ำ ป่า อย่างเป็นระบบ");

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Network_Item adapterList = new RV_Adapter_Network_Item(data);
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
