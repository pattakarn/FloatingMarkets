package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;

import org.parceler.Parcels;

public class EditMarketActivity extends AppCompatActivity {

    TextView tvTitle;
    EditText etName;
    Button btnSave;

    WrapFdbMarket itemMarket = null;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_market);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();
//        Intent i = getIntent();
//        WrapFdbMarket itemMarket = Parcels.unwrap(i.getParcelableExtra("objMarket"));
        Bundle bundle = getIntent().getExtras();

//        WrapFdbMarket itemMarket = Parcels.unwrap(getIntent().getExtras().getParcelableExtra("itemMarket"));
        if (bundle != null) {
            itemMarket = Parcels.unwrap(bundle.getParcelable("itemMarket"));
            etName.setText(itemMarket.getData().getNameMarket());
        }


        mRootRef = FirebaseDatabase.getInstance().getReference();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mMarketRef = mRootRef.child("market");
                String nameMarket = etName.getText().toString();
                FdbMarket dataMarket = new FdbMarket();
                dataMarket.setNameMarket(nameMarket);


                if (itemMarket != null) {
                    mMarketRef.child(itemMarket.getKey()).setValue(dataMarket);
                } else {
                    mMarketRef.push().setValue(dataMarket);
                }

                finish();

//                Toast.makeText(getApplication(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        etName = (EditText) findViewById(R.id.et_name);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
