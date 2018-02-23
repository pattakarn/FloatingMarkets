package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Comment_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataCommentItem;

import org.parceler.Parcels;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tvTitle;
    Button btn;

    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setTitle("Comment");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            itemShop = Parcels.unwrap(bundle.getParcelable("itemShop"));
            if (itemShop != null){
                tvTitle.setText(itemShop.getData().getNameShop());
            }

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));
            if (itemProduct != null){
                tvTitle.setText(itemProduct.getData().getNameProduct());
            }

        }



        String[] title_comment = getResources().getStringArray(R.array.title_comment);
        String[] detail_comment = getResources().getStringArray(R.array.detail_comment);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add("rating");

        for (int i=0; i<title_comment.length; i++){
            data.add(new DataCommentItem(title_comment[i], detail_comment[i], "5"));
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Comment_Item adapterList = new RV_Adapter_Comment_Item(data);
        rv.setAdapter(adapterList);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, RatingActivity.class);
                Bundle bundle = new Bundle();
                if (itemShop != null) {
                    bundle.putParcelable("itemShop", Parcels.wrap(itemShop));
                } else if (itemProduct != null) {
                    bundle.putParcelable("itemProduct", Parcels.wrap(itemProduct));
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
        btn = (Button) findViewById(R.id.btn);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
