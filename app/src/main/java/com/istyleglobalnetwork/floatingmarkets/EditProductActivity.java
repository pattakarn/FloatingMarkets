package com.istyleglobalnetwork.floatingmarkets;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.Util.Helper;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ImageViewGroup;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;

public class EditProductActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMarket;
    TextView tvZone;
    TextView tvShop;
    EditText etName;
    EditText etType;
    EditText etDetail;
    EditText etPrice;
    ImageViewGroup ivg;
    Button btnSave;

    WrapFdbZone itemZone = null;
    WrapFdbMarket itemMarket = null;
    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;
    List<WrapFdbImage> itemImage = new ArrayList<WrapFdbImage>();
    List<String> listPathImage = new ArrayList<String>();

    DatabaseReference mRootRef;
    private UploadTask mUploadTask;
    private StorageReference folderRef, imageRef;

    Boolean isImageUpdate = false;
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
                setListImage();
            }

        }

        ivg.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlbumSelectActivity.class);
                intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, 5); // set limit for image selection
                startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
            }
        });

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
                int price = Integer.parseInt(etPrice.getText().toString());
                FdbProduct dataProduct = new FdbProduct();
                dataProduct.setNameProduct(nameProduct);
                dataProduct.setType(txtType);
                dataProduct.setDescription(txtDetail);
                dataProduct.setPrice(price);

                DatabaseReference mImageRef = mRootRef.child("photo");
                DatabaseReference mItemImageRef = mRootRef.child("item-photo");
                FdbImage dataImage = new FdbImage();

                String keyProduct = null;
                String keyImage = null;

//                Toast.makeText(getApplication(), "Click " + dataZone.getNameZone() + " " + dataZone.getOwner(), Toast.LENGTH_SHORT).show();
                if (itemProduct != null) {
//                    mMarketZoneRef.child(itemMarket.getKey()).setValue(dataMarket);
                    keyProduct = itemProduct.getKey();
                    mProductRef.child(keyProduct).setValue(dataProduct);
                    mShopRef.child(shopKey).child(keyProduct).setValue(dataProduct);
                } else {
                    keyProduct = mProductRef.push().getKey();
                    mProductRef.child(keyProduct).setValue(dataProduct);
                    mShopRef.child(shopKey).child(keyProduct).setValue(dataProduct);
                }

                if (isImageUpdate) {
                    for (int i = 0; i < 5; i++) {
                        String nameImage = null;
                        try {
                            keyImage = itemImage.get(i).getKey();
                            nameImage = keyImage + ".png";
                        } catch (IndexOutOfBoundsException ex) {
                            keyImage = mImageRef.push().getKey();
                            nameImage = keyImage + ".png";
                        }
//                    if (itemImage.get(i) != null){
//                        keyImage = itemImage.get(i).getKey();
//                        nameImage = keyImage + ".png";
//                    } else {
//                        keyImage = mImageRef.push().getKey();
//                        nameImage = keyImage + ".png";
//                    }

                        dataImage.setNameImage(nameImage);
                        mImageRef.child(keyImage).setValue(dataImage);
                        mItemImageRef.child(keyProduct).child(keyImage).setValue(dataImage);
                        try {
                            uploadFromStream(listPathImage.get(i), nameImage);
                        } catch (IndexOutOfBoundsException ex) {
//                        uploadFromStream("", nameImage);
                        }

                    }
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
        etPrice = (EditText) findViewById(R.id.et_price);
        ivg = (ImageViewGroup) findViewById(R.id.ivg);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    private void setListImage() {
        mRootRef.child("item-photo").child(itemProduct.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<Object> dataUri = new ArrayList<Object>();
                listPathImage = new ArrayList<String>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
                }

                ivg.getTvImage().setText("Photo (" + itemImage.size() + ")");
                GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 3);
                glm.setOrientation(LinearLayoutManager.VERTICAL);
                ivg.getRv().setLayoutManager(glm);
                RV_Adapter_Grid_Image_Fdb adapterList = new RV_Adapter_Grid_Image_Fdb(itemImage);
                ivg.getRv().setAdapter(adapterList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            isImageUpdate = true;
            //The array list has the image paths of the selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);
            ArrayList<Object> dataUri = new ArrayList<Object>();
            listPathImage = new ArrayList<String>();

            for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(i).path));
                dataUri.add(uri);
//                ivSelect.setImageURI(uri);
                // start play with image uri
                listPathImage.add(images.get(i).path);
//                uploadFromStream(images.get(i).path);
            }

//            LinearLayoutManager llm = new LinearLayoutManager(this);
//            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            ivg.getTvImage().setText("Photo (" + dataUri.size() + ")");
            GridLayoutManager glm = new GridLayoutManager(this, 3);
            glm.setOrientation(LinearLayoutManager.VERTICAL);
            ivg.getRv().setLayoutManager(glm);
            RV_Adapter_Grid_Image adapterList = new RV_Adapter_Grid_Image(dataUri);
            ivg.getRv().setAdapter(adapterList);
        }
    }

    private void uploadFromStream(String path, String name) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        folderRef = storageRef.child("photos");
        imageRef = folderRef.child(name);

//        Helper.showDialog(getApplicationContext());
        InputStream stream = null;
        try {
            stream = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mUploadTask = imageRef.putStream(stream);
        mUploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Helper.dismissDialog();
//                mTextView.setText(String.format("Failure: %s", exception.getMessage()));
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Helper.dismissDialog();
                setResult(RESULT_OK);
                finish();
//                getFragmentManager().popBackStack();
//                mTextView.setText(taskSnapshot.getDownloadUrl().toString());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
