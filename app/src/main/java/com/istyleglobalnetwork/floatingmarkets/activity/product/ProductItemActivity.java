package com.istyleglobalnetwork.floatingmarkets.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
import com.istyleglobalnetwork.floatingmarkets.BuyActivity;
import com.istyleglobalnetwork.floatingmarkets.CartListActivity;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FBAnalytics;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Product_Item;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ProductItemActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    Button btnBuy;
    int imageItem;

    WrapFdbShop itemShop = null;
    WrapFdbProduct itemProduct = null;
    ArrayList<Object> data = new ArrayList<Object>();
    List<Object> itemImage = new ArrayList<Object>();

    DatabaseReference mRootRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_item);

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
            itemProduct = Parcels.unwrap(bundle.getParcelable("itemProduct"));

        }

        imageItem = bundle.getInt("ImageItem");
        tvTitle.setText(itemProduct.getData().getNameProduct());
//        DataProductItem productItem = new DataProductItem(itemShop, itemProduct, imageItem);
        setListImage();

        mAuth = FirebaseAuth.getInstance();
        FBAnalytics fbAnalytics = new FBAnalytics(ProductItemActivity.this);
        fbAnalytics.addItem(itemProduct.getKey(), itemProduct.getData().getNameProduct(), itemProduct.getData().getPrice(), 0);
        if (mAuth.getCurrentUser() == null){
            fbAnalytics.addUser("-", "-");
        } else {
            fbAnalytics.addUser(mAuth.getCurrentUser().getUid(), mAuth.getCurrentUser().getEmail());
        }
        fbAnalytics.EventViewItem();


        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ProductItemActivity.this, CartListActivity.class);
//                startActivity(intent);

                Intent intent = new Intent(ProductItemActivity.this, BuyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemShop", Parcels.wrap(itemShop));
                bundle.putParcelable("itemProduct", Parcels.wrap(itemProduct));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setListImage() {
        String product = itemProduct.getKey();
        mRootRef.child("item-photo").child(product).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemImage = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbImage value = postSnapshot.getValue(FdbImage.class);
                    itemImage.add(new WrapFdbImage(key, value));
                }

                data = new ArrayList<Object>();
                data.add(itemImage);
                data.add(itemShop.getData().getNameShop());
                data.add(itemProduct.getData().getDescription());
                data.add(itemProduct.getData().getNameProduct());
                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(llm);
                RV_Adapter_Product_Item adapterList = new RV_Adapter_Product_Item(itemProduct, data);
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
        btnBuy = (Button) findViewById(R.id.btn_buy);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_cart:
                Intent intent = new Intent(ProductItemActivity.this, CartListActivity.class);
                startActivity(intent);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
