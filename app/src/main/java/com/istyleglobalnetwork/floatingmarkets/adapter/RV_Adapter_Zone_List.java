package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.ShopActivity;

/**
 * Created by Sung on 20/11/2017 AD.
 */

public class RV_Adapter_Zone_List extends BaseAdapter {

    Context mContext;
    String[] listShop;

    public RV_Adapter_Zone_List(Context context, String[] listShop) {
        this.mContext = context;
        this.listShop = listShop;
    }

    @Override
    public int getCount() {
        return listShop.length;
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
            view = mInflater.inflate(R.layout.list, viewGroup, false);
        }

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);

        TextView textView = (TextView) view.findViewById(R.id.tv);
        textView.setText(listShop[position]);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ShopActivity.class);
                intent.putExtra("NameShop", listShop[position]);
                mContext.startActivity(intent);
            }
        });


        return view;
    }
}
