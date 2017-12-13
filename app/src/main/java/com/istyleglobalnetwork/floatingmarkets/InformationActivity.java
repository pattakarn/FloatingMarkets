package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.istyleglobalnetwork.floatingmarkets.pageradapter.PagerAdapterInformation;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setTitle("ประวัติ");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int[] image = { R.drawable.intro1, R.drawable.intro2, R.drawable.intro3};
        PagerAdapterInformation adapter = new PagerAdapterInformation(getSupportFragmentManager(), image);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
