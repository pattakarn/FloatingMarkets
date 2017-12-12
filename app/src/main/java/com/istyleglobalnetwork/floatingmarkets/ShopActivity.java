package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    String nameShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Bundle bundle = getIntent().getExtras();
        nameShop = bundle.getString("NameShop", "");

        ImageView ivComment = (ImageView) findViewById(R.id.iv_comment);
        ImageView ivRating = (ImageView) findViewById(R.id.iv_rating);
        ImageView ivProduct = (ImageView) findViewById(R.id.iv_share);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);

        tv_title.setText(nameShop);
//        setTitle(nameShop);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ivComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, CommentActivity.class);
                startActivity(intent);
            }
        });
        ivRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, RatingActivity.class);
                intent.putExtra("NameShop", nameShop);
                startActivity(intent);
            }
        });
        ivProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, ProductListActivity.class);
                intent.putExtra("NameShop", nameShop);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
