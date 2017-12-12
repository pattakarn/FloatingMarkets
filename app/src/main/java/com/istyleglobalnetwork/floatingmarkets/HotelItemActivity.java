package com.istyleglobalnetwork.floatingmarkets;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Hotel_Item;

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

        LayoutInflater inflater = getLayoutInflater();
        FragmentManager fm = getFragmentManager();


        Bundle bundle = getIntent().getExtras();
        nameHotel = bundle.getString("NameHotel", "");
        imageHotel = bundle.getInt("ImageHotel");

        initInstances();
        tvTitle.setText(nameHotel);



        ArrayList<Object> data = new ArrayList<Object>();
        data.add(imageHotel);
        data.add("1");
        data.add("Twin - Non - Smoking");
        data.add("Standard Double Room");
        data.add("Standard Twin Room");
        data.add("Triple - Non - Smoking");


        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Hotel_Item adapterList = new RV_Adapter_Hotel_Item(data, inflater);
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
