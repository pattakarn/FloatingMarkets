package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_List_Comment;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setTitle("Comment");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RV_Adapter_List_Comment adapter = new RV_Adapter_List_Comment(this);

        ListView listView = (ListView)findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
