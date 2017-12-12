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

public class RV_Adapter_Hotel_List extends BaseAdapter {

    Context mContext;
    String[] nameHotel;
    int[] imageHotel;

    public RV_Adapter_Hotel_List(Context context, String[] nameHotel, int[] imageHotel) {
        this.mContext = context;
        this.nameHotel = nameHotel;
        this.imageHotel = imageHotel;
    }

    @Override
    public int getCount() {
        return nameHotel.length;
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
            view = mInflater.inflate(R.layout.card_hotel, viewGroup, false);
        }

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_product);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView tvLocation = (TextView) view.findViewById(R.id.tv_location);
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_price);
        TextView tvRating = (TextView) view.findViewById(R.id.tv_rating);

        tvTitle.setText(nameHotel[position]);
        imageView.setImageResource(imageHotel[position]);
        if (position == 0){
            tvLocation.setText("ตลิ่งชัน");
            tvPrice.setText("500 บาท");
        } else if (position == 1){
            tvLocation.setText("ปิ่นเกล้า");
            tvPrice.setText("350 บาท");
        } else if (position == 2){
            tvLocation.setText("พุทธมณฑลสาย 1");
            tvPrice.setText("1000 บาท");
        }



        return view;
    }
}
