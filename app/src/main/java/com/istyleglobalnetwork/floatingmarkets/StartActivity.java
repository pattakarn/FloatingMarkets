package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.istyleglobalnetwork.floatingmarkets.pageradapter.PagerAdapterInformation;

public class StartActivity extends AppCompatActivity {

    LoopingViewPager viewPager;

    FrameLayout rootLayout;

    private static final int RC_LOGIN = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int[] image = { R.drawable.bg_blue, R.drawable.bg_green, R.drawable.bg_organge, R.drawable.bg_pink};
        PagerAdapterInformation adapter = new PagerAdapterInformation(getSupportFragmentManager(), image);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        FloatingActionButton fabbtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        fabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(rootLayout, "Hello", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent();
//                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tran_right_from_left);
                intent.setClass(StartActivity.this, LoginActivity.class);
                startActivityForResult(intent, RC_LOGIN);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

}
