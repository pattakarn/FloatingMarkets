package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.istyleglobalnetwork.floatingmarkets.viewgroup.ContactViewGroup;

public class ContactUsActivity extends AppCompatActivity {

    ContactViewGroup gContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initInstances();

        gContact.getTvLink().setText("www.istyleglobalnetwork.com\nwww.istyle.in.th");
        gContact.getTvEmail().setText("istyleglobalnetwork@gmail.com");
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        gContact = (ContactViewGroup) findViewById(R.id.group_contact);

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
