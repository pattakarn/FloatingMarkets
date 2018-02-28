package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogLoginAndProfile;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
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
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser currentUser;

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

        mAuth = FirebaseAuth.getInstance();
        setPhoto();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                if (firebaseAuth.getCurrentUser() != null) {
//                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
//                        Log.d("Add Order", "Success");
//                        addOrder();
//                    } else {
//                        firebaseAuth.getCurrentUser().sendEmailVerification();
//                        Toast.makeText(BuyActivity.this, "กรุณายืนยันอีเมล", Toast.LENGTH_SHORT).show();
//                        Log.d("Add Order", "Failed");
//                        FirebaseAuth.getInstance().signOut();
//                    }
//
//                }
//            }
//        };

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentUser = mAuth.getCurrentUser();
                if (currentUser == null) {
                    Intent intent = new Intent(BuyActivity.this, LoginActivity.class);
                    startActivity(intent);
//                    getLogin();
                } else {
//                    if (currentUser.isEmailVerified()) {
////                        Toast.makeText(BuyActivity.this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();
//                        Log.d("Add Order", "Success");
//                        addOrder();
//                    } else {
//                        currentUser.sendEmailVerification();
//                        Toast.makeText(BuyActivity.this, "กรุณายืนยันอีเมล แล้วเข้าสู่ระบบอีกครั้ง", Toast.LENGTH_SHORT).show();
//                        Log.d("Add Order", "Failed");
//                        FirebaseAuth.getInstance().signOut();
//                        getLogin();
//                    }
                    addOrder();
                }

            }
        });

    }

    private void getLogin() {
        DialogLoginAndProfile make_popup = new DialogLoginAndProfile(BuyActivity.this);
        make_popup.Popup_Login();
//        make_popup.Popup_Login();
    }

    private void setPhoto() {
        mRootRef.child("item-photo").child(itemProduct.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String keyPhoto = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
//                            vh1.getIvProduct().setImageDrawable(value.getNameImage());
                    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                    StorageReference folderRef = storageRef.child("photos");
                    StorageReference imageRef = folderRef.child(value.getNameImage());
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(BuyActivity.this)
                                    .load(uri.toString())
                                    .placeholder(R.mipmap.ic_floating_market)
                                    .into(ivProduct);
                        }
                    });
                    break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addOrder(){
        DatabaseReference mOrderRef = mRootRef.child("order");
        DatabaseReference mUserOrderRef = mRootRef.child("user-order");

        FdbOrder dataOrder = new FdbOrder();
        String productID = itemProduct.getKey();
        int quantity = qvg.getQuantity();
        int price = itemProduct.getData().getPrice() * quantity;
        dataOrder.setProductID(productID);
        dataOrder.setUserID(mAuth.getCurrentUser().getUid());
        dataOrder.setQuantity(quantity);
        dataOrder.setStatus("standby");
        dataOrder.setPrice(price);
        dataOrder.setDate(DateTimeMillis.getDateMillisNow());
        dataOrder.setTime(DateTimeMillis.getTimeMillisNow());


        String keyOrder = mOrderRef.push().getKey();
        mOrderRef.child(keyOrder).setValue(dataOrder);
        mUserOrderRef.child(currentUser.getUid()).child(keyOrder).setValue(dataOrder);

        Intent intent = new Intent(BuyActivity.this, CartListActivity.class);
        startActivity(intent);
        finish();
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
