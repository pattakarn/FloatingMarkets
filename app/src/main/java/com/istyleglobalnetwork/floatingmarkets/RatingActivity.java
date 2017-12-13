package com.istyleglobalnetwork.floatingmarkets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingActivity extends AppCompatActivity {

    String nameShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        nameShop = bundle.getString("NameShop", "");

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        TextView tv_title_rating = (TextView) findViewById(R.id.tv_title_rating);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
//        final TextView tv_result = (TextView) findViewById(R.id.tv_result);
        Button btn_submit = (Button) findViewById(R.id.btn_submit);

        tv_title.setText(nameShop);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                tv_result.setText("Result : " + v);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
