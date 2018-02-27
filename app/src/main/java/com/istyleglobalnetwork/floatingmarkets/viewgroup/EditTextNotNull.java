package com.istyleglobalnetwork.floatingmarkets.viewgroup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class EditTextNotNull extends LinearLayout {

    private TextInputLayout layoutValue;
    private EditText value;

    public EditTextNotNull(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
    }

    public EditTextNotNull(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
    }

    public EditTextNotNull(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
    }

    public EditTextNotNull(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
    }

    public void initInflate() {
        inflate(getContext(), R.layout.edittext_notnull, this);
    }

    public void initInstance() {
        layoutValue = (TextInputLayout) findViewById(R.id.layout_value);
        value = (EditText) findViewById(R.id.value);
    }

    public EditText getValue() {
        return value;
    }

    public void setValue(EditText value) {
        this.value = value;
    }

    public Boolean checkNull() {
        if (TextUtils.isEmpty(value.getText().toString())) {
            value.setError(getResources().getString(R.string.error_field_required));
            return false;
        }
        return true;
    }

    public void setHint(String text) {
        layoutValue.setHint(text);
    }

    public void setPasswordType(Boolean bool) {
        if (bool) {
            value.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void setEmailType(Boolean bool) {
        if (bool)
            value.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    public void setNumberType(Boolean bool) {
        if (bool)
            value.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
    }

    public String getText() {
        return this.value.getText().toString();
    }

    public void setText(String text) {
        value.setText(text);
    }

    public void setEnable(boolean bool) {
        value.setEnabled(bool);
    }

    public void setDateType(Boolean bool){
        if (bool)
            value.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_DATETIME_VARIATION_DATE);
    }


}
