package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.TravelItemActivity;

/**
 * Created by Sung on 20/11/2017 AD.
 */

public class RV_Adapter_List_Travel extends BaseAdapter {

    Context mContext;
    String[] listTravel;
    int[] imageTravel;

    public RV_Adapter_List_Travel(Context context, String[] listTravel, int[] imageTravel) {
        this.mContext = context;
        this.listTravel = listTravel;
        this.imageTravel = imageTravel;
    }

    @Override
    public int getCount() {
        return listTravel.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = mInflater.inflate(R.layout.card_travel, viewGroup, false);
        }

        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.rl);

        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        TextView tv = (TextView) view.findViewById(R.id.tv);

        tv.setText(listTravel[position]);
        iv.setImageResource(imageTravel[position]);

//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ZoneActivity.class);
//                intent.putExtra("NumberZone", "" + (position + 1));
//                mContext.startActivity(intent);
//            }
//        });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TravelItemActivity.class);
                intent.putExtra("NameItem", listTravel[position]);
                intent.putExtra("ImageItem", imageTravel[position]);
                mContext.startActivity(intent);
            }
        });


        return view;
    }
}
