package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderGridImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Grid_Image_Fdb extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<WrapFdbImage> itemImage = new ArrayList<WrapFdbImage>();
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Grid_Image_Fdb(List<WrapFdbImage> items) {
        this.itemImage = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_grid_image, parent, false);
        viewHolder = new ViewHolderGridImage(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderGridImage vh = (ViewHolderGridImage) holder;
        configureViewHolderGridImage(vh, position);
    }

    private void configureViewHolderGridImage(final ViewHolderGridImage vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
//        final Uri uri = (Uri) items.get(position);
//        Log.d("uri2", "============== " + uri.toString());
////        vh1.getIv().setImageURI(uri);
//        Glide.with(inflater.getContext())
//                .load(uri.toString())
//                .placeholder(R.mipmap.ic_floating_market)
//                .into(vh1.getIv());
//        vh1.getCv().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(inflater.getContext(), NetworkItemActivity.class);
//                intent.putExtra("NameTravel", data.getNameItem());
//                intent.putExtra("ImageTravel", data.getImageItem());
//                inflater.getContext().startActivity(intent);
////        }
//            }
//        });

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference folderRef = storageRef.child("photos");
        StorageReference imageRef = folderRef.child(itemImage.get(position).getData().getNameImage());
        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                            Glide.with(inflater.getContext())
                                    .load(uri.toString())
                                    .placeholder(R.mipmap.ic_floating_market)
                                    .into(vh1.getIv());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemImage.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (items.get(position) instanceof Text) {
//            return TITLE;
//        } else if (items.get(position) instanceof String) {
//            return IMAGE;
//        }
        return position;
    }


}
