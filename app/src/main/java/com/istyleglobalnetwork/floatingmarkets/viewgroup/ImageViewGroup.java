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

public class ImageViewGroup extends LinearLayout {

    private CardView cv;
    private TextView tvImage;
    private RecyclerView rv;

    public ImageViewGroup(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public ImageViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public ImageViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    public ImageViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    public void initInflate(){
        inflate(getContext(), R.layout.card_image_viewgroup,this);
    }

    public void initInstance(){
//        tvTitle = (TextView) findViewById(R.id.tv_title);
        cv = (CardView) findViewById(R.id.cv);
        tvImage = (TextView) findViewById(R.id.tv_image);
        rv = (RecyclerView) findViewById(R.id.rv);
    }

    public CardView getCv() {
        return cv;
    }

    public void setCv(CardView cv) {
        this.cv = cv;
    }

    public TextView getTvImage() {
        return tvImage;
    }

    public void setTvImage(TextView tvImage) {
        this.tvImage = tvImage;
    }

    public RecyclerView getRv() {
        return rv;
    }

    public void setRv(RecyclerView rv) {
        this.rv = rv;
    }
}
