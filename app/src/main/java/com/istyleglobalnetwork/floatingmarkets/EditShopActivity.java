package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;

import org.parceler.Parcels;

public class EditShopActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMarket;
    TextView tvZone;
    EditText etName;
    EditText etOwner;
    EditText etSize;
    Button btnSave;

    WrapFdbZone itemZone = null;
    WrapFdbMarket itemMarket = null;
    WrapFdbShop itemShop = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);

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
                etName.setText(itemShop.getData().getNameShop());
            }


        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mZoneRef = mRootRef.child("zone-shop");
                DatabaseReference mShopRef = mRootRef.child("shop");

//                String marketKey = itemMarket.getKey();
                String zoneKey = itemZone.getKey();
                String nameShop = etName.getText().toString();
                String nameOwner = etOwner.getText().toString();
                FdbShop dataZone = new FdbShop();
                dataZone.setNameShop(nameShop);
                dataZone.setOwner(nameOwner);

//                Toast.makeText(getApplication(), "Click " + dataZone.getNameZone() + " " + dataZone.getOwner(), Toast.LENGTH_SHORT).show();
                if (itemShop != null) {
//                    mMarketZoneRef.child(itemMarket.getKey()).setValue(dataMarket);
                    String shopKey = itemShop.getKey();
                    mShopRef.child(shopKey).setValue(dataZone);
                    mZoneRef.child(zoneKey).child(shopKey).setValue(dataZone);
                } else {
                    String shopKey = mZoneRef.push().getKey();
                    mShopRef.child(shopKey).setValue(dataZone);
                    mZoneRef.child(zoneKey).child(shopKey).setValue(dataZone);
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
        etName = (EditText) findViewById(R.id.et_name);
        etOwner = (EditText) findViewById(R.id.et_owner);
        etSize = (EditText) findViewById(R.id.et_size);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
