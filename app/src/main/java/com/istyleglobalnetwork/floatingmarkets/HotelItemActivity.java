package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Hotel_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataImageHotel;

import java.util.ArrayList;

public class HotelItemActivity extends AppCompatActivity {

    String nameHotel;
    int imageHotel;

    TextView tvTitle;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Bundle bundle = getIntent().getExtras();
        nameHotel = bundle.getString("NameHotel", "");
        imageHotel = bundle.getInt("ImageHotel");

        initInstances();
        tvTitle.setText(nameHotel);

        DataImageHotel dataImageHotel = new DataImageHotel(imageHotel, nameHotel, "4");



        ArrayList<Object> data = new ArrayList<Object>();
        data.add(dataImageHotel);
        data.add("contact");
        data.add("location");
        data.add(nameHotel);
        data.add("Twin - Non - Smoking");
        data.add("Standard Double Room");
        data.add("Standard Twin Room");
        data.add("Triple - Non - Smoking");


        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Hotel_Item adapterList = new RV_Adapter_Hotel_Item(data);
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
