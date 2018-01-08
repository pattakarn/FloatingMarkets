package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbZone;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;

import org.parceler.Parcels;

public class EditZoneActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMarket;
    EditText etName;
    EditText etOwner;
    EditText etSize;
    Button btnSave;

    WrapFdbZone itemZone = null;
    WrapFdbMarket itemMarket = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_zone);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();
//        Intent i = getIntent();
//        WrapFdbMarket itemMarket = Parcels.unwrap(i.getParcelableExtra("objMarket"));
        Bundle bundle = getIntent().getExtras();

//        WrapFdbMarket itemMarket = Parcels.unwrap(getIntent().getExtras().getParcelableExtra("itemMarket"));
        if (bundle != null) {

            itemZone = Parcels.unwrap(bundle.getParcelable("itemZone"));
            if (itemZone != null) {
                etName.setText(itemZone.getData().getNameZone());
                etOwner.setText(itemZone.getData().getOwner());
            }


            itemMarket = Parcels.unwrap(bundle.getParcelable("itemMarket"));
            if (itemMarket != null) {
                tvMarket.setText(itemMarket.getData().getNameMarket());
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mMarketZoneRef = mRootRef.child("market-zone");
                DatabaseReference mZoneRef = mRootRef.child("zone");


                String marketKey = itemMarket.getKey();
                String nameZone = etName.getText().toString();
                String nameOwner = etOwner.getText().toString();
                String size = etSize.getText().toString();
                FdbZone dataZone = new FdbZone();
                dataZone.setNameZone(nameZone);
                dataZone.setOwner(nameOwner);
                dataZone.setSize(size);

//                Toast.makeText(getApplication(), "Click " + dataZone.getNameZone() + " " + dataZone.getOwner(), Toast.LENGTH_SHORT).show();
                if (itemZone != null) {
//                    mMarketZoneRef.child(itemMarket.getKey()).setValue(dataMarket);
                    String zoneKey = itemZone.getKey();
                    mZoneRef.child(zoneKey).setValue(dataZone);
                    mMarketZoneRef.child(marketKey).child(zoneKey).setValue(dataZone);
                } else {
                    String zoneKey = mZoneRef.push().getKey();
                    mZoneRef.child(zoneKey).setValue(dataZone);
                    mMarketZoneRef.child(marketKey).child(zoneKey).setValue(dataZone);
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
