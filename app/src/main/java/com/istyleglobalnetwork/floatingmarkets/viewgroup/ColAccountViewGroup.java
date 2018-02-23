package com.istyleglobalnetwork.floatingmarkets.viewgroup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ColAccountViewGroup extends LinearLayout {

    private TextView tvTitle;
    private TextView tvValue;

    public ColAccountViewGroup(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public ColAccountViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public ColAccountViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    public ColAccountViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    public void initInflate(){
        inflate(getContext(), R.layout.col_account,this);
    }

    public void initInstance(){

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvValue = (TextView) findViewById(R.id.tv_value);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvValue() {
        return tvValue;
    }

    public void setTvValue(TextView tvValue) {
        this.tvValue = tvValue;
    }
}
