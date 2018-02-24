package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.istyleglobalnetwork.floatingmarkets.DateTimeMillis;
import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogLoginAndProfile;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbUser;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderAccountPhoto;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderAccountProfile;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Account extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Account(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_account_photo, parent, false);
                viewHolder = new ViewHolderAccountPhoto(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_account_profile, parent, false);
                viewHolder = new ViewHolderAccountProfile(v2);
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderAccountPhoto vh1 = (ViewHolderAccountPhoto) holder;
                configureViewHolderAccountPhoto(vh1, position);
                break;
            case 1:
                ViewHolderAccountProfile vh2 = (ViewHolderAccountProfile) holder;
                configureViewHolderAccountProfile(vh2, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureViewHolderAccountProfile(ViewHolderAccountProfile vh1, int position) {

        final WrapFdbUser dataUser = (WrapFdbUser) items.get(position);

        vh1.getColName().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLoginAndProfile popup = new DialogLoginAndProfile(inflater.getContext());
                popup.Popup_ChangeContactName(dataUser);
            }
        });
        vh1.getColSex().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLoginAndProfile popup = new DialogLoginAndProfile(inflater.getContext());
                popup.Popup_ChangeGender(dataUser);
            }
        });
        vh1.getColBirth().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLoginAndProfile popup = new DialogLoginAndProfile(inflater.getContext());
                popup.Popup_ChangeDate(dataUser);
            }
        });
        vh1.getColPhone().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLoginAndProfile popup = new DialogLoginAndProfile(inflater.getContext());
                popup.Popup_ChangePhone(dataUser);
            }
        });

//        vh1.getColName().getLl().setOnClickListener(this);
//        vh1.getColSex().getLl().setOnClickListener(this);
//        vh1.getColBirth().getLl().setOnClickListener(this);
//        vh1.getColPhone().getLl().setOnClickListener(this);


        vh1.getColName().getTvTitle().setText("Contact Name");
        vh1.getColEmail().getTvTitle().setText("Email");
        vh1.getColSex().getTvTitle().setText("Gender");
        vh1.getColBirth().getTvTitle().setText("Birth Year");
        vh1.getColPhone().getTvTitle().setText("Phone");

        if (dataUser.getData() != null) {
            vh1.getColName().getTvValue().setText(dataUser.getData().getNameContact());
            vh1.getColEmail().getTvValue().setText(dataUser.getData().getEmail());
            vh1.getColSex().getTvValue().setText(dataUser.getData().getGender());
            vh1.getColBirth().getTvValue().setText(DateTimeMillis.MillisToDate(dataUser.getData().getBirth()));
            vh1.getColPhone().getTvValue().setText(dataUser.getData().getPhone());
        }


    }

    private void configureViewHolderAccountPhoto(ViewHolderAccountPhoto vh1, int position) {

        vh1.getTvTitle().setText("Photo");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Uri urlPhoto = null;
        if (currentUser != null) {
            for (UserInfo profile : currentUser.getProviderData()) {
                urlPhoto = profile.getPhotoUrl();
            }
        }
        Glide.with(inflater.getContext())
                .load(urlPhoto)
                .placeholder(R.mipmap.ic_floating_market)
                .into(vh1.getIvPhoto());
        vh1.getIvPhoto().setImageURI(urlPhoto);

//        vh1.getColEmail().getTvValue().setText();getTv
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
