package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.istyleglobalnetwork.floatingmarkets.CommentActivity;
import com.istyleglobalnetwork.floatingmarkets.DateTimeMillis;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbComment;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbFeeling;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbStock;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbFeeling;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.LoginActivity;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.product.ProductItemActivity;
import com.istyleglobalnetwork.floatingmarkets.data.DataProductItem;
import com.istyleglobalnetwork.floatingmarkets.data.DataRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderAward;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderContact;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderImageShop;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderProduct;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderTime;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Shop_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    WrapFdbShop itemShop;
    LayoutInflater inflater;

    WrapFdbFeeling dataFeeling;
    DatabaseReference mRootRef;
    FirebaseAuth mAuth;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Shop_Item(WrapFdbShop itemShop, List<Object> items, LayoutInflater inflater) {
        this.itemShop = itemShop;
        this.items = items;
        this.inflater = inflater;

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_image_shop_rv, parent, false);
                viewHolder = new ViewHolderImageShop(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_award, parent, false);
                viewHolder = new ViewHolderAward(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_time, parent, false);
                viewHolder = new ViewHolderTime(v3);
                break;
            case 3:
                View v4 = inflater.inflate(R.layout.card_contact, parent, false);
                viewHolder = new ViewHolderContact(v4);
                break;
            case 4:
                View v5 = inflater.inflate(R.layout.card_rating, parent, false);
                viewHolder = new ViewHolderRating(v5);
                break;
            default:
//                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                View v6 = inflater.inflate(R.layout.card_product, parent, false);
                viewHolder = new ViewHolderProduct(v6);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderImageShop vh1 = (ViewHolderImageShop) holder;
                configureViewHolderImageShop(vh1, position);
                break;
            case 1:
                ViewHolderAward vh2 = (ViewHolderAward) holder;
                configureViewHolderAward(vh2, position);
                break;
            case 2:
                ViewHolderTime vh3 = (ViewHolderTime) holder;
                configureViewHolderTime(vh3, position);
                break;
            case 3:
                ViewHolderContact vh4 = (ViewHolderContact) holder;
                configureViewHolderContact(vh4, position);
                break;
            case 4:
                ViewHolderRating vh5 = (ViewHolderRating) holder;
                configureViewHolderRating(vh5, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                ViewHolderProduct vh6 = (ViewHolderProduct) holder;
                configureViewHolderProduct(vh6, position);
                break;
        }
    }

    private void configureViewHolderContact(ViewHolderContact vh1, int position) {
        WrapFdbShop data = (WrapFdbShop) items.get(position);
//        User user = (User) items.get(position);
//        if (user != null) {
        vh1.getTvLink().setText(data.getData().getFacebook());
        vh1.getTvPhone().setText(data.getData().getPhone());
        vh1.getTvLine().setText(data.getData().getLine());
        vh1.getTvFb().setText(data.getData().getFacebook());
        vh1.getTvEmail().setText(data.getData().getEmail());
//        vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderRating(final ViewHolderRating vh1, final int position) {
        vh1.getBtnRating().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), CommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemShop", Parcels.wrap(itemShop));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });

        mRootRef.child("item-comment").child(itemShop.getKey()).orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataRating dataRating = new DataRating();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbComment value = postSnapshot.getValue(FdbComment.class);
//
                    Log.d("item-comment", "==========================================================" + key);
                    Log.d("item-comment", "==========================================================" + value.getComment());

                    dataRating.addStarAll(value.getRating());
                    dataRating.addUserAll();

                    int ratingPoint = (int) value.getRating();
                    if (ratingPoint == 1) {
                        dataRating.addStar1();
                    } else if (ratingPoint == 2) {
                        dataRating.addStar2();
                    } else if (ratingPoint == 3) {
                        dataRating.addStar3();
                    } else if (ratingPoint == 4) {
                        dataRating.addStar4();
                    } else if (ratingPoint == 5) {
                        dataRating.addStar5();
                    }

                }

                vh1.getRatingBar().setRating(dataRating.getStarAvg());
                vh1.getTvRatingMean().setText(dataRating.getStarAvg() + " out of 5");
                vh1.getTvRatingAll().setText(dataRating.getUserAll() + " rating & review");
                vh1.getTv5star().setText("5 stars : " + dataRating.getStar5());
                vh1.getTv4star().setText("4 stars : " + dataRating.getStar4());
                vh1.getTv3star().setText("3 stars : " + dataRating.getStar3());
                vh1.getTv2star().setText("2 stars : " + dataRating.getStar2());
                vh1.getTv1star().setText("1 stars : " + dataRating.getStar1());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        User user = (User) items.get(position);
//        if (user != null) {
//        vh1.getDetail().setText(items.get(position).toString());
//        }
    }

    private void configureViewHolderAward(ViewHolderAward vh2, int position) {
//        vh2.getTvAward().setText("รางวัลที่ 1\nรางวัลที่ 2\nรางวัลที่ 3");
//        vh2.getImage().setImageResource(R.drawable.talad3);

        final List<WrapFdbAward> dataAward = (List<WrapFdbAward>) items.get(position);
        String txtAward = "";

        for (int i = 0; i < dataAward.size(); i++) {
            txtAward += dataAward.get(i).getData().getNameAward();

            if (i < dataAward.size() - 1) {
                txtAward += "\n";
            }
        }

        vh2.getTvAward().setText(txtAward);
    }

    private void configureViewHolderTime(ViewHolderTime vh2, int position) {
        String textTime = itemShop.getData().getMonday() + "\n" + itemShop.getData().getTuesday() +
                "\n" + itemShop.getData().getWednesday() + "\n" + itemShop.getData().getThursday() +
                "\n" + itemShop.getData().getFriday() + "\n" + itemShop.getData().getSaturday() +
                "\n" + itemShop.getData().getSunday();
        vh2.getTvDate().setText("วันจันทร์\nวันอังคาร\nวันพุธ\nวันพฤหัสบดี\nวันศุกร์\nวันเสาร์\nวันอาทิตย์");
        textTime = textTime.replace("null", "ปิด");
//        vh2.getTvTime().setText("ปิด\nปิด\n9:00 - 18:00\n9:00 - 18:00\n9:00 - 22:00\n9:00 - 22:00\n9:00 - 22:00");
        vh2.getTvTime().setText(textTime);
//        vh2.getImage().setImageResource(R.drawable.talad3);
    }

    private void configureViewHolderImageShop(final ViewHolderImageShop vh2, int position) {

        ArrayList<WrapFdbImage> data = (ArrayList<WrapFdbImage>) items.get(position);
        vh2.setImage(data);
        vh2.getTvName().setText(itemShop.getData().getNameShop());
//        vh2.getImage().setImageResource(R.drawable.talad3);
        if (mAuth.getCurrentUser() != null) {
            mRootRef.child("item-feeling").child(itemShop.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String key = postSnapshot.getKey();
                        FdbFeeling value = postSnapshot.getValue(FdbFeeling.class);
//
                        if (value != null) {
                            if (value.getUserID().equals(mAuth.getCurrentUser().getUid())) {
                                Log.d("item-comment", "==========================================================" + key);
                                Log.d("item-comment", "==========================================================" + value.getFeeling());

                                dataFeeling = new WrapFdbFeeling(key, value);
                                if (value.getFeeling().equals("love")) {
                                    vh2.getIvLove().setColorFilter(Color.RED);
                                } else {
                                    vh2.getIvLove().setColorFilter(Color.GRAY);
                                }
                                break;
                            }
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        vh2.getIvLove().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    String feeling = "";
                    if (dataFeeling != null) {
                        feeling = dataFeeling.getData().getFeeling();
                    }

                    if (feeling.equals("")) {
                        feeling = "love";
                        vh2.getIvLove().setColorFilter(Color.RED);

                    } else if (feeling.equals("love")) {
                        feeling = "";
                        vh2.getIvLove().setColorFilter(Color.GRAY);
                    }

                    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference mItemRef = mRootRef.child("item-feeling");
                    DatabaseReference mFeelingRef = mRootRef.child("feeling");

                    String userID = mAuth.getCurrentUser().getUid();
                    if (dataFeeling == null) {
                        String newKey = mFeelingRef.push().getKey();
                        FdbFeeling data = new FdbFeeling();
                        dataFeeling = new WrapFdbFeeling(newKey, data);
                    } else if (dataFeeling.getData() == null) {
                        String newKey = mFeelingRef.push().getKey();
                        FdbFeeling data = new FdbFeeling();
                        dataFeeling = new WrapFdbFeeling(newKey, data);
                    }
                    dataFeeling.getData().setFeeling(feeling);
                    dataFeeling.getData().setItemID(itemShop.getKey());
                    dataFeeling.getData().setUserID(userID);
                    dataFeeling.getData().setDate(DateTimeMillis.getDateMillisNow());
                    dataFeeling.getData().setTime(DateTimeMillis.getTimeMillisNow());

                    mItemRef.child(itemShop.getKey()).child(dataFeeling.getKey()).setValue(dataFeeling.getData());
                    mFeelingRef.child(dataFeeling.getKey()).setValue(dataFeeling.getData());
                } else {
                    inflater.getContext().startActivity(new Intent(inflater.getContext(), LoginActivity.class));
                }
            }
        });

        mRootRef.child("item-feeling").child(itemShop.getKey()).orderByChild("feeling").equalTo("love").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;
                DataRating dataRating = new DataRating();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbComment value = postSnapshot.getValue(FdbComment.class);
//
                    count++;

                }

                vh2.getTvCount().setText(count + "");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void configureViewHolderProduct(final ViewHolderProduct vh2, int position) {

        final DataProductItem data = (DataProductItem) items.get(position);
        setPhoto(data.getItemProduct().getKey(), vh2.getIvProduct());
//        vh2.getIvProduct().setImageResource(data.getImage());
        vh2.getTvName().setText(data.getItemProduct().getData().getNameProduct());
        vh2.getTvPrice().setText(data.getItemProduct().getData().getPrice() + " B");
        vh2.getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), ProductItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemShop", Parcels.wrap(data.getItemShop()));
                bundle.putParcelable("itemProduct", Parcels.wrap(data.getItemProduct()));
                intent.putExtra("ImageItem", data.getImage());
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
//        vh2.getImage().setImageResource(R.drawable.talad3);
        mRootRef.child("stock").child(data.getItemProduct().getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String keyPhoto = dataSnapshot.getKey();
                FdbStock value = dataSnapshot.getValue(FdbStock.class);

                if (value != null) {
                    vh2.getTvDetail().setText("stock : " + value.getQuantity());
                } else {
                    vh2.getTvDetail().setText("stock : 0");
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

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
