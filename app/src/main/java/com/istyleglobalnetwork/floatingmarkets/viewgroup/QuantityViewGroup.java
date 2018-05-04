package com.istyleglobalnetwork.floatingmarkets.viewgroup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class QuantityViewGroup extends LinearLayout {

    private Button btnAdd;
    private Button btnRemove;
    private TextView tvQuantity;

    private int quantity = 1;

    public QuantityViewGroup(@NonNull Context context) {
        super(context);
        initInflate();
        initInstance();
        setBasic();
    }

    public QuantityViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstance();
        setBasic();
    }

    public QuantityViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstance();
        setBasic();
    }

    public QuantityViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstance();
        setBasic();
    }

    public void initInflate() {
        inflate(getContext(), R.layout.card_quantity, this);
    }

    public void initInstance() {
//        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnRemove = (Button) findViewById(R.id.btn_remove);
        tvQuantity = (TextView) findViewById(R.id.tv_qunatity);
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(Button btnAdd) {
        this.btnAdd = btnAdd;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(Button btnRemove) {
        this.btnRemove = btnRemove;
    }

    public TextView getTvQuantity() {
        return tvQuantity;
    }

    public void setTvQuantity(TextView tvQuantity) {
        this.tvQuantity = tvQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        getTvQuantity().setText(quantity + "");
    }

    public void plus(){
        this.quantity += 1;
        tvQuantity.setText(quantity + "");
    }

    public void minus(){
        if (quantity > 1) {
            this.quantity -= 1;
            tvQuantity.setText(quantity + "");
        }
    }

    private void setBasic() {

        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity += 1;
                tvQuantity.setText(quantity + "");
                if (quantity >= 20) {
                    btnAdd.setVisibility(INVISIBLE);
                } else if (quantity >= 1) {
                    btnRemove.setVisibility(VISIBLE);
                }
            }
        });
        btnRemove.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity -= 1;
                tvQuantity.setText(quantity + "");
                if (quantity <= 1) {
                    btnRemove.setVisibility(INVISIBLE);
                } else if (quantity <= 20) {
                    btnAdd.setVisibility(VISIBLE);
                }
            }
        });
    }
}
