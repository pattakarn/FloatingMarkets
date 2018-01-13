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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.Util.Helper;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image;
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

public class EditShopActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvMarket;
    TextView tvZone;
    EditText etName;
    EditText etOwner;
    EditText etPhone;
    EditText etLine;
    EditText etFacebook;
    EditText etEmail;
    ImageViewGroup ivg;
    Button btnSave;

    WrapFdbZone itemZone = null;
    WrapFdbMarket itemMarket = null;
    WrapFdbShop itemShop = null;
    List<WrapFdbImage> itemImage = new ArrayList<WrapFdbImage>();
    List<String> listPathImage = new ArrayList<String>();

    DatabaseReference mRootRef;
    private UploadTask mUploadTask;
    private StorageReference folderRef, imageRef;

    Boolean isImageUpdate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        folderRef = storageRef.child("photos");


        Bundle bundle = getIntent().getExtras();
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
                etOwner.setText(itemShop.getData().getOwner());
                etPhone.setText(itemShop.getData().getPhone());
                etLine.setText(itemShop.getData().getLine());
                etFacebook.setText(itemShop.getData().getFacebook());
                etEmail.setText(itemShop.getData().getEmail());
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
                DatabaseReference mZoneRef = mRootRef.child("zone-shop");
                DatabaseReference mShopRef = mRootRef.child("shop");

//                String marketKey = itemMarket.getKey();
                String zoneKey = itemZone.getKey();
                String nameShop = etName.getText().toString();
                String nameOwner = etOwner.getText().toString();
                String phone = etPhone.getText().toString();
                String line = etLine.getText().toString();
                String facebook = etFacebook.getText().toString();
                String email = etEmail.getText().toString();
                FdbShop dataZone = new FdbShop();
                dataZone.setNameShop(nameShop);
                dataZone.setOwner(nameOwner);
                dataZone.setPhone(phone);
                dataZone.setLine(line);
                dataZone.setFacebook(facebook);
                dataZone.setEmail(email);

                DatabaseReference mImageRef = mRootRef.child("photo");
                DatabaseReference mItemImageRef = mRootRef.child("item-photo");
                FdbImage dataImage = new FdbImage();

                String keyShop = null;
                String keyImage = null;

//                Toast.makeText(getApplication(), "Click " + dataZone.getNameZone() + " " + dataZone.getOwner(), Toast.LENGTH_SHORT).show();
                if (itemShop != null) {
//                    mMarketZoneRef.child(itemMarket.getKey()).setValue(dataMarket);
                    keyShop = itemShop.getKey();
                    mShopRef.child(keyShop).setValue(dataZone);
                    mZoneRef.child(zoneKey).child(keyShop).setValue(dataZone);
                } else {
                    keyShop = mZoneRef.push().getKey();
                    mShopRef.child(keyShop).setValue(dataZone);
                    mZoneRef.child(zoneKey).child(keyShop).setValue(dataZone);
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
                        mItemImageRef.child(keyShop).child(keyImage).setValue(dataImage);
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
        etName = (EditText) findViewById(R.id.et_name);
        etOwner = (EditText) findViewById(R.id.et_owner);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etLine = (EditText) findViewById(R.id.et_line);
        etFacebook = (EditText) findViewById(R.id.et_facebook);
        etEmail = (EditText) findViewById(R.id.et_email);
        ivg = (ImageViewGroup) findViewById(R.id.ivg);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    private void setListImage() {
        mRootRef.child("item-photo").child(itemShop.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<Object> dataUri = new ArrayList<Object>();
                listPathImage = new ArrayList<String>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
//                    listMarket.add(value.getNameMarket());
//                    dataMarket.add(new WrapFdbMarket(key, value));
                    imageRef = folderRef.child(value.getNameImage());
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            listPathImage.add(uri.toString());
                            dataUri.add(uri);
//                            Glide.with(getApplicationContext())
//                                    .load(uri.toString())
//                                    .placeholder(R.mipmap.ic_floating_market)
//                                    .into(ivSelect);
                        }
                    });
                }

                ivg.getTvImage().setText("Photo (" + itemImage.size() + ")");
                GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 3);
                glm.setOrientation(LinearLayoutManager.VERTICAL);
                ivg.getRv().setLayoutManager(glm);
                RV_Adapter_Grid_Image adapterList = new RV_Adapter_Grid_Image(dataUri);
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