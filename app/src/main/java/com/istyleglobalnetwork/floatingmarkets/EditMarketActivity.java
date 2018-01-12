package com.istyleglobalnetwork.floatingmarkets;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.Util.Helper;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;

public class EditMarketActivity extends AppCompatActivity {

    TextView tvTitle;
    EditText etName;
    ImageView ivSelect;
    Button btnSave;

    String pathImageProfile = null;

    WrapFdbMarket itemMarket = null;

    DatabaseReference mRootRef;
    private UploadTask mUploadTask;
    private StorageReference folderRef, imageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_market);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        folderRef = storageRef.child("photos");
        Bundle bundle = getIntent().getExtras();

//        WrapFdbMarket itemMarket = Parcels.unwrap(getIntent().getExtras().getParcelableExtra("itemMarket"));
        if (bundle != null) {
            itemMarket = Parcels.unwrap(bundle.getParcelable("itemMarket"));
            etName.setText(itemMarket.getData().getNameMarket());
            imageRef = folderRef.child("hello.png");
            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(getApplicationContext())
                            .load(uri.toString())
                            .placeholder(R.mipmap.ic_floating_market)
                            .into(ivSelect);
                }
            });

        }


        mRootRef = FirebaseDatabase.getInstance().getReference();
        ivSelect.setOnClickListener(new View.OnClickListener() {
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
                DatabaseReference mMarketRef = mRootRef.child("market");
                String nameMarket = etName.getText().toString();
                FdbMarket dataMarket = new FdbMarket();
                dataMarket.setNameMarket(nameMarket);

                if (itemMarket != null) {
                    String key = itemMarket.getKey();

                    if (pathImageProfile != null) {
                        uploadFromStream(pathImageProfile);
                        dataMarket.setImage(key + ".png");
                    }
                    mMarketRef.child(key).setValue(dataMarket);
                } else {
                    String key = mMarketRef.push().getKey();

                    if (pathImageProfile != null) {
                        uploadFromStream(pathImageProfile);
                        dataMarket.setImage(key + ".png");
                    }
                    mMarketRef.child(key).setValue(dataMarket);
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
        ivSelect = (ImageView) findViewById(R.id.iv_select);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of the selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);

            for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(i).path));
                ivSelect.setImageURI(uri);
                // start play with image uri
                pathImageProfile = images.get(i).path;
//                uploadFromStream(images.get(i).path);
            }
        }
    }

    private void uploadFromStream(String path) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        folderRef = storageRef.child("photos");
        imageRef = folderRef.child("hello.png");

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
