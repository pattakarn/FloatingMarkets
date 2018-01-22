package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Product_Cart(List<WrapFdbOrder> items) {
        this.items = items;
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

    private void configureViewHolderProductCart(ViewHolderProductCart vh1, int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
        WrapFdbOrder dataOrder = items.get(position);

        getProduct(vh1, dataOrder.getData().getProductID());
        vh1.getQvgQuantity().setQuantity(dataOrder.getData().getQuantity());
        vh1.getIvDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(inflater.getContext());
                builder.setMessage("คุณต้องการจะลบออเดอร์ใช่หรือไม่ ?");
                builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(inflater.getContext(),
                                "ขอบคุณครับ", Toast.LENGTH_SHORT).show();
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

    private void getProduct(final ViewHolderProductCart vh1, String productID) {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("product").child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbProduct value = dataSnapshot.getValue(FdbProduct.class);

                vh1.getTvName().setText(value.getNameProduct());
                vh1.getTvPrice().setText(value.getPrice() + " บาท");
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
