package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogManageShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbZone;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.UpdatePhotoActivity;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditPhoto;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditShopData;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditShopHead;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditShopOpentime;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Edit_Shop extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Edit_Shop(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_edit_shop_head, parent, false);
                viewHolder = new ViewHolderEditShopHead(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_edit_shop_data, parent, false);
                viewHolder = new ViewHolderEditShopData(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_edit_shop_opentime, parent, false);
                viewHolder = new ViewHolderEditShopOpentime(v3);
                break;
            case 3:
                View v4 = inflater.inflate(R.layout.card_edit_photo, parent, false);
                viewHolder = new ViewHolderEditPhoto(v4);
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
                ViewHolderEditShopHead vh1 = (ViewHolderEditShopHead) holder;
                configureViewHolderEditShopHead(vh1, position);
                break;
            case 1:
                ViewHolderEditShopData vh2 = (ViewHolderEditShopData) holder;
                configureViewHolderEditShopData(vh2, position);
                break;
            case 2:
                ViewHolderEditShopOpentime vh3 = (ViewHolderEditShopOpentime) holder;
                configureViewHolderEditShopOpentime(vh3, position);
                break;
            case 3:
                ViewHolderEditPhoto vh4 = (ViewHolderEditPhoto) holder;
                configureViewHolderEditPhoto(vh4, position);
                break;

            default:
//                ViewHolderEditProductPhoto vh = (ViewHolderEditProductPhoto) holder;
//                configureViewHolderEditProductPhoto(vh, position);
                break;
        }
    }

    private void configureViewHolderEditPhoto(ViewHolderEditPhoto vh3, int position) {
        List<Object> tempObject = (List<Object>) items.get(position);
        final WrapFdbShop dataShop = (WrapFdbShop) tempObject.get(0);
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
                bundle.putParcelable("itemShop", Parcels.wrap(dataShop));
                bundle.putParcelable("itemImage", Parcels.wrap(itemImage));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
    }

    private void configureViewHolderEditShopHead(ViewHolderEditShopHead vh1, int position) {

        List<String> dataHead = (List<String>) items.get(position);

        vh1.getColMarket().getTvTitle().setText("Market");
        vh1.getColZone().getTvTitle().setText("Zone");

        if (dataHead != null) {
            vh1.getColMarket().getTvValue().setText(dataHead.get(0));
            vh1.getColZone().getTvValue().setText(dataHead.get(1));
        }


    }

    private void configureViewHolderEditShopData(ViewHolderEditShopData vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbShop dataShop = (WrapFdbShop) tempObject.get(0);
        final WrapFdbZone dataZone = (WrapFdbZone) tempObject.get(1);

        vh1.getColName().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeText(dataShop, dataZone, "name");
            }
        });
        vh1.getColOwner().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeText(dataShop, dataZone, "owner");
            }
        });
        vh1.getColPhone().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeText(dataShop, dataZone, "phone");
            }
        });
        vh1.getColLine().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeText(dataShop, dataZone, "line");
            }
        });
        vh1.getColFacebook().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeText(dataShop, dataZone, "facebook");
            }
        });
        vh1.getColEmail().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeText(dataShop, dataZone, "email");
            }
        });

        vh1.getColName().getTvTitle().setText("Name");
        vh1.getColOwner().getTvTitle().setText("Owner Name");
        vh1.getColPhone().getTvTitle().setText("Phone");
        vh1.getColLine().getTvTitle().setText("Line ID");
        vh1.getColFacebook().getTvTitle().setText("Facebook");
        vh1.getColEmail().getTvTitle().setText("Email");

        if (dataShop.getData() != null) {
            vh1.getColName().getTvValue().setText(dataShop.getData().getNameShop());
            vh1.getColOwner().getTvValue().setText(dataShop.getData().getOwner());
            vh1.getColPhone().getTvValue().setText(dataShop.getData().getPhone());
            vh1.getColLine().getTvValue().setText(dataShop.getData().getLine());
            vh1.getColFacebook().getTvValue().setText(dataShop.getData().getFacebook());
            vh1.getColEmail().getTvValue().setText(dataShop.getData().getEmail());
        }


    }

    private void configureViewHolderEditShopOpentime(ViewHolderEditShopOpentime vh1, int position) {

//        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbShop dataShop = null;
        final WrapFdbZone dataZone = null;

        vh1.getColMonday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeOpentime(dataShop, dataZone, "monday");
            }
        });
        vh1.getColTuesday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeOpentime(dataShop, dataZone, "tuesday");
            }
        });
        vh1.getColWednesday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeOpentime(dataShop, dataZone, "wednesday");
            }
        });
        vh1.getColThursday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeOpentime(dataShop, dataZone, "thursday");
            }
        });
        vh1.getColFriday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeOpentime(dataShop, dataZone, "friday");
            }
        });
        vh1.getColSaturday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeOpentime(dataShop, dataZone, "saturday");
            }
        });
        vh1.getColSunday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageShop popup = new DialogManageShop(inflater.getContext());
                popup.Popup_ChangeOpentime(dataShop, dataZone, "sunday");
            }
        });

        vh1.getColMonday().getTvTitle().setText("Monday");
        vh1.getColTuesday().getTvTitle().setText("Tuesday");
        vh1.getColWednesday().getTvTitle().setText("Wednesday");
        vh1.getColThursday().getTvTitle().setText("Thursday");
        vh1.getColFriday().getTvTitle().setText("Friday");
        vh1.getColSaturday().getTvTitle().setText("Saturday");
        vh1.getColSunday().getTvTitle().setText("Sunday");

//        if (dataShop.getData() != null) {
//            vh1.getColMonday().getTvValue().setText(dataShop.getData().getNameShop());
//            vh1.getColTuesday().getTvValue().setText(dataShop.getData().getOwner());
//            vh1.getColWednesday().getTvValue().setText(dataShop.getData().getPhone());
//            vh1.getColThursday().getTvValue().setText(dataShop.getData().getLine());
//            vh1.getColFriday().getTvValue().setText(dataShop.getData().getFacebook());
//            vh1.getColSaturday().getTvValue().setText(dataShop.getData().getEmail());
//            vh1.getColFriday().getTvValue().setText(dataShop.getData().getEmail());
//        }


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
