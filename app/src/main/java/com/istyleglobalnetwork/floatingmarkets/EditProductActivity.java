package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;

import org.parceler.Parcels;

public class EditProductActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMarket;
    TextView tvZone;
    TextView tvShop;
    EditText etName;
    EditText etType;
    EditText etDetail;
    Button btnSave;

    WrapFdbZone itemZone = null;
    WrapFdbMarket itemMarket = null;
    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();
//        Intent i = getIntent();
//        WrapFdbMarket itemMarket = Parcels.unwrap(i.getParcelableExtra("objMarket"));
        Bundle bundle = getIntent().getExtras();

//        WrapFdbMarket itemMarket = Parcels.unwrap(getIntent().getExtras().getParcelableExtra("itemMarket"));
        if (bundle != null) {

            itemMarket = Parcels.unwrap(bundle.getParcelable("itemMarket"));
            if (itemMarket != null) {
                tvMarket.setText(itemMarket.getData().getNameMarket());
            }

            itemZone = Parcels.unwrap(bundle.getParcelable("itemZone"));
            if (itemZone != null) {
                tvZone.setText(itemZone.getData().getNameZone());
            }

            itemShop = Parcels.unwrap(bundle.getParcelable("itemShop"));
            if (itemShop != null) {
                tvShop.setText(itemShop.getData().getNameShop());
            }

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));
            if (itemProduct != null) {
                etName.setText(itemProduct.getData().getNameProduct());
            }


        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mShopRef = mRootRef.child("shop-product");
                DatabaseReference mProductRef = mRootRef.child("product");

//                String marketKey = itemMarket.getKey();
                String shopKey = itemShop.getKey();
                String nameProduct = etName.getText().toString();
                String txtType = etType.getText().toString();
                String txtDetail = etDetail.getText().toString();
                FdbProduct dataProduct = new FdbProduct();
                dataProduct.setNameProduct(nameProduct);
                dataProduct.setType(txtType);
                dataProduct.setDescription(txtDetail);

//                Toast.makeText(getApplication(), "Click " + dataZone.getNameZone() + " " + dataZone.getOwner(), Toast.LENGTH_SHORT).show();
                if (itemProduct != null) {
//                    mMarketZoneRef.child(itemMarket.getKey()).setValue(dataMarket);
                    String productKey = itemProduct.getKey();
                    mProductRef.child(productKey).setValue(dataProduct);
                    mShopRef.child(shopKey).child(productKey).setValue(dataProduct);
                } else {
                    String productKey = mProductRef.push().getKey();
                    mProductRef.child(productKey).setValue(dataProduct);
                    mShopRef.child(shopKey).child(productKey).setValue(dataProduct);
                }

                finish();

//                Toast.makeText(getApplication(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMarket = (TextView) findViewById(R.id.tv_market);
        tvZone = (TextView) findViewById(R.id.tv_zone);
        tvShop = (TextView) findViewById(R.id.tv_shop);
        etName = (EditText) findViewById(R.id.et_name);
        etType = (EditText) findViewById(R.id.et_type);
        etDetail = (EditText) findViewById(R.id.et_detail);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
