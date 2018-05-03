package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.istyleglobalnetwork.floatingmarkets.FireDB.FdbUser;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbBookingList;
import com.istyleglobalnetwork.floatingmarkets.R;

import java.util.List;

/**
 * Created by Sung on 1/28/2016 AD.
 */
public class RV_Adapter_Booking_Bottom_Sheet extends RecyclerView.Adapter<RV_Adapter_Booking_Bottom_Sheet.MyViewHolder> {

    List<WrapFdbBookingList> listData;

    View itemView;
    LayoutInflater inflater;

    private static final int RC_SCHEDULE_EDIT = 206;

    public RV_Adapter_Booking_Bottom_Sheet(List<WrapFdbBookingList> listData) {
        this.listData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_calendar_booking, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
//        if (position %2 == 1){
//            viewHolder.ll.setBackgroundColor(inflater.getContext().getResources().getColor(R.color.blue50));
//        } else {
//            viewHolder.ll.setBackgroundColor(inflater.getContext().getResources().getColor(R.color.white));
//        }
//        viewHolder.tv_title.setText(scheduleList.get(position).getSubject());
//        viewHolder.tv_comment.setText(scheduleList.get(position).getDescription());
//        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pageHome = new Intent(inflater.getContext(), AddEditActivity.class);
//                Bundle a = new Bundle();
//                a.putString("Select", "schedule");
//                a.putString("Key", keyList.get(position));
//                pageHome.putExtras(a);
////                inflater.getContext().startActivity(pageHome);
//                fa.startActivityForResult(pageHome, RC_SCHEDULE_EDIT);
//            }
//        });

//        Log.d("pppp", position + " +++++++++ " + lastPosition);
//        if (position > lastPosition) {
//            Animation anim = AnimationUtils.loadAnimation(inflater.getContext(), R.anim.up_from_bottom);
//            itemView.startAnimation(anim);
//            lastPosition = position;
//        }
        viewHolder.tv_name.setText("ชื่อผู้จอง : ");
        viewHolder.tv_phone.setText("");
        viewHolder.tv_email.setText("");
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.child("user").child(listData.get(position).getData().getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                FdbUser value = dataSnapshot.getValue(FdbUser.class);
                viewHolder.tv_name.setText("ชื่อผู้จอง : " + value.getNameContact());
                viewHolder.tv_phone.setText(value.getPhone());
                viewHolder.tv_email.setText(value.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        viewHolder.tv_title.setText(listData.get(position).getData().getUserID());
//        String start = DateTimeMillis.MillisToDate(listData.get(position).getData().getCheckin());
//        String end = DateTimeMillis.MillisToDate(listData.get(position).getData().getCheckout());
//        String comment = "Check in : " + start + " -> Check out : " + end;
//        viewHolder.tv_comment.setText(comment);
//        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent pageHome = new Intent(inflater.getContext(), AddEditActivity.class);
////                Bundle a = new Bundle();
////                a.putString("Select", "schedule");
////                a.putString("Key", listData.get(position).getKey());
////                pageHome.putExtras(a);
//////                inflater.getContext().startActivity(pageHome);
////                fa.startActivityForResult(pageHome, RC_SCHEDULE_EDIT);
//                AlertDialog.Builder builder = new AlertDialog.Builder(inflater.getContext());
//                CreatePopupDialog CPD = new CreatePopupDialog(builder, inflater);
//                CPD.PopupEditPlanner(listData.get(position), fdEvent, null);
//            }
//        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
//        return scheduleList.size();
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_name;
        public TextView tv_phone;
        public TextView tv_email;
        public LinearLayout ll;

        private MyViewHolder(View v) {
            super(v);
            this.ll = (LinearLayout) v.findViewById(R.id.ll);
            this.tv_name = (TextView) v.findViewById(R.id.tv_name);
            this.tv_phone = (TextView) v.findViewById(R.id.tv_phone);
            this.tv_email = (TextView) v.findViewById(R.id.tv_email);

        }
    }

    public static class Data_Detail {

        String Title;
        String Message;

        public Data_Detail(String Title, String Message) {
            this.Title = Title;
            this.Message = Message;
        }

    }

}

