package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogManageZone;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.UpdatePhotoActivity;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditPhoto;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditZoneData;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditZoneHead;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Edit_Zone extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Edit_Zone(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_edit_zone_head, parent, false);
                viewHolder = new ViewHolderEditZoneHead(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_edit_zone_data, parent, false);
                viewHolder = new ViewHolderEditZoneData(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_edit_photo, parent, false);
                viewHolder = new ViewHolderEditPhoto(v3);
                break;
            default:
//                View v = inflater.inflate(R.layout.card_edit_photo, parent, false);
//                viewHolder = new ViewHolderEditProductPhoto(v);
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolderEditZoneHead vh1 = (ViewHolderEditZoneHead) holder;
                configureViewHolderEditZoneHead(vh1, position);
                break;
            case 1:
                ViewHolderEditZoneData vh2 = (ViewHolderEditZoneData) holder;
                configureViewHolderEditZoneData(vh2, position);
                break;
            case 2:
                ViewHolderEditPhoto vh3 = (ViewHolderEditPhoto) holder;
                configureViewHolderEditPhoto(vh3, position);
                break;
            default:
//                ViewHolderEditProductPhoto vh = (ViewHolderEditProductPhoto) holder;
//                configureViewHolderEditProductPhoto(vh, position);
                break;
        }
    }

    private void configureViewHolderEditPhoto(ViewHolderEditPhoto vh3, int position) {
        List<Object> tempObject = (List<Object>) items.get(position);
        final WrapFdbZone dataZone = (WrapFdbZone) tempObject.get(0);
        final List<WrapFdbImage> itemImage = (List<WrapFdbImage>) tempObject.get(1);

        vh3.getIvg().getTvImage().setText("Photo (" + itemImage.size() + ")");
        GridLayoutManager glm = new GridLayoutManager(inflater.getContext(), 3);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        vh3.getIvg().getRv().setLayoutManager(glm);
        RV_Adapter_Grid_Image_Fdb adapterList = new RV_Adapter_Grid_Image_Fdb(itemImage);
        vh3.getIvg().getRv().setAdapter(adapterList);

        vh3.getIvg().getCv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), UpdatePhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemZone", Parcels.wrap(dataZone));
                bundle.putParcelable("itemImage", Parcels.wrap(itemImage));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
    }

    private void configureViewHolderEditZoneHead(ViewHolderEditZoneHead vh1, int position) {

        List<String> dataHead = (List<String>) items.get(position);

        vh1.getColMarket().getTvTitle().setText("Market");

        if (dataHead != null) {
            vh1.getColMarket().getTvValue().setText(dataHead.get(0));
        }


    }

    private void configureViewHolderEditZoneData(ViewHolderEditZoneData vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbZone dataZone = (WrapFdbZone) tempObject.get(0);
        final WrapFdbMarket dataMarket = (WrapFdbMarket) tempObject.get(1);

        vh1.getColName().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageZone popup = new DialogManageZone(inflater.getContext());
                popup.Popup_ChangeText(dataZone, dataMarket, "name");
            }
        });
        vh1.getColOwner().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageZone popup = new DialogManageZone(inflater.getContext());
                popup.Popup_ChangeText(dataZone, dataMarket, "owner");
            }
        });
        vh1.getColSize().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageZone popup = new DialogManageZone(inflater.getContext());
                popup.Popup_ChangeText(dataZone, dataMarket, "size");
            }
        });

        vh1.getColName().getTvTitle().setText("Name");
        vh1.getColOwner().getTvTitle().setText("Owner Name");
        vh1.getColSize().getTvTitle().setText("Size");

        if (dataZone.getData() != null) {
            vh1.getColName().getTvValue().setText(dataZone.getData().getNameZone());
            vh1.getColOwner().getTvValue().setText(dataZone.getData().getOwner());
            vh1.getColSize().getTvValue().setText(dataZone.getData().getSize());
        }


    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (items.get(position) instanceof Text) {
//            return TITLE;
//        } else if (items.get(position) instanceof String) {
//            return IMAGE;
//        }
        return position;
    }


}
