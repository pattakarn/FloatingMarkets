package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.viewgroup.ColAccountViewGroup;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderEditHotelData extends RecyclerView.ViewHolder {

    private ColAccountViewGroup colName;
    private ColAccountViewGroup colOwner;
    private ColAccountViewGroup colPhone;
    private ColAccountViewGroup colLine;
    private ColAccountViewGroup colFacebook;
    private ColAccountViewGroup colEmail;

    public ViewHolderEditHotelData(View itemView) {
        super(itemView);
        this.colName = (ColAccountViewGroup) itemView.findViewById(R.id.col_name);
        this.colOwner = (ColAccountViewGroup) itemView.findViewById(R.id.col_owner);
        this.colPhone = (ColAccountViewGroup) itemView.findViewById(R.id.col_phone);
        this.colLine = (ColAccountViewGroup) itemView.findViewById(R.id.col_line);
        this.colFacebook = (ColAccountViewGroup) itemView.findViewById(R.id.col_facebook);
        this.colEmail = (ColAccountViewGroup) itemView.findViewById(R.id.col_email);
    }

    public ColAccountViewGroup getColName() {
        return colName;
    }

    public void setColName(ColAccountViewGroup colName) {
        this.colName = colName;
    }

    public ColAccountViewGroup getColOwner() {
        return colOwner;
    }

    public void setColOwner(ColAccountViewGroup colOwner) {
        this.colOwner = colOwner;
    }

    public ColAccountViewGroup getColPhone() {
        return colPhone;
    }

    public void setColPhone(ColAccountViewGroup colPhone) {
        this.colPhone = colPhone;
    }

    public ColAccountViewGroup getColLine() {
        return colLine;
    }

    public void setColLine(ColAccountViewGroup colLine) {
        this.colLine = colLine;
    }

    public ColAccountViewGroup getColFacebook() {
        return colFacebook;
    }

    public void setColFacebook(ColAccountViewGroup colFacebook) {
        this.colFacebook = colFacebook;
    }

    public ColAccountViewGroup getColEmail() {
        return colEmail;
    }

    public void setColEmail(ColAccountViewGroup colEmail) {
        this.colEmail = colEmail;
    }
}
