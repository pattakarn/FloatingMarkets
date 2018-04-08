package com.istyleglobalnetwork.floatingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FBAnalytics;
import com.istyleglobalnetwork.floatingmarkets.activity.hotel.HotelListActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.manage.ManageMainActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.network.NetworkListActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.service.ServiceListActivity;
import com.istyleglobalnetwork.floatingmarkets.activity.travel.TravelListActivity;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Main;
import com.istyleglobalnetwork.floatingmarkets.data.DataMainMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    RecyclerView rv;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        initInstances();
        mAuth = FirebaseAuth.getInstance();

//        setSupportActionBar(toolbar);

//        RV_Adapter_Main_Menu adapter = new RV_Adapter_Main_Menu(this);
//        gridview.setAdapter(adapter);

        ArrayList<Object> data = new ArrayList<Object>();
        data.add(new DataMainMenu(InformationActivity.class, "ประวัติ"));
        data.add(new DataMainMenu(HomeZoneActivity.class, "โซน"));
        data.add(new DataMainMenu(TravelListActivity.class, "สถานที่ท่องเที่ยว"));
        data.add(new DataMainMenu(MapsActivity.class, "แผนที่"));
        data.add(new DataMainMenu(NetworkListActivity.class, "เครือข่าย"));
        data.add(new DataMainMenu(ServiceListActivity.class, "บริการ"));
        data.add(new DataMainMenu(CarParkActivity.class, "ลานจอดรถ"));
        data.add(new DataMainMenu(HotelListActivity.class, "ที่พัก"));
        data.add(new DataMainMenu(ContactUsActivity.class, "ติดต่อเรา"));
        data.add(new DataMainMenu(ManageMainActivity.class, "Dashboard"));
//        data.add(new DataMainMenu(ScrollingActivity.class, "Test"));
//        data.add(new DataMainMenu(LoginActivity.class, "Login"));

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(glm);
        RV_Adapter_Main adapterList = new RV_Adapter_Main(data);
        rv.setAdapter(adapterList);

        FBAnalytics fbAnalytics = new FBAnalytics(MainActivity.this);

        if (mAuth.getCurrentUser() != null) {
            Crashlytics.setUserEmail(mAuth.getCurrentUser().getEmail());
            fbAnalytics.addUser(mAuth.getCurrentUser().getUid(), mAuth.getCurrentUser().getEmail());
        } else {
            Crashlytics.setUserEmail("-");
            fbAnalytics.addUser("-", "-");
        }
        fbAnalytics.EventAppOpen();

//        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        Bundle bundle = new Bundle();
//        bundle.putString("USER_ID", mAuth.getCurrentUser().getUid());
//        bundle.putString("EMAIL", mAuth.getCurrentUser().getEmail());
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);

//        Log.d("DateTimeNow", "===============================" + DateTimeMillis.getDateNow());
//        Log.d("DateTimeNow", "===============================" + DateTimeMillis.getTimeNow());
//        Log.d("DateTimeNow", "===============================" + DateTimeMillis.TimeToMillis(DateTimeMillis.getTimeNow()));
//        Log.d("DateTimeNow", "===============================" + DateTimeMillis.MillisToTime(DateTimeMillis.TimeToMillis(DateTimeMillis.getTimeNow())));

    }

    private void initInstances() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.rv);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_person:
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser!=null) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
