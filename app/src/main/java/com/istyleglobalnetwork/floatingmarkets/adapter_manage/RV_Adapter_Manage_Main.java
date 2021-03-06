package com.istyleglobalnetwork.floatingmarkets.adapter_manage;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istyleglobalnetwork.floatingmarkets.R;
import com.istyleglobalnetwork.floatingmarkets.data.DataMainMenu;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderManageMain;
import com.istyleglobalnetwork.floatingmarkets.viewholder.ViewHolderManageMainHeader;

import java.util.List;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class RV_Adapter_Manage_Main extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    LayoutInflater inflater;

    private final int TITLE = 0, DATA = 1;

    public RV_Adapter_Manage_Main(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        this.inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TITLE :
                View v = inflater.inflate(R.layout.card_manage_main_header, parent, false);
                viewHolder = new ViewHolderManageMainHeader(v);
                break;
            case DATA :
                View v2 = inflater.inflate(R.layout.card_manage_main, parent, false);
                viewHolder = new ViewHolderManageMain(v2);
                break;
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case TITLE :
                ViewHolderManageMainHeader vh = (ViewHolderManageMainHeader) holder;
                configureViewHolderHeader(vh, position);
                break;
            case DATA :
                ViewHolderManageMain vh2 = (ViewHolderManageMain) holder;
                configureViewHolderMainMenu(vh2, position);
                break;
        }

    }

    private void configureViewHolderMainMenu(ViewHolderManageMain vh1, final int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
//        vh1.getIv().setImageResource((Integer) items.get(position));
        final DataMainMenu data = (DataMainMenu) items.get(position);
        vh1.getTv().setText(data.getNameItem());
        if (data.getcItem() != null) {
            vh1.getCv().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(inflater.getContext(), data.getcItem());
                    inflater.getContext().startActivity(intent);
//        }
                }
            });
        }

    }

    private void configureViewHolderHeader(ViewHolderManageMainHeader vh1, final int position) {
//        User user = (User) items.get(position);
//        if (user != null) {
//        vh1.getIv().setImageResource((Integer) items.get(position));
//        final DataMainMenu data = (DataMainMenu) items.get(position);
        final String data = (String) items.get(position);
        vh1.getTv().setText(data);
//        if (data.getcItem() != null) {
//            vh1.getCv().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(inflater.getContext(), data.getcItem());
//                    inflater.getContext().startActivity(intent);
////        }
//                }
//            });
//        }

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof String) {
            return TITLE;
        } else if (items.get(position) instanceof DataMainMenu) {
            return DATA;
        }
        return position;
    }


}
