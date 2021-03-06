package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogManageProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbProduct;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbShop;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.manage.ManagePhotoActivity;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditPhoto;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditProductData;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditProductHead;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Edit_Product extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Edit_Product(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View v1 = inflater.inflate(R.layout.card_edit_product_head, parent, false);
                viewHolder = new ViewHolderEditProductHead(v1);
                break;
            case 1:
                View v2 = inflater.inflate(R.layout.card_edit_product_data, parent, false);
                viewHolder = new ViewHolderEditProductData(v2);
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
                ViewHolderEditProductHead vh1 = (ViewHolderEditProductHead) holder;
                configureViewHolderEditProductHead(vh1, position);
                break;
            case 1:
                ViewHolderEditProductData vh2 = (ViewHolderEditProductData) holder;
                configureViewHolderEditProductData(vh2, position);
                break;
            case 2:
                ViewHolderEditPhoto vh3 = (ViewHolderEditPhoto) holder;
                configureViewHolderEditProductPhoto(vh3, position);
                break;
            default:
//                ViewHolderEditProductPhoto vh = (ViewHolderEditProductPhoto) holder;
//                configureViewHolderEditProductPhoto(vh, position);
                break;
        }
    }

    private void configureViewHolderEditProductPhoto(ViewHolderEditPhoto vh3, int position) {
        List<Object> tempObject = (List<Object>) items.get(position);
        final WrapFdbProduct dataProduct = (WrapFdbProduct) tempObject.get(0);
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
                bundle.putParcelable("itemProduct", Parcels.wrap(dataProduct));
//                bundle.putParcelable("itemImage", Parcels.wrap(itemImage));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
    }

    private void configureViewHolderEditProductHead(ViewHolderEditProductHead vh1, int position) {

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
        vh1.getColZone().getTvTitle().setText("Zone");
        vh1.getColShop().getTvTitle().setText("Shop");

        if (dataHead != null) {
            vh1.getColMarket().getTvValue().setText(dataHead.get(0));
            vh1.getColZone().getTvValue().setText(dataHead.get(1));
            vh1.getColShop().getTvValue().setText(dataHead.get(2));
        }


    }

    private void configureViewHolderEditProductData(ViewHolderEditProductData vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbProduct dataProduct = (WrapFdbProduct) tempObject.get(0);
        final WrapFdbShop dataShop = (WrapFdbShop) tempObject.get(1);

        vh1.getColName().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageProduct popup = new DialogManageProduct(inflater.getContext());
                popup.Popup_ChangeName(dataProduct, dataShop);
            }
        });
        vh1.getColType().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageProduct popup = new DialogManageProduct(inflater.getContext());
                popup.Popup_ChangeType(dataProduct, dataShop);
            }
        });
        vh1.getColDetail().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageProduct popup = new DialogManageProduct(inflater.getContext());
                popup.Popup_ChangeDetail(dataProduct, dataShop);
            }
        });
        vh1.getColPrice().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageProduct popup = new DialogManageProduct(inflater.getContext());
                popup.Popup_ChangePrice(dataProduct, dataShop);
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

        if (dataProduct.getData() != null) {
            vh1.getColName().getTvValue().setText(dataProduct.getData().getNameProduct());
            vh1.getColType().getTvValue().setText(dataProduct.getData().getType());
            vh1.getColDetail().getTvValue().setText(dataProduct.getData().getDescription());
            vh1.getColPrice().getTvValue().setText(dataProduct.getData().getPrice() + " บาท");
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
