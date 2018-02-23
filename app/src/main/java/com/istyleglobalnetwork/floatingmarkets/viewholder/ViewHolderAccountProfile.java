package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderAccountProfile extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colName;
    private ColAccountViewGroup colEmail;
    private ColAccountViewGroup colSex;
    private ColAccountViewGroup colBirth;

    public ViewHolderAccountProfile(View itemView) {
        super(itemView);
        this.colName = (ColAccountViewGroup) itemView.findViewById(R.id.col_name);
        this.colEmail = (ColAccountViewGroup) itemView.findViewById(R.id.col_email);
        this.colSex = (ColAccountViewGroup) itemView.findViewById(R.id.col_sex);
        this.colBirth = (ColAccountViewGroup) itemView.findViewById(R.id.col_birth);
    }

    public ColAccountViewGroup getColName() {
        return colName;
    }

    public void setColName(ColAccountViewGroup colName) {
        this.colName = colName;
    }

    public ColAccountViewGroup getColEmail() {
        return colEmail;
    }

    public void setColEmail(ColAccountViewGroup colEmail) {
        this.colEmail = colEmail;
    }

    public ColAccountViewGroup getColSex() {
        return colSex;
    }

    public void setColSex(ColAccountViewGroup colSex) {
        this.colSex = colSex;
    }

    public ColAccountViewGroup getColBirth() {
        return colBirth;
    }

    public void setColBirth(ColAccountViewGroup colBirth) {
        this.colBirth = colBirth;
    }
}
