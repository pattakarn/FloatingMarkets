package com.istyleglobalnetwork.floatingmarkets.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.CartListActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Product_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductItem;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ProductItemActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Button btnBuy;
    int imageItem;

    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            itemShop = Parcels.unwrap(bundle.getParcelable("itemShop"));

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));

        }
        imageItem = bundle.getInt("ImageItem");

        tvTitle.setText(itemProduct.getData().getNameProduct());

        DataProductItem productItem = new DataProductItem(itemShop, itemProduct, imageItem);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(productItem);
        data.add(itemShop.getData().getNameShop());
        data.add(itemProduct.getData().getDescription());
        data.add(itemProduct.getData().getNameProduct());
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RV_Adapter_Product_Item adapterList = new RV_Adapter_Product_Item(data);
        rv.setAdapter(adapterList);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductItemActivity.this, CartListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
        btnBuy = (Button) findViewById(R.id.btn_buy);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_cart:
                Intent intent = new Intent(ProductItemActivity.this, CartListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
