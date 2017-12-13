package com.istyleglobalnetwork.floatingmarkets.viewgroup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ContactViewGroup extends LinearLayout {

    private TextView tvLink;
    private TextView tvPhone;
    private TextView tvLine;
    private TextView tvFb;
    private TextView tvEmail;

    public ContactViewGroup(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public ContactViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public ContactViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    public ContactViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    public void initInflate(){
        inflate(getContext(), R.layout.card_contact,this);
    }

    public void initInstance(){
//        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvLink = (TextView) findViewById(R.id.tv_link);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvLine = (TextView) findViewById(R.id.tv_line);
        tvFb = (TextView) findViewById(R.id.tv_fb);
        tvEmail = (TextView) findViewById(R.id.tv_email);
    }

    public TextView getTvLink() {
        return tvLink;
    }

    public void setTvLink(TextView tvLink) {
        this.tvLink = tvLink;
    }

    public TextView getTvPhone() {
        return tvPhone;
    }

    public void setTvPhone(TextView tvPhone) {
        this.tvPhone = tvPhone;
    }

    public TextView getTvLine() {
        return tvLine;
    }

    public void setTvLine(TextView tvLine) {
        this.tvLine = tvLine;
    }

    public TextView getTvFb() {
        return tvFb;
    }

    public void setTvFb(TextView tvFb) {
        this.tvFb = tvFb;
    }

    public TextView getTvEmail() {
        return tvEmail;
    }

    public void setTvEmail(TextView tvEmail) {
        this.tvEmail = tvEmail;
    }
}
