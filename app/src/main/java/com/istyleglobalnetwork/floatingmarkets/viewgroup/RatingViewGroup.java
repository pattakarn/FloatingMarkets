package com.istyleglobalnetwork.floatingmarkets.viewgroup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RatingViewGroup extends LinearLayout {

    Button btn;

    public RatingViewGroup(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public RatingViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public RatingViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    public RatingViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    public void initInflate(){
        inflate(getContext(), R.layout.card_rating,this);
    }

    public void initInstance(){
//        tvTitle = (TextView) findViewById(R.id.tv_title);
        btn = (Button) findViewById(R.id.btn_rating);
        btn.setVisibility(View.INVISIBLE);

    }

    public void setTitle(String text){
//        tvTitle.setText(text);
    }
}
