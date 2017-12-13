package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.GridView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Main_Menu;

public class MainActivity extends AppCompatActivity {

    GridView gridview;
//    Toolbar toolbar;

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();
//        setSupportActionBar(toolbar);

        RV_Adapter_Main_Menu adapter = new RV_Adapter_Main_Menu(this);
        gridview.setAdapter(adapter);
    }


    private void initInstances() {
        gridview = (GridView) findViewById(R.id.gridView);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);


//        iv_company = (ImageView) rootView.findViewById(R.id.iv_company);
//        iv_company.setOnClickListener(this);

    }
}
