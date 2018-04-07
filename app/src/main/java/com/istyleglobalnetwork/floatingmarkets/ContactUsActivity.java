package com.istyleglobalnetwork.floatingmarkets;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ContactViewGroup;

public class ContactUsActivity extends AppCompatActivity {

    ContactViewGroup gContact;
    DatabaseReference mRootRef;

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

//        String clientToken = FirebaseInstanceId.getInstance().getToken();
//        FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications");
//        Log.d("GetToken", "================================= " + clientToken);
//
//        FdbMessage message = new FdbMessage("user1", clientToken, "Title", "Hello");
//        mRootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference mMessageRef = mRootRef.child("messages");
//
//        String key = mMessageRef.push().getKey();
//        mMessageRef.child(key).setValue(message);

//        FirebaseMessaging fm = FirebaseMessaging.getInstance();
//        fm.send(new RemoteMessage.Builder("project-8640332612@gcm.googleapis.com")
//                .setMessageId(Integer.toString(1234))
//                .addData("my_message", "Hello World")
//                .addData("my_action","SAY_HELLO")
//                .build());


//        Sender fcm = new Sender("project-8640332612");
//
//        Pushraven.setAccountFile(new File("service_account.json"));
//        Pushraven.setProjectId("project-8640332612");
//// build the message
//        Message message = new Message.MessageBuilder()
//                .toToken(clientToken) // single android/ios device
//                .addData("title","test1")
//                .addData("body","First Test")
//                .build();
//// send the message
//        fcm.send(message);
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
