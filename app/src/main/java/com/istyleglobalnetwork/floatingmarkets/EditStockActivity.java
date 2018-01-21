package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbStock;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbStock;

import org.parceler.Parcels;

public class EditStockActivity extends AppCompatActivity {

    TextView tvTitle;
    private RadioGroup radioGroup;
    private RadioButton radioAdd, radioReduce;
    EditText etAmount;
    Button btnSave;

    WrapFdbProduct itemProduct = null;
    WrapFdbStock itemStock = null;

    DatabaseReference mRootRef;
    private UploadTask mUploadTask;
    private StorageReference folderRef, imageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stock);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();

//        WrapFdbMarket itemMarket = Parcels.unwrap(getIntent().getExtras().getParcelableExtra("itemMarket"));
        if (bundle != null) {

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));
            if (itemProduct != null) {
                tvTitle.setText(itemProduct.getData().getNameProduct());
//                etName.setText(itemProduct.getData().getNameProduct());
//                setListImage();
                getStock();
            }

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mStockRef = mRootRef.child("stock");

                String nameProduct = itemProduct.getData().getNameProduct();

                int oldAmount = 0;
                int newAmount = 0;
                int sumAmount = 0;
                if (itemStock != null){
                    oldAmount = Integer.parseInt(itemStock.getData().getAmount());
                }

                newAmount = Integer.parseInt(etAmount.getText().toString());
                
                if (radioAdd.isChecked()){
                    sumAmount = oldAmount + newAmount;
                } else if (radioReduce.isChecked()){
                    sumAmount = oldAmount - newAmount;
                }



                FdbStock dataStock = new FdbStock();
                dataStock.setNameProduct(nameProduct);
                dataStock.setAmount(sumAmount + "");

                mStockRef.child(itemProduct.getKey()).setValue(dataStock);


                finish();

            }
        });
    }

    private void getStock(){
        mRootRef.child("stock").child(itemProduct.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    String key = dataSnapshot.getKey();
                    FdbStock value = dataSnapshot.getValue(FdbStock.class);
                    itemStock = new WrapFdbStock(key, value);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioAdd = (RadioButton) findViewById(R.id.radio_add);
        radioReduce = (RadioButton) findViewById(R.id.radio_reduce);
        etAmount = (EditText) findViewById(R.id.et_amount) ;
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}