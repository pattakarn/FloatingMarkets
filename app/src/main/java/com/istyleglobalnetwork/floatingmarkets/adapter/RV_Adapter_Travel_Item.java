package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.CommentActivity;
import com.istyleglobalnetwork.floatingmarkets.DateTimeMillis;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbComment;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbFeeling;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbFeeling;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbTravel;
import com.istyleglobalnetwork.floatingmarkets.LoginActivity;
import com.istyleglobalnetwork.floatingmarkets.MapsActivity;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderContact;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderImageTravel2;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderLocation;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderRating;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderText1;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderTime;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Travel_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    WrapFdbTravel itemTravel;
    LayoutInflater inflater;

    WrapFdbFeeling dataFeeling;
    DatabaseReference mRootRef;
    FirebaseAuth mAuth;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Travel_Item(WrapFdbTravel itemTravel, List<Object> items, LayoutInflater inflater) {
        this.itemTravel = itemTravel;
        this.items = items;
        this.inflater = inflater;

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_image_travel_rv, parent, false);
                viewHolder = new ViewHolderImageTravel2(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_location, parent, false);
                viewHolder = new ViewHolderLocation(v2);
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
            case 5:
                View v6 = inflater.inflate(R.layout.card_text2, parent, false);
                viewHolder = new ViewHolderText1(v6);
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
                ViewHolderImageTravel2 vh1 = (ViewHolderImageTravel2) holder;
                configureViewHolderImageTravel(vh1, position);
                break;
            case 1:
                ViewHolderLocation vh2 = (ViewHolderLocation) holder;
                configureViewHolderLocation(vh2, position);
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
            case 5:
                ViewHolderText1 vh6 = (ViewHolderText1) holder;
                configureViewHolderText2(vh6, position);
                break;
            default:
//                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) viewHolder;
//                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    private void configureViewHolderImageTravel(final ViewHolderImageTravel2 vh2, int position) {

        ArrayList<WrapFdbImage> data = (ArrayList<WrapFdbImage>) items.get(position);
        vh2.setImage(data);
        vh2.getTvName().setText(itemTravel.getData().getNameTravel());
//        vh2.getImage().setImageResource(R.drawable.talad3);
        if (mAuth.getCurrentUser() != null) {
            mRootRef.child("item-feeling").child(itemTravel.getKey()).addValueEventListener(new ValueEventListener() {
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
                    dataFeeling.getData().setItemID(itemTravel.getKey());
                    dataFeeling.getData().setUserID(userID);
                    dataFeeling.getData().setDate(DateTimeMillis.getDateMillisNow());
                    dataFeeling.getData().setTime(DateTimeMillis.getTimeMillisNow());

                    mItemRef.child(itemTravel.getKey()).child(dataFeeling.getKey()).setValue(dataFeeling.getData());
                    mFeelingRef.child(dataFeeling.getKey()).setValue(dataFeeling.getData());
                } else {
                    inflater.getContext().startActivity(new Intent(inflater.getContext(), LoginActivity.class));
                }
            }
        });

        mRootRef.child("item-feeling").child(itemTravel.getKey()).orderByChild("feeling").equalTo("love").addValueEventListener(new ValueEventListener() {
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

    private void configureViewHolderLocation(ViewHolderLocation vh2, int position) {
//        vh2.getImage().setImageResource(R.drawable.talad3);
//        vh2.getImage().setImageResource(R.drawable.talad3);
        final WrapFdbTravel data = (WrapFdbTravel) items.get(position);
//        vh2.getTvLocation().setText("58/1 ถนนเพชรเกษม ซอยเพชรเกษม 41 แขวงบางแค เขตบางแค กรุงเทพมหานคร 10160");
        vh2.getTvLocation().setText(data.getData().getAddress());
        vh2.getIbMap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), MapsActivity.class);
                intent.putExtra("namePlace", data.getData().getNameTravel());
                intent.putExtra("latitude", data.getData().getLatitude());
                intent.putExtra("longitude", data.getData().getLongitude());
                inflater.getContext().startActivity(intent);
            }
        });

    }

    private void configureViewHolderTime(ViewHolderTime vh2, int position) {
        String textTime = itemTravel.getData().getMonday() + "\n" + itemTravel.getData().getTuesday() +
                "\n" + itemTravel.getData().getWednesday() + "\n" + itemTravel.getData().getThursday() +
                "\n" + itemTravel.getData().getFriday() + "\n" + itemTravel.getData().getSaturday() +
                "\n" + itemTravel.getData().getSunday();
        vh2.getTvDate().setText("วันจันทร์\nวันอังคาร\nวันพุธ\nวันพฤหัสบดี\nวันศุกร์\nวันเสาร์\nวันอาทิตย์");
        textTime = textTime.replace("null", "ปิด");
//        vh2.getTvTime().setText("ปิด\nปิด\n9:00 - 18:00\n9:00 - 18:00\n9:00 - 22:00\n9:00 - 22:00\n9:00 - 22:00");
        vh2.getTvTime().setText(textTime);
//        vh2.getImage().setImageResource(R.drawable.talad3);
    }

    private void configureViewHolderContact(ViewHolderContact vh1, int position) {
        WrapFdbTravel data = (WrapFdbTravel) items.get(position);
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
                bundle.putParcelable("itemTravel", Parcels.wrap(itemTravel));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });

        mRootRef.child("item-comment").child(itemTravel.getKey()).orderByChild("date").addValueEventListener(new ValueEventListener() {
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

    private void configureViewHolderText2(ViewHolderText1 vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        WrapFdbTravel data = (WrapFdbTravel) items.get(position);
        vh1.getTitle().setText("Detail");
        vh1.getDetail().setText(data.getData().getDetail());
//        }
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
