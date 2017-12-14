package com.istyleglobalnetwork.floatingmarkets.activity.shop;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Shop_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataImageShop;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductItem;

import java.util.ArrayList;

public class ShopItemActivity extends AppCompatActivity {

    String nameShop;
    int imageShop;

    TextView tvTitle;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LayoutInflater inflater = getLayoutInflater();
        FragmentManager fm = getFragmentManager();


        Bundle bundle = getIntent().getExtras();
        nameShop = bundle.getString("NameShop", "");
//        imageShop = bundle.getInt("ImageShop", 0);

        initInstances();
        tvTitle.setText(nameShop);

        DataImageShop dataImageShop = new DataImageShop(R.drawable.talad2, nameShop);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(dataImageShop);
        data.add("Award");
        data.add("Time");
        data.add("contact");
        data.add(nameShop);
        data.add(new DataProductItem(nameShop, R.drawable.ice1, "ไอติมโบรานใส่ถ้วย"));
        data.add(new DataProductItem(nameShop, R.drawable.ice2, "ไอติมโบรานใส่ขนมปัง"));
        data.add(new DataProductItem(nameShop, R.drawable.ice3, "ไอติมโบรานใส่มะพร้าว"));


        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Shop_Item adapterList = new RV_Adapter_Shop_Item(data, inflater);
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
