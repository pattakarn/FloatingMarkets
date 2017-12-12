package com.istyleglobalnetwork.floatingmarkets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class HeaderViewGroup extends LinearLayout {

    public HeaderViewGroup(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public HeaderViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public HeaderViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    public HeaderViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    public void initInflate(){
        inflate(getContext(),R.layout.header_floating_market,this);
    }

    public void initInstance(){
//        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    public void setTitle(String text){
//        tvTitle.setText(text);
    }
}
