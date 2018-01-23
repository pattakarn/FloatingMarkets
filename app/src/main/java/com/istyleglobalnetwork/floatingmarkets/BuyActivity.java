package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbOrder;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.QuantityViewGroup;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends AppCompatActivity {

    TextView tvTitle;
    ImageView ivProduct;
    TextView tvName;
    TextView tvDetail;
    QuantityViewGroup qvg;
    Button btnAdd;

    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;
    ArrayList<Object> data = new ArrayList<Object>();
    List<Object> itemImage = new ArrayList<Object>();

    DatabaseReference mRootRef;
    private UploadTask mUploadTask;
    private StorageReference folderRef, imageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        folderRef = storageRef.child("photos");

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            itemShop = Parcels.unwrap(bundle.getParcelable("itemShop"));
            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));
            if (itemProduct != null) {
                tvName.setText(itemProduct.getData().getNameProduct());
                tvDetail.setText(itemProduct.getData().getPrice() + " บาท");
            }

        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mOrderRef = mRootRef.child("order");

                FdbOrder dataOrder = new FdbOrder();
                String productID = itemProduct.getKey();
                int quantity = qvg.getQuantity();
                int price = itemProduct.getData().getPrice() * quantity;
                dataOrder.setProductID(productID);
                dataOrder.setQuantity(quantity);
                dataOrder.setStatus("standby");
                dataOrder.setPrice(price);


                String keyOrder = mOrderRef.push().getKey();
                mOrderRef.child(keyOrder).setValue(dataOrder);

                Intent intent = new Intent(BuyActivity.this, CartListActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivProduct = (ImageView) findViewById(R.id.iv_product);
        tvName = (TextView) findViewById(R.id.tv_productname);
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        qvg = (QuantityViewGroup) findViewById(R.id.qvg);
        btnAdd = (Button) findViewById(R.id.btn_add_cart);

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
