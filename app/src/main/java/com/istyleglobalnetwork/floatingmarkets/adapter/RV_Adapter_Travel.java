package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbTravel;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.travel.TravelItemActivity;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderTravel;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Travel extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Travel(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_travel, parent, false);
        viewHolder = new ViewHolderTravel(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderTravel vh = (ViewHolderTravel) holder;
        configureViewHolderTravel(vh, position);
    }

    private void configureViewHolderTravel(ViewHolderTravel vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        final WrapFdbTravel data = (WrapFdbTravel) items.get(position);
//        vh1.getIv().setImageResource(data.getImageItem());
        setPhoto(data.getKey(), vh1.getIv());
        vh1.getTv().setText(data.getData().getNameTravel());
        vh1.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), TravelItemActivity.class);
//                intent.putExtra("NameShop", data.getNameItem());
//                intent.putExtra("ImageTravel", data.getImageItem());
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemTravel", Parcels.wrap(data));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
//        }
            }
        });

    }

    private void setPhoto(String itemKey, final ImageView iv) {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("item-photo").child(itemKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);

                    if (value.getIndex().equals("1")) {

                        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                        StorageReference folderRef = storageRef.child("photos");
                        StorageReference imageRef = folderRef.child(value.getNameImage());

                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(inflater.getContext())
                                        .load(uri.toString())
//                                        .placeholder(R.mipmap.ic_floating_market)
                                        .into(iv);
                            }
                        });
                    }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
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
