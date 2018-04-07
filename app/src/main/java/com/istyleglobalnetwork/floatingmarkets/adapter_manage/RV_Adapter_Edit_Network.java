package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.DialogPopup.DialogManageNetwork;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbAward;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbImage;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbMarket;
import com.istyleglobalnetwork.floatingmarkets.FireDB.WrapFdbNetwork;
import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.adapter.RV_Adapter_Grid_Image_Fdb;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditNetworkData;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditPhoto;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditShopAward;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditShopOpentime;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderEditZoneHead;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Edit_Network extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, IMAGE = 1;

    public RV_Adapter_Edit_Network(List<Object> items) {
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
                View v2 = inflater.inflate(R.layout.card_edit_network_data, parent, false);
                viewHolder = new ViewHolderEditNetworkData(v2);
                break;
            case 2:
                View v3 = inflater.inflate(R.layout.card_edit_shop_award, parent, false);
                viewHolder = new ViewHolderEditShopAward(v3);
                break;
            case 3:
                View v4 = inflater.inflate(R.layout.card_edit_shop_opentime, parent, false);
                viewHolder = new ViewHolderEditShopOpentime(v4);
                break;
            case 4:
                View v5 = inflater.inflate(R.layout.card_edit_photo, parent, false);
                viewHolder = new ViewHolderEditPhoto(v5);
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
                configureViewHolderEditNetworkHead(vh1, position);
                break;
            case 1:
                ViewHolderEditNetworkData vh2 = (ViewHolderEditNetworkData) holder;
                configureViewHolderEditNetworkData(vh2, position);
                break;
            case 2:
                ViewHolderEditShopAward vh3 = (ViewHolderEditShopAward) holder;
                configureViewHolderEditNetworkAward(vh3, position);
                break;
            case 3:
                ViewHolderEditShopOpentime vh4 = (ViewHolderEditShopOpentime) holder;
                configureViewHolderEditNetworkOpentime(vh4, position);
                break;
            case 4:
                ViewHolderEditPhoto vh5 = (ViewHolderEditPhoto) holder;
                configureViewHolderEditPhoto(vh5, position);
                break;

            default:
//                ViewHolderEditProductPhoto vh = (ViewHolderEditProductPhoto) holder;
//                configureViewHolderEditProductPhoto(vh, position);
                break;
        }
    }

    private void configureViewHolderEditNetworkHead(ViewHolderEditZoneHead vh1, int position) {

        List<String> dataHead = (List<String>) items.get(position);

        vh1.getColMarket().getTvTitle().setText("Market");

        if (dataHead != null) {
            vh1.getColMarket().getTvValue().setText(dataHead.get(0));
        }


    }

    private void configureViewHolderEditNetworkData(ViewHolderEditNetworkData vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbNetwork dataNetwork = (WrapFdbNetwork) tempObject.get(0);
        final WrapFdbMarket dataMarket = (WrapFdbMarket) tempObject.get(1);

        vh1.getColName().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeText(dataNetwork, dataMarket, "name");
            }
        });
        vh1.getColOwner().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeText(dataNetwork, dataMarket, "owner");
            }
        });
        vh1.getColPhone().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeText(dataNetwork, dataMarket, "phone");
            }
        });
        vh1.getColLine().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeText(dataNetwork, dataMarket, "line");
            }
        });
        vh1.getColFacebook().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeText(dataNetwork, dataMarket, "facebook");
            }
        });
        vh1.getColEmail().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeText(dataNetwork, dataMarket, "email");
            }
        });

        vh1.getColName().getTvTitle().setText("Name");
        vh1.getColOwner().getTvTitle().setText("Owner Name");
        vh1.getColPhone().getTvTitle().setText("Phone");
        vh1.getColLine().getTvTitle().setText("Line ID");
        vh1.getColFacebook().getTvTitle().setText("Facebook");
        vh1.getColEmail().getTvTitle().setText("Email");

        if (dataNetwork.getData() != null) {
            vh1.getColName().getTvValue().setText(dataNetwork.getData().getNameNetwork());
            vh1.getColOwner().getTvValue().setText(dataNetwork.getData().getOwner());
            vh1.getColPhone().getTvValue().setText(dataNetwork.getData().getPhone());
            vh1.getColLine().getTvValue().setText(dataNetwork.getData().getLine());
            vh1.getColFacebook().getTvValue().setText(dataNetwork.getData().getFacebook());
            vh1.getColEmail().getTvValue().setText(dataNetwork.getData().getEmail());
        }


    }

    private void configureViewHolderEditNetworkAward(ViewHolderEditShopAward vh3, int position) {
        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbNetwork dataNetwork = (WrapFdbNetwork) tempObject.get(0);
        final List<WrapFdbAward> dataAward = (List<WrapFdbAward>) tempObject.get(1);

        vh3.getColAward1().getTvTitle().setText("Award 1");
        vh3.getColAward2().getTvTitle().setText("Award 2");
        vh3.getColAward3().getTvTitle().setText("Award 3");

        vh3.getColAward1().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                if (dataAward.size() > 0) {
                    popup.Popup_ChangeAward(dataNetwork, dataAward.get(0), "award1");
                } else {
                    popup.Popup_ChangeAward(dataNetwork, null, "award1");
                }
            }
        });
        vh3.getColAward2().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                if (dataAward.size() > 1) {
                    popup.Popup_ChangeAward(dataNetwork, dataAward.get(1), "award2");
                } else {
                    popup.Popup_ChangeAward(dataNetwork, null, "award2");
                }
            }
        });
        vh3.getColAward3().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                if (dataAward.size() > 2) {
                    popup.Popup_ChangeAward(dataNetwork, dataAward.get(2), "award3");
                } else {
                    popup.Popup_ChangeAward(dataNetwork, null, "award3");
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

    private void configureViewHolderEditNetworkOpentime(ViewHolderEditShopOpentime vh1, int position) {

        ArrayList<Object> tempObject = (ArrayList<Object>) items.get(position);
        final WrapFdbNetwork dataNetwork = (WrapFdbNetwork) tempObject.get(0);
        final WrapFdbMarket dataMarket = (WrapFdbMarket) tempObject.get(1);

        vh1.getColMonday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeOpentime(dataNetwork, dataMarket, "monday");
            }
        });
        vh1.getColTuesday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeOpentime(dataNetwork, dataMarket, "tuesday");
            }
        });
        vh1.getColWednesday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeOpentime(dataNetwork, dataMarket, "wednesday");
            }
        });
        vh1.getColThursday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeOpentime(dataNetwork, dataMarket, "thursday");
            }
        });
        vh1.getColFriday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeOpentime(dataNetwork, dataMarket, "friday");
            }
        });
        vh1.getColSaturday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeOpentime(dataNetwork, dataMarket, "saturday");
            }
        });
        vh1.getColSunday().getLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManageNetwork popup = new DialogManageNetwork(inflater.getContext());
                popup.Popup_ChangeOpentime(dataNetwork, dataMarket, "sunday");
            }
        });

        vh1.getColMonday().getTvTitle().setText("Monday");
        vh1.getColTuesday().getTvTitle().setText("Tuesday");
        vh1.getColWednesday().getTvTitle().setText("Wednesday");
        vh1.getColThursday().getTvTitle().setText("Thursday");
        vh1.getColFriday().getTvTitle().setText("Friday");
        vh1.getColSaturday().getTvTitle().setText("Saturday");
        vh1.getColSunday().getTvTitle().setText("Sunday");

        if (dataNetwork.getData() != null) {
            vh1.getColMonday().getTvValue().setText(dataNetwork.getData().getMonday());
            vh1.getColTuesday().getTvValue().setText(dataNetwork.getData().getTuesday());
            vh1.getColWednesday().getTvValue().setText(dataNetwork.getData().getWednesday());
            vh1.getColThursday().getTvValue().setText(dataNetwork.getData().getThursday());
            vh1.getColFriday().getTvValue().setText(dataNetwork.getData().getFriday());
            vh1.getColSaturday().getTvValue().setText(dataNetwork.getData().getSaturday());
            vh1.getColSunday().getTvValue().setText(dataNetwork.getData().getSunday());
        }


    }

    private void configureViewHolderEditPhoto(ViewHolderEditPhoto vh3, int position) {
        List<Object> tempObject = (List<Object>) items.get(position);
        final WrapFdbNetwork dataNetwork = (WrapFdbNetwork) tempObject.get(0);
        final List<WrapFdbImage> itemImage = (List<WrapFdbImage>) tempObject.get(1);

        vh3.getIvg().getTvImage().setText("Photo (" + itemImage.size() + ")");
        GridLayoutManager glm = new GridLayoutManager(inflater.getContext(), 3);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        vh3.getIvg().getRv().setLayoutManager(glm);
        RV_Adapter_Grid_Image_Fdb adapterList = new RV_Adapter_Grid_Image_Fdb(itemImage);
        vh3.getIvg().getRv().setAdapter(adapterList);

//        vh3.getIvg().getCv().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(inflater.getContext(), UpdatePhotoActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("itemShop", Parcels.wrap(dataShop));
//                bundle.putParcelable("itemImage", Parcels.wrap(itemImage));
//                intent.putExtras(bundle);
//                inflater.getContext().startActivity(intent);
//            }
//        });
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
