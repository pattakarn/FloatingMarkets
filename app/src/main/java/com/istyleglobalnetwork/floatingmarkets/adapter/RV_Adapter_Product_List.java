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

public class RV_Adapter_Product_List extends BaseAdapter {

    Context mContext;
    String[] listItem;
    int[] imageItem;

    public RV_Adapter_Product_List(Context context, String[] listItem, int[] imageItem) {
        this.mContext = context;
        this.listItem = listItem;
        this.imageItem = imageItem;
    }

    @Override
    public int getCount() {
        return listItem.length;
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
            view = mInflater.inflate(R.layout.card_product, viewGroup, false);
        }

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_product);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_detail = (TextView) view.findViewById(R.id.tv_detail);
        TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
        TextView tv_rating = (TextView) view.findViewById(R.id.tv_rating);

        tv_title.setText(listItem[position]);
        imageView.setImageResource(imageItem[position]);
        if (position == 0){
            tv_detail.setText("ไอศรีมรสรวมมิตร ใส่ถ้วย");
            tv_price.setText("20 บาท");
        } else if (position == 1){
            tv_detail.setText("ไอศรีมรสรวมมิตร ใส่ถขนมปัง");
            tv_price.setText("15 บาท");
        } else if (position == 2){
            tv_detail.setText("ไอศรีมรสรวมมิตร ใส่ถลูกมะพร้าว");
            tv_price.setText("30 บาท");
        }





        return view;
    }
}
