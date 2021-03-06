package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbOrder;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderProductCart;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Product_Cart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WrapFdbOrder> items;
    TextView tvTotal;
    int total;
    LayoutInflater inflater;
    DatabaseReference mRootRef;
    StorageReference storageRef;
    StorageReference folderRef, imageRef;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Product_Cart(List<WrapFdbOrder> items, int total, TextView tvTotal) {
        this.items = items;
        this.total = total;
        this.tvTotal = tvTotal;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        storageRef = FirebaseStorage.getInstance().getReference();
        folderRef = storageRef.child("photos");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.card_product_cart, parent, false);
        viewHolder = new ViewHolderProductCart(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderProductCart vh = (ViewHolderProductCart) holder;
        configureViewHolderProductCart(vh, position);
    }

    private void configureViewHolderProductCart(final ViewHolderProductCart vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        final WrapFdbOrder dataOrder = items.get(position);

        getProduct(vh1, dataOrder.getData().getProductID(), dataOrder);
        vh1.getQvgQuantity().setQuantity(dataOrder.getData().getQuantity());
        vh1.getIvDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(inflater.getContext());
                builder.setMessage("คุณต้องการจะลบออเดอร์ใช่หรือไม่ ?");
                builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeProduct(dataOrder.getKey());
                        Toast.makeText(inflater.getContext(),
                                "ลบเรียบร้อย", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

    }

    private void getProduct(final ViewHolderProductCart vh1, String productID, final WrapFdbOrder tempOrder) {
        final DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("product").child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String key = dataSnapshot.getKey();
                final FdbProduct value = dataSnapshot.getValue(FdbProduct.class);

                vh1.getQvgQuantity().getBtnAdd().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vh1.getQvgQuantity().plus();
                        tempOrder.getData().plus(value.getPrice());
                        total += value.getPrice();
                        tvTotal.setText(total + " บาท");
                    }
                });
                vh1.getQvgQuantity().getBtnRemove().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vh1.getQvgQuantity().minus();
                        tempOrder.getData().minus(value.getPrice());
                        total -= value.getPrice();
                        tvTotal.setText(total + " บาท");
                    }
                });

                mRootRef.child("item-photo").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                    Glide.with(inflater.getContext())
                                            .load(uri.toString())
//                                            .placeholder(R.mipmap.ic_floating_market)
                                            .into(vh1.getIvProduct());
                                }
                            });
                            break;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                vh1.getTvName().setText(value.getNameProduct());
                vh1.getTvPrice().setText(value.getPrice() + " บาท");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void removeProduct(String keyOrder){
        DatabaseReference mOrderRef = mRootRef.child("order");
        DatabaseReference mUserOrderRef = mRootRef.child("user-order");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mOrderRef.child(keyOrder).setValue(null);
        mUserOrderRef.child(currentUser.getUid()).child(keyOrder).setValue(null);

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

    public List<WrapFdbOrder> getItems(){
        return this.items;
    }


}
