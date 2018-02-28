package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbStock;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbStockList;
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
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stock);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
                DatabaseReference mStockListRef = mRootRef.child("stock-list");

                String nameProduct = itemProduct.getData().getNameProduct();

                int oldQuantity = 0;
                int newQuantity = 0;
                int sumQuantity = 0;
                if (itemStock.getData() != null){
                    oldQuantity = itemStock.getData().getQuantity();
                }
                newQuantity = Integer.parseInt(etAmount.getText().toString());

                FdbStockList dataStockList = new FdbStockList();
                
                if (radioAdd.isChecked()){
                    sumQuantity = oldQuantity + newQuantity;
                    dataStockList.setMark("add");
                } else if (radioReduce.isChecked()){
                    sumQuantity = oldQuantity - newQuantity;
                    dataStockList.setMark("reduce");
                }

                FdbStock dataStock = new FdbStock();
                dataStock.setNameProduct(nameProduct);
                dataStock.setQuantity(sumQuantity);
                dataStock.setUpdateDate(DateTimeMillis.getDateMillisNow());
                dataStock.setUpdateTime(DateTimeMillis.getTimeMillisNow());

                mAuth = FirebaseAuth.getInstance();
                String keyStockList = mStockListRef.child(itemProduct.getKey()).push().getKey();
                dataStockList.setQuantity(newQuantity);
                dataStockList.setUserID(mAuth.getCurrentUser().getUid());
                dataStockList.setDate(DateTimeMillis.getDateMillisNow());
                dataStockList.setTime(DateTimeMillis.getTimeMillisNow());

                mStockRef.child(itemProduct.getKey()).setValue(dataStock);
                mStockListRef.child(itemProduct.getKey()).child(keyStockList).setValue(dataStockList);


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
