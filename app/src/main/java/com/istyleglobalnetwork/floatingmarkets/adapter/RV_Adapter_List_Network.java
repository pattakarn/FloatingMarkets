package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 20/11/2017 AD.
 */

public class RV_Adapter_List_Network extends BaseAdapter {

    Context mContext;
    String[] listNetwork;
    int[] imageNetwork;

    public RV_Adapter_List_Network(Context context, String[] listNetwork, int[] imageNetwork) {
        this.mContext = context;
        this.listNetwork = listNetwork;
        this.imageNetwork = imageNetwork;
    }

    @Override
    public int getCount() {
        return listNetwork.length;
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

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);

        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        TextView tv = (TextView) view.findViewById(R.id.tv);

        tv.setText(listNetwork[position]);
        iv.setImageResource(imageNetwork[position]);


//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ZoneActivity.class);
//                intent.putExtra("NumberZone", "" + (position + 1));
//                mContext.startActivity(intent);
//            }
//        });


        return view;
    }
}
