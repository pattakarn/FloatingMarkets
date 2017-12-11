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

public class RV_Adapter_List_Comment extends BaseAdapter {

    Context mContext;
    String[] title_comment;
    String[] detail_comment;

    public RV_Adapter_List_Comment(Context context) {
        this.mContext = context;
        this.title_comment = mContext.getResources().getStringArray(R.array.title_comment);
        this.detail_comment = mContext.getResources().getStringArray(R.array.detail_comment);
    }

    @Override
    public int getCount() {
        return title_comment.length;
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
            view = mInflater.inflate(R.layout.list_comment, viewGroup, false);
        }

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);

        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_detail = (TextView) view.findViewById(R.id.tv_comment);
        tv_title.setText(title_comment[position]);
        tv_detail.setText(detail_comment[position]);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv);

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
