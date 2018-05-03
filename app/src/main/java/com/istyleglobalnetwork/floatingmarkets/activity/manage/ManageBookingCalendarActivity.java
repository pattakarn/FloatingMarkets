package com.istyleglobalnetwork.floatingmarkets.activity.manage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.DateTimeMillis;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbBookingList;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbBookingList;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter_manage.RV_Adapter_Booking_Bottom_Sheet;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ManageBookingCalendarActivity extends AppCompatActivity {

    TextView tvTitle;
    RecyclerView rv;
    CompactCalendarView compactCalendar;

    WrapFdbHotel itemHotel = null;
    WrapFdbRoom itemRoom = null;

    List<Object> data;

    DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_booking_calendar);

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

            itemRoom = Parcels.unwrap(bundle.getParcelable("itemRoom"));
            if (itemRoom != null) {
                tvTitle.setText(itemRoom.getData().getNameRoom());
//                tvDetail.setText(itemRoom.getData().getPrice() + " บาท");
            }

        }

        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
//        Event ev1 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");
//        compactCalendar.addEvent(ev1);

        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
//        Event ev2 = new Event(Color.GREEN, 1433704251000L);
//        compactCalendar.addEvent(ev2);

        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
//        List<Event> events = compactCalendar.getEvents(1433701251000L); // can also take a Date object

        // events has size 2 with the 2 events inserted previously
//        Log.d(TAG, "Events: " + events);

        // define a listener to receive callbacks when certain events happen.
//        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
//            @Override
//            public void onDayClick(Date dateClicked) {
//                List<Event> events = compactCalendar.getEvents(dateClicked);
//                Log.d("Calendar", "Day was clicked: " + dateClicked + " with events " + events);
//            }
//
//            @Override
//            public void onMonthScroll(Date firstDayOfNewMonth) {
//                Log.d("Calendar2", "Month was scrolled to: " + firstDayOfNewMonth);
//            }
//        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        setListItem();
    }

    private void setListItem() {

        mRootRef.child("booking-list").child(itemRoom.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<Object>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    FdbBookingList value = postSnapshot.getValue(FdbBookingList.class);
                    data.add(new WrapFdbBookingList(key, value));
                }


                for (Object temp : data){
                    WrapFdbBookingList bookTemp = (WrapFdbBookingList) temp;
                    String start = DateTimeMillis.MillisToDateCon(bookTemp.getData().getCheckin());
                    String end = DateTimeMillis.MillisToDateCon(bookTemp.getData().getCheckout());
                    List<Date> range = DateTimeMillis.getRangeDates(start, end);
                    for (Date date:range){
                        String dateData = DateTimeMillis.DateToString(date);
//                        Log.d("Hello Date", dateData);
                        Event ev1 = new Event(Color.GREEN, Long.parseLong(DateTimeMillis.DateToMillis(dateData)));
                        compactCalendar.addEvent(ev1);
                    }

                }

                compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                    @Override
                    public void onDayClick(Date dateClicked) {
                        List<Event> events = compactCalendar.getEvents(dateClicked);
                        Log.d("Calendar", "Day was clicked: " + dateClicked + " with events " + events);

                        List<WrapFdbBookingList> listData = new ArrayList<WrapFdbBookingList>();
                        for (Object temp : data){
                            WrapFdbBookingList bookTemp = (WrapFdbBookingList) temp;
                            String start = DateTimeMillis.MillisToDateCon(bookTemp.getData().getCheckin());
                            String end = DateTimeMillis.MillisToDateCon(bookTemp.getData().getCheckout());
                            if (DateTimeMillis.checkRangeDates(start, end, dateClicked)){
                                listData.add((WrapFdbBookingList) temp);
                            }
                        }
                        RV_Adapter_Booking_Bottom_Sheet adapter = new RV_Adapter_Booking_Bottom_Sheet(listData);
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onMonthScroll(Date firstDayOfNewMonth) {
                        Log.d("Calendar2", "Month was scrolled to: " + firstDayOfNewMonth);
                    }
                });

//                RV_Adapter_Manage_Room_Booking adapterList = new RV_Adapter_Manage_Room_Booking(data);
//                rv.setAdapter(adapterList);
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
        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
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
