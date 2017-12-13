package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.istyleglobalnetwork.floatingmarkets.viewgroup.ContactViewGroup;

public class ContactUsActivity extends AppCompatActivity {

    ContactViewGroup gContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initInstances();

        gContact.getTvLink().setText("www.istyleglobalnetwork.com\nwww.istyle.in.th");
        gContact.getTvEmail().setText("istyleglobalnetwork@gmail.com");
    }

    private void initInstances() {
        // init instance with rootView.findViewById here
        gContact = (ContactViewGroup) findViewById(R.id.group_contact);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
