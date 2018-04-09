package com.istyleglobalnetwork.floatingmarkets.activity.manage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbService;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbTravel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
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

public class ManagePhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private View mProgressView;
    TextView tvTitle;

    LinearLayout ll;
    TextView tv1;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    Button btnSave;

    WrapFdbZone itemZone = null;
    WrapFdbMarket itemMarket = null;
    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;
    WrapFdbTravel itemTravel = null;
    WrapFdbHotel itemHotel = null;
    WrapFdbRoom itemRoom = null;
    WrapFdbNetwork itemNetwork = null;
    WrapFdbService itemService = null;
    WrapFdbImage[] itemImage = new WrapFdbImage[5];
    String[] listPathImage = new String[5];

    DatabaseReference mRootRef;
    private UploadTask mUploadTask;
    private StorageReference folderRef, imageRef;

    Boolean isImageUpdate = false;
    String itemKey = "";
    int indexImage = 0;

    private LoadingTask Task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_photo);

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

            itemMarket = Parcels.unwrap(bundle.getParcelable("itemMarket"));
            if (itemMarket != null) {
//                tvMarket.setText(itemMarket.getData().getNameMarket());
                itemKey = itemMarket.getKey();
            }

            itemZone = Parcels.unwrap(bundle.getParcelable("itemZone"));
            if (itemZone != null) {
//                tvZone.setText(itemZone.getData().getNameZone());
                itemKey = itemZone.getKey();
            }

            itemShop = Parcels.unwrap(bundle.getParcelable("itemShop"));
            if (itemShop != null) {
//                tvShop.setText(itemShop.getData().getNameShop());
                itemKey = itemShop.getKey();
            }

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));
            if (itemProduct != null) {
//                etName.setText(itemProduct.getData().getNameProduct());
//                setListImage();
                itemKey = itemProduct.getKey();
            }

            itemHotel = Parcels.unwrap(bundle.getParcelable("itemHotel"));
            if (itemHotel != null) {
                itemKey = itemHotel.getKey();
            }

            itemRoom = Parcels.unwrap(bundle.getParcelable("itemRoom"));
            if (itemRoom != null) {
                itemKey = itemRoom.getKey();
            }

            itemTravel = Parcels.unwrap(bundle.getParcelable("itemTravel"));
            if (itemTravel != null) {
//                tvShop.setText(itemShop.getData().getNameShop());
                itemKey = itemTravel.getKey();
            }

            itemNetwork = Parcels.unwrap(bundle.getParcelable("itemNetwork"));
            if (itemNetwork != null) {
                itemKey = itemNetwork.getKey();
            }

            itemService = Parcels.unwrap(bundle.getParcelable("itemService"));
            if (itemService != null) {
                itemKey = itemService.getKey();
            }




        }

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        setListImage();
    }

    private void setListImage() {
        mRootRef.child("item-photo").child(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<Object> dataUri = new ArrayList<Object>();
//                listPathImage = new ArrayList<String>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    int indexValue = Integer.parseInt(value.getIndex()) - 1;
                    itemImage[indexValue] = new WrapFdbImage(key, value);

                    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                    StorageReference folderRef = storageRef.child("photos");
                    StorageReference imageRef = folderRef.child(itemImage[indexValue].getData().getNameImage());

                    switch (indexValue) {
                        case 0:
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(ManagePhotoActivity.this)
                                            .load(uri.toString())
                                            .placeholder(R.mipmap.ic_floating_market)
                                            .into(iv1);
                                }
                            });
                            break;
                        case 1:
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(ManagePhotoActivity.this)
                                            .load(uri.toString())
                                            .placeholder(R.mipmap.ic_floating_market)
                                            .into(iv2);
                                }
                            });
                            break;
                        case 2:
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(ManagePhotoActivity.this)
                                            .load(uri.toString())
                                            .placeholder(R.mipmap.ic_floating_market)
                                            .into(iv3);
                                }
                            });
                            break;
                        case 3:
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(ManagePhotoActivity.this)
                                            .load(uri.toString())
                                            .placeholder(R.mipmap.ic_floating_market)
                                            .into(iv4);
                                }
                            });
                            break;
                        case 4:
                            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(ManagePhotoActivity.this)
                                            .load(uri.toString())
                                            .placeholder(R.mipmap.ic_floating_market)
                                            .into(iv5);
                                }
                            });
                            break;

                    }

                }

//                if (itemImage.size() != 0) {
//                    ivg.getTvImage().setText("Photo (" + itemImage.size() + ")");
//                    GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 2);
//                    glm.setOrientation(LinearLayoutManager.VERTICAL);
//                    ivg.getRv().setLayoutManager(glm);
//                    RV_Adapter_Grid_Image_Fdb adapterList = new RV_Adapter_Grid_Image_Fdb(itemImage);
//                    ivg.getRv().setAdapter(adapterList);
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        ll = (LinearLayout) findViewById(R.id.ll);
        tv1 = (TextView) findViewById(R.id.tv1);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);
        btnSave = (Button) findViewById(R.id.btn_save);

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            ll.setVisibility(show ? View.GONE : View.VISIBLE);
            ll.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ll.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            ll.setVisibility(show ? View.VISIBLE : View.GONE);
            ll.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ll.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            ll.setVisibility(show ? View.VISIBLE : View.GONE);
            ll.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == iv1) {
            indexImage = 1;
            callAlbum();
        } else if (v == iv2) {
            indexImage = 2;
            callAlbum();
        } else if (v == iv3) {
            indexImage = 3;
            callAlbum();
        } else if (v == iv4) {
            indexImage = 4;
            callAlbum();
        } else if (v == iv5) {
            indexImage = 5;
            callAlbum();
        } else if (v == btnSave) {
            Task = new ManagePhotoActivity.LoadingTask();
            Task.execute((Void) null);

            DatabaseReference mImageRef = mRootRef.child("photo");
            DatabaseReference mItemImageRef = mRootRef.child("item-photo");
            FdbImage dataImage = new FdbImage();

            String keyImage = null;

            if (isImageUpdate) {
                for (int i = 0; i < listPathImage.length; i++) {
                    if (listPathImage[i] != null) {
                        String nameImage = null;
                        if (itemImage[i] != null) {
                            keyImage = itemImage[i].getKey();
                            nameImage = keyImage + ".png";
                        } else {
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

                        if (!itemKey.equals("")) {
                            showProgress(true);
                            dataImage.setNameImage(nameImage);
                            dataImage.setItemID(itemKey);
                            dataImage.setIndex((i + 1) + "");
                            mImageRef.child(keyImage).setValue(dataImage);
                            mItemImageRef.child(itemKey).child(keyImage).setValue(dataImage);
                            try {
                                uploadFromStream(listPathImage[i], nameImage);
                            } catch (IndexOutOfBoundsException ex) {
//                        uploadFromStream("", nameImage);
                            }
                        }
                    }

                }
            }

            finish();
        }
    }

    public void callAlbum() {
        Intent intent = new Intent(getApplicationContext(), AlbumSelectActivity.class);
        intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, 1); // set limit for image selection
        startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            isImageUpdate = true;
            //The array list has the image paths of the selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);
//            ArrayList<Object> dataUri = new ArrayList<Object>();
//            listPathImage = new ArrayList<String>();

            Uri uri = Uri.fromFile(new File(images.get(0).path));
            listPathImage[indexImage - 1] = images.get(0).path;
//            dataUri.add(uri);
            switch (indexImage) {
                case 1:
                    iv1.setImageURI(uri);
                    break;
                case 2:
                    iv2.setImageURI(uri);
                    break;
                case 3:
                    iv3.setImageURI(uri);
                    break;
                case 4:
                    iv4.setImageURI(uri);
                    break;
                case 5:
                    iv5.setImageURI(uri);
                    break;

            }
//                ivSelect.setImageURI(uri);
            // start play with image uri

//            listPathImage.add(images.get(i).path);

//                uploadFromStream(images.get(i).path);

//            LinearLayoutManager llm = new LinearLayoutManager(this);
//            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
//            ivg.getTvImage().setText("Photo (" + dataUri.size() + ")");
//            GridLayoutManager glm = new GridLayoutManager(this, 3);
//            glm.setOrientation(LinearLayoutManager.VERTICAL);
//            ivg.getRv().setLayoutManager(glm);
//            RV_Adapter_Grid_Image adapterList = new RV_Adapter_Grid_Image(dataUri);
//            ivg.getRv().setAdapter(adapterList);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class LoadingTask extends AsyncTask<Void, Void, Boolean> {


        LoadingTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

//            for (String credential : DUMMY_CREDENTIALS) {
//                String[] pieces = credential.split(":");
//                if (pieces[0].equals(mEmail)) {
//                    // Account exists, return true if the password matches.
//                    return pieces[1].equals(mPassword);
//                }
//            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            Task = null;
            showProgress(false);

            if (success) {
                Toast.makeText(ManagePhotoActivity.this, "อัพโหลดรูปสำเร็จ", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(ManagePhotoActivity.this, "เกิดข้อผิดพลาด กรุณาลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            Task = null;
            showProgress(false);
        }
    }
}
