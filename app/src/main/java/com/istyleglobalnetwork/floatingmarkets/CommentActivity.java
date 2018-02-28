package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogComment;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbComment;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbComment;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Comment_Item;
import com.istyleglobalnetwork.floatingmarkets.data.DataRating;

import org.parceler.Parcels;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tvTitle;
    Button btn;

    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;

    String itemID;
    String itemName;

    DatabaseReference mRootRef;
    ArrayList<Object> dataComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setTitle("Comment");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            itemShop = Parcels.unwrap(bundle.getParcelable("itemShop"));
            if (itemShop != null) {
                tvTitle.setText(itemShop.getData().getNameShop());
                itemID = itemShop.getKey();
                itemName = itemShop.getData().getNameShop();
            }

            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));
            if (itemProduct != null) {
                tvTitle.setText(itemProduct.getData().getNameProduct());
                itemID = itemProduct.getKey();
                itemName = itemProduct.getData().getNameProduct();
            }

        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        setListComment();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                if (mAuth == null) {
                    startActivity(new Intent(CommentActivity.this, LoginActivity.class));
                } else {
                    DialogComment popup = new DialogComment(CommentActivity.this);
                    popup.Popup_ChangeComment(itemID);
                }

            }
        });

    }

    private void setListComment() {
        mRootRef.child("item-comment").child(itemID).orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataComment = new ArrayList<Object>();
                DataRating dataRating = new DataRating();
                dataComment.add("Rating");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbComment value = postSnapshot.getValue(FdbComment.class);
//
                    Log.d("item-comment", "==========================================================" + key);
                    Log.d("item-comment", "==========================================================" + value.getComment());

                    dataRating.addStarAll(value.getRating());
                    dataRating.addUserAll();

                    int ratingPoint = (int) value.getRating();
                    if (ratingPoint == 1){
                        dataRating.addStar1();
                    } else if (ratingPoint == 2){
                        dataRating.addStar2();
                    } else if (ratingPoint == 3){
                        dataRating.addStar3();
                    } else if (ratingPoint == 4){
                        dataRating.addStar4();
                    } else if (ratingPoint == 5){
                        dataRating.addStar5();
                    }

                    dataComment.add(new WrapFdbComment(key, value));
                }

                dataComment.set(0, dataRating);

                RV_Adapter_Comment_Item adapterList = new RV_Adapter_Comment_Item(dataComment);
                rv.setAdapter(adapterList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);
        btn = (Button) findViewById(R.id.btn);

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
}
