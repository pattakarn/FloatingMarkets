package com.istyleglobalnetwork.floatingmarkets.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.shop.ShopListActivity;

/**
 * Created by Sung on 1/17/2017 AD.
 */

public class RV_Adapter_Zone_Menu extends BaseAdapter {

    private Context mContext;
    private String[] menu;

    public RV_Adapter_Zone_Menu(Context context) {
        this.mContext = context;
        menu = mContext.getResources().getStringArray(R.array.main_menu);
    }

    public int getCount() {
        return menu.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(mContext);

            // get layout from grid_item.xml ( Defined Below )

            gridView = inflater.inflate(R.layout.card_zone_menu, null);

            // set value into textview
            LinearLayout ll = (LinearLayout) gridView.findViewById(R.id.ll);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.iv);
//            TextView textView = (TextView) gridView.findViewById(R.id.tv_title);

//            textView.setText(menu[position]);
//            if (position == 0) {
////                iv_menu.setImageResource(R.drawable.ic_launcher_background);
//                textView.setText("ประวัติ");
//
//            } else if (position == 1) {
////                iv_menu.setImageResource(R.drawable.personnal);
//                textView.setText("โซน");
//            } else if (position == 2) {
////                iv_menu.setImageResource(R.drawable.calendar);
//                textView.setText("สถานที่ท่องเที่ยว");
//            } else if (position == 3) {
////                iv_menu.setImageResource(R.drawable.personnal);
//                textView.setText("เครือข่าย");
//            } else if (position == 4) {
////                iv_menu.setImageResource(R.drawable.personnal);
//                textView.setText("บริการ");
//            } else if (position == 5) {
////                iv_menu.setImageResource(R.drawable.personnal);
//                textView.setText("ลานจอดรถ");
//            } else if (position == 6) {
////                iv_menu.setImageResource(R.drawable.personnal);
//                textView.setText("ที่พัก");
//            }
            if (position == 0){
                imageView.setImageResource(R.drawable.zone_1);
            } else if (position == 1){
                imageView.setImageResource(R.drawable.zone_2);
            } else if (position == 2){
                imageView.setImageResource(R.drawable.zone_3);
            } else if (position == 3){
                imageView.setImageResource(R.drawable.zone_4);
            } else if (position == 4){
                imageView.setImageResource(R.drawable.zone_5);
            } else if (position == 5){
                imageView.setImageResource(R.drawable.zone_6);
            } else if (position == 6){
                imageView.setImageResource(R.drawable.zone_7);
            } else if (position == 7){
                imageView.setImageResource(R.drawable.zone_8);
            }

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ShopListActivity.class);
                    intent.putExtra("NumberZone", "" + (position + 1));
                    mContext.startActivity(intent);
                }
            });

//            if (!keyCompany.equals("-")) {
//                iv_menu.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (position == 0) {
//                            Intent pageHome = new Intent(mContext, AddEditActivity.class);
//                            Bundle a = new Bundle();
//                            a.putString("Select", "company");
//                            a.putString("Key", keyCompany);
//                            pageHome.putExtras(a);
////                        inflater.getContext().startActivity(pageHome);
//                            activity.startActivityForResult(pageHome, RC_EDIT_COMPANY);
//                        } else if (position == 1) {
//                            Intent pageHome = new Intent(mContext, CompanyActivity.class);
//                            Bundle a = new Bundle();
//                            a.putString("Select", "personal");
//                            a.putString("Name", nameCompany);
//                            a.putString("Key", keyCompany);
//                            pageHome.putExtras(a);
////                        inflater.getContext().startActivity(pageHome);
//                            activity.startActivityForResult(pageHome, RC_PERSONNAL_COMPANY);
//                        } else if (position == 2) {
//                            Intent pageHome = new Intent(mContext, CompanyActivity.class);
//                            Bundle a = new Bundle();
//                            a.putString("Select", "calendar");
//                            a.putString("Name", nameCompany);
//                            a.putString("Key", keyCompany);
//                            pageHome.putExtras(a);
////                        inflater.getContext().startActivity(pageHome);
//                            activity.startActivityForResult(pageHome, RC_CALENDAR_COMPANY);
//                        } else if (position == 3) {
//                            Intent pageHome = new Intent(mContext, CompanyActivity.class);
//                            Bundle a = new Bundle();
//                            a.putString("Select", "contact");
//                            a.putString("Name", nameCompany);
//                            a.putString("Key", keyCompany);
//                            pageHome.putExtras(a);
////                        inflater.getContext().startActivity(pageHome);
//                            activity.startActivityForResult(pageHome, RC_PERSONNAL_COMPANY);
//                        } else if (position == 4) {
//                            Intent pageHome = new Intent(mContext, CompanyActivity.class);
//                            Bundle a = new Bundle();
//                            a.putString("Select", "supplyer");
//                            a.putString("Name", nameCompany);
//                            a.putString("Key", keyCompany);
//                            pageHome.putExtras(a);
////                        inflater.getContext().startActivity(pageHome);
//                            activity.startActivityForResult(pageHome, RC_PERSONNAL_COMPANY);
//                        } else if (position == 5) {
//                            Intent pageHome = new Intent(mContext, ManagementActivity.class);
//                            Bundle a = new Bundle();
//                            a.putString("Select", "solution");
//                            a.putString("key", keyCompany);
//                            pageHome.putExtras(a);
//                            inflater.getContext().startActivity(pageHome);
////                            Intent pageHome = new Intent(mContext, CompanyActivity.class);
////                            Bundle a = new Bundle();
////                            a.putString("Select", "solutionmain");
////                            a.putString("Name", nameCompany);
////                            a.putString("Key", keyCompany);
////                            pageHome.putExtras(a);
////                            inflater.getContext().startActivity(pageHome);
//                        } else if (position == 6) {
//                            Intent pageHome = new Intent(mContext, ManagementActivity.class);
//                            Bundle a = new Bundle();
//                            a.putString("Select", "messagecontactcompany");
//                            a.putString("key", keyCompany);
//                            pageHome.putExtras(a);
//                            inflater.getContext().startActivity(pageHome);
//                        } else if (position == 7) {
//                            Intent pageHome = new Intent(mContext, CompanyActivity.class);
//                            Bundle a = new Bundle();
//                            a.putString("Select", "note");
//                            a.putString("Name", nameCompany);
//                            a.putString("Key", keyCompany);
//                            pageHome.putExtras(a);
////                        inflater.getContext().startActivity(pageHome);
//                            activity.startActivityForResult(pageHome, RC_PERSONNAL_COMPANY);
//                        }
//                    }
//                });
//            }


            // set image based on selected text


        } else {

            gridView = (View) convertView;
        }

        return gridView;
    }

}
