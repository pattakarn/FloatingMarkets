package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogManageTravel;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbTravel;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.activity.manage.ManagePhotoActivity;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditPhoto;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditShopAward;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditShopOpentime;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditTravelData;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditTravelLocation;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditZoneHead;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Edit_Travel extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Edit_Travel(List<Object> items) {
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
                View v2 = inflater.inflate(R.layout.card_edit_travel_data, parent, false);
                viewHolder = new ViewHolderEditTravelData(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_edit_travel_location, parent, false);
                viewHolder = new ViewHolderEditTravelLocation(v3);
                break;
            case 3:
                View v4 = inflater.inflate(R.layout.card_edit_shop_award, parent, false);
                viewHolder = new ViewHolderEditShopAward(v4);
                break;
            case 4:
                View v5 = inflater.inflate(R.layout.card_edit_shop_opentime, parent, false);
                viewHolder = new ViewHolderEditShopOpentime(v5);
                break;
            case 5:
                View v6 = inflater.inflate(R.layout.card_edit_photo, parent, false);
                viewHolder = new ViewHolderEditPhoto(v6);
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
                configureViewHolderEditTravelHead(vh1, position);
                break;
            case 1:
                ViewHolderEditTravelData vh2 = (ViewHolderEditTravelData) holder;
                configureViewHolderEditTravelData(vh2, position);
                break;
            case 2:
                ViewHolderEditTravelLocation vh3 = (ViewHolderEditTravelLocation) holder;
                configureViewHolderEditTravelLocation(vh3, position);
                break;
            case 3:
                ViewHolderEditShopAward vh4 = (ViewHolderEditShopAward) holder;
                configureViewHolderEditTravelAward(vh4, position);
                break;
            case 4:
                ViewHolderEditShopOpentime vh5 = (ViewHolderEditShopOpentime) holder;
                configureViewHolderEditTravelOpentime(vh5, position);
                break;
            case 5:
                ViewHolderEditPhoto vh6 = (ViewHolderEditPhoto) holder;
                configureViewHolderEditPhoto(vh6, position);
                break;

            default:
//                ViewHolderEditProductPhoto vh = (ViewHolderEditProductPhoto) holder;
//                configureViewHolderEditProductPhoto(vh, position);
                break;
        }
    }

    private void configureViewHolderEditTravelHead(ViewHolderEditZoneHead vh1, int position) {

        List<String> dataHead = (List<String>) items.get(position);

        vh1.getColMarket().getTvTitle().setText("Market");

        if (dataHead != null) {
            vh1.getColMarket().getTvValue().setText(dataHead.get(0));
        }


    }

    private void configureViewHolderEditTravelData(ViewHolderEditTravelData vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbTravel dataTravel = (WrapFdbTravel) tempObject.get(0);
        final WrapFdbMarket dataMarket = (WrapFdbMarket) tempObject.get(1);

        vh1.getColName().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "name");
            }
        });
        vh1.getColOwner().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "owner");
            }
        });
        vh1.getColPhone().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "phone");
            }
        });
        vh1.getColLine().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "line");
            }
        });
        vh1.getColFacebook().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "facebook");
            }
        });
        vh1.getColEmail().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "email");
            }
        });
        vh1.getColDetail().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "detail");
            }
        });

        vh1.getColName().getTvTitle().setText("Name");
        vh1.getColOwner().getTvTitle().setText("Owner Name");
        vh1.getColPhone().getTvTitle().setText("Phone");
        vh1.getColLine().getTvTitle().setText("Line ID");
        vh1.getColFacebook().getTvTitle().setText("Facebook");
        vh1.getColEmail().getTvTitle().setText("Email");
        vh1.getColDetail().getTvTitle().setText("Detail");

        if (dataTravel.getData() != null) {
            vh1.getColName().getTvValue().setText(dataTravel.getData().getNameTravel());
            vh1.getColOwner().getTvValue().setText(dataTravel.getData().getOwner());
            vh1.getColPhone().getTvValue().setText(dataTravel.getData().getPhone());
            vh1.getColLine().getTvValue().setText(dataTravel.getData().getLine());
            vh1.getColFacebook().getTvValue().setText(dataTravel.getData().getFacebook());
            vh1.getColEmail().getTvValue().setText(dataTravel.getData().getEmail());
            vh1.getColDetail().getTvValue().setText(dataTravel.getData().getDetail());
        }
    }

    private void configureViewHolderEditTravelLocation(ViewHolderEditTravelLocation vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbTravel dataTravel = (WrapFdbTravel) tempObject.get(0);
        final WrapFdbMarket dataMarket = (WrapFdbMarket) tempObject.get(1);

        vh1.getColAddress().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "address");
            }
        });
        vh1.getColLatitude().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "latitude");
            }
        });
        vh1.getColLongitude().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeText(dataTravel, dataMarket, "longitude");
            }
        });

        vh1.getColAddress().getTvTitle().setText("Address");
        vh1.getColLatitude().getTvTitle().setText("Latitude");
        vh1.getColLongitude().getTvTitle().setText("Longitude");

        if (dataTravel.getData() != null) {
            vh1.getColAddress().getTvValue().setText(dataTravel.getData().getAddress());
            vh1.getColLatitude().getTvValue().setText(dataTravel.getData().getLatitude());
            vh1.getColLongitude().getTvValue().setText(dataTravel.getData().getLongitude());
        }


    }

    private void configureViewHolderEditTravelAward(ViewHolderEditShopAward vh3, int position) {
        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbTravel dataTravel = (WrapFdbTravel) tempObject.get(0);
        final List<WrapFdbAward> dataAward = (List<WrapFdbAward>) tempObject.get(1);

        vh3.getColAward1().getTvTitle().setText("Award 1");
        vh3.getColAward2().getTvTitle().setText("Award 2");
        vh3.getColAward3().getTvTitle().setText("Award 3");

        vh3.getColAward1().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                if (dataAward.size() > 0) {
                    popup.Popup_ChangeAward(dataTravel, dataAward.get(0), "award1");
                } else {
                    popup.Popup_ChangeAward(dataTravel, null, "award1");
                }
            }
        });
        vh3.getColAward2().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                if (dataAward.size() > 1) {
                    popup.Popup_ChangeAward(dataTravel, dataAward.get(1), "award2");
                } else {
                    popup.Popup_ChangeAward(dataTravel, null, "award2");
                }
            }
        });
        vh3.getColAward3().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                if (dataAward.size() > 2) {
                    popup.Popup_ChangeAward(dataTravel, dataAward.get(2), "award3");
                } else {
                    popup.Popup_ChangeAward(dataTravel, null, "award3");
                }
            }
        });
        for (int i = 0; i < dataAward.size(); i++) {
            if (i == 0) {
                vh3.getColAward1().getTvValue().setText(dataAward.get(i).getData().getNameAward());
            } else if (i == 1) {
                vh3.getColAward2().getTvValue().setText(dataAward.get(i).getData().getNameAward());
            } else if (i == 2) {
                vh3.getColAward3().getTvValue().setText(dataAward.get(i).getData().getNameAward());
            }
        }


    }

    private void configureViewHolderEditTravelOpentime(ViewHolderEditShopOpentime vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbTravel dataTravel = (WrapFdbTravel) tempObject.get(0);
        final WrapFdbMarket dataMarket = (WrapFdbMarket) tempObject.get(1);

        vh1.getColMonday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeOpentime(dataTravel, dataMarket, "monday");
            }
        });
        vh1.getColTuesday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeOpentime(dataTravel, dataMarket, "tuesday");
            }
        });
        vh1.getColWednesday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeOpentime(dataTravel, dataMarket, "wednesday");
            }
        });
        vh1.getColThursday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeOpentime(dataTravel, dataMarket, "thursday");
            }
        });
        vh1.getColFriday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeOpentime(dataTravel, dataMarket, "friday");
            }
        });
        vh1.getColSaturday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeOpentime(dataTravel, dataMarket, "saturday");
            }
        });
        vh1.getColSunday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageTravel popup = new DialogManageTravel(inflater.getContext());
                popup.Popup_ChangeOpentime(dataTravel, dataMarket, "sunday");
            }
        });

        vh1.getColMonday().getTvTitle().setText("Monday");
        vh1.getColTuesday().getTvTitle().setText("Tuesday");
        vh1.getColWednesday().getTvTitle().setText("Wednesday");
        vh1.getColThursday().getTvTitle().setText("Thursday");
        vh1.getColFriday().getTvTitle().setText("Friday");
        vh1.getColSaturday().getTvTitle().setText("Saturday");
        vh1.getColSunday().getTvTitle().setText("Sunday");

        if (dataTravel.getData() != null) {
            vh1.getColMonday().getTvValue().setText(dataTravel.getData().getMonday());
            vh1.getColTuesday().getTvValue().setText(dataTravel.getData().getTuesday());
            vh1.getColWednesday().getTvValue().setText(dataTravel.getData().getWednesday());
            vh1.getColThursday().getTvValue().setText(dataTravel.getData().getThursday());
            vh1.getColFriday().getTvValue().setText(dataTravel.getData().getFriday());
            vh1.getColSaturday().getTvValue().setText(dataTravel.getData().getSaturday());
            vh1.getColSunday().getTvValue().setText(dataTravel.getData().getSunday());
        }


    }

    private void configureViewHolderEditPhoto(ViewHolderEditPhoto vh3, int position) {
        List<Object> tempObject = (List<Object>) items.get(position);
        final WrapFdbTravel dataTravel = (WrapFdbTravel) tempObject.get(0);
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
                bundle.putParcelable("itemTravel", Parcels.wrap(dataTravel));
//                bundle.putParcelable("itemImage", Parcels.wrap(itemImage));
                intent.putExtras(bundle);
                inflater.getContext().startActivity(intent);
            }
        });
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
