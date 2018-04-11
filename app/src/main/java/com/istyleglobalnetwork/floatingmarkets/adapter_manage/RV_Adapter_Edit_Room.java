package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogManageRoom;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbHotel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbRoom;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.manage.ManagePhotoActivity;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditPhoto;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditRoomData;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditRoomOption;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditShopHead;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Edit_Room extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Edit_Room(List<Object> items) {
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
                View v2 = inflater.inflate(R.layout.card_edit_room_data, parent, false);
                viewHolder = new ViewHolderEditRoomData(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_edit_room_option, parent, false);
                viewHolder = new ViewHolderEditRoomOption(v3);
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
                configureViewHolderEditRoomHead(vh1, position);
                break;
            case 1:
                ViewHolderEditRoomData vh2 = (ViewHolderEditRoomData) holder;
                configureViewHolderEditRoomData(vh2, position);
                break;
            case 2:
                ViewHolderEditRoomOption vh3 = (ViewHolderEditRoomOption) holder;
                configureViewHolderEditRoomOption(vh3, position);
                break;
            case 3:
                ViewHolderEditPhoto vh4 = (ViewHolderEditPhoto) holder;
                configureViewHolderEditRoomPhoto(vh4, position);
                break;
            default:
//                ViewHolderEditProductPhoto vh = (ViewHolderEditProductPhoto) holder;
//                configureViewHolderEditRoomPhoto(vh, position);
                break;
        }
    }

    private void configureViewHolderEditRoomPhoto(ViewHolderEditPhoto vh3, int position) {
        List<Object> tempObject = (List<Object>) items.get(position);
        final WrapFdbRoom dataRoom = (WrapFdbRoom) tempObject.get(0);
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
//                Intent intent = new Intent(inflater.getContext(), UpdatePhotoActivity.class);
                Intent intent = new Intent(inflater.getContext(), ManagePhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemRoom", Parcels.wrap(dataRoom));
//                bundle.putParcelable("itemImage", Parcels.wrap(itemImage));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
    }

    private void configureViewHolderEditRoomHead(ViewHolderEditShopHead vh1, int position) {

        List<String> dataHead = (List<String>) items.get(position);

//        vh1.getColMarket().getLl().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogLoginAndProfile popup = new DialogLoginAndProfile(inflater.getContext());
//                popup.Popup_ChangeContactName(dataUser);
//            }
//        });
//        vh1.getColZone().getLl().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogLoginAndProfile popup = new DialogLoginAndProfile(inflater.getContext());
//                popup.Popup_ChangeGender(dataUser);
//            }
//        });
//        vh1.getColShop().getLl().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogLoginAndProfile popup = new DialogLoginAndProfile(inflater.getContext());
//                popup.Popup_ChangeDate(dataUser);
//            }
//        });

//        vh1.getColName().getLl().setOnClickListener(this);
//        vh1.getColSex().getLl().setOnClickListener(this);
//        vh1.getColBirth().getLl().setOnClickListener(this);
//        vh1.getColPhone().getLl().setOnClickListener(this);


        vh1.getColMarket().getTvTitle().setText("Market");
        vh1.getColZone().getTvTitle().setText("Hotel");

        if (dataHead != null) {
            vh1.getColMarket().getTvValue().setText(dataHead.get(0));
            vh1.getColZone().getTvValue().setText(dataHead.get(1));
        }


    }

    private void configureViewHolderEditRoomData(ViewHolderEditRoomData vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbRoom dataRoom = (WrapFdbRoom) tempObject.get(0);
        final WrapFdbHotel dataHotel = (WrapFdbHotel) tempObject.get(1);

        vh1.getColName().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeName(dataRoom, dataHotel);
            }
        });
        vh1.getColType().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeType(dataRoom, dataHotel);
            }
        });
        vh1.getColDetail().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeDetail(dataRoom, dataHotel);
            }
        });
        vh1.getColPrice().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangePrice(dataRoom, dataHotel);
            }
        });

//        vh1.getColName().getLl().setOnClickListener(this);
//        vh1.getColSex().getLl().setOnClickListener(this);
//        vh1.getColBirth().getLl().setOnClickListener(this);
//        vh1.getColPhone().getLl().setOnClickListener(this);


        vh1.getColName().getTvTitle().setText("Name");
        vh1.getColType().getTvTitle().setText("Type");
        vh1.getColDetail().getTvTitle().setText("Detail");
        vh1.getColPrice().getTvTitle().setText("Price");

        if (dataRoom.getData() != null) {
            vh1.getColName().getTvValue().setText(dataRoom.getData().getNameRoom());
            vh1.getColType().getTvValue().setText(dataRoom.getData().getType());
            vh1.getColDetail().getTvValue().setText(dataRoom.getData().getDescription());
            vh1.getColPrice().getTvValue().setText(dataRoom.getData().getPrice() + " บาท");
        }


    }

    private void configureViewHolderEditRoomOption(ViewHolderEditRoomOption vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbRoom dataRoom = (WrapFdbRoom) tempObject.get(0);
        final WrapFdbHotel dataHotel = (WrapFdbHotel) tempObject.get(1);

        vh1.getColSize().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeSize(dataRoom, dataHotel);
            }
        });
        vh1.getColGuest().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeGuest(dataRoom, dataHotel);
            }
        });
        vh1.getColBed().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeBed(dataRoom, dataHotel);
            }
        });
        vh1.getColBreakfast().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeBrackfast(dataRoom, dataHotel);
            }
        });
        vh1.getColWifi().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeWifi(dataRoom, dataHotel);
            }
        });
        vh1.getColAir().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeAir(dataRoom, dataHotel);
            }
        });
        vh1.getColSmoke().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageRoom popup = new DialogManageRoom(inflater.getContext());
                popup.Popup_ChangeSmoke(dataRoom, dataHotel);
            }
        });


//        vh1.getColName().getLl().setOnClickListener(this);
//        vh1.getColSex().getLl().setOnClickListener(this);
//        vh1.getColBirth().getLl().setOnClickListener(this);
//        vh1.getColPhone().getLl().setOnClickListener(this);


        vh1.getColSize().getTvTitle().setText("Size");
        vh1.getColGuest().getTvTitle().setText("Guest");
        vh1.getColBed().getTvTitle().setText("Bed");
        vh1.getColBreakfast().getTvTitle().setText("Breakfast");
        vh1.getColWifi().getTvTitle().setText("Wifi");
        vh1.getColAir().getTvTitle().setText("Air");
        vh1.getColSmoke().getTvTitle().setText("Non-Smoking");

        if (dataRoom.getData() != null) {
            vh1.getColSize().getTvValue().setText(dataRoom.getData().getSize() + " square meter");
            vh1.getColGuest().getTvValue().setText("Max " + dataRoom.getData().getGuest() + " guests");
            vh1.getColBed().getTvValue().setText(dataRoom.getData().getBed());
            vh1.getColBreakfast().getTvValue().setText(dataRoom.getData().getBreakfast());
            vh1.getColWifi().getTvValue().setText(dataRoom.getData().getWifi());
            vh1.getColAir().getTvValue().setText(dataRoom.getData().getAir());
            vh1.getColSmoke().getTvValue().setText(dataRoom.getData().getSmoke());
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
