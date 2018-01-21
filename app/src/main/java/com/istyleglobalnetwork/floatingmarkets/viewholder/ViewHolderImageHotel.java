package com.istyleglobalnetwork.floatingmarkets.viewholder;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.istyleglobalnetwork.floatingmarkets.R;

/**
 * Created by Sung on 12/12/2017 AD.
 */

public class ViewHolderImageHotel extends RecyclerView.ViewHolder {

    //    private ImageView ivHotel;
    private TextView tvName;
    private RatingBar ratingBar;
    private FrameLayout fl;

    FragmentManager fm;


    public ViewHolderImageHotel(View itemView) {
        super(itemView);
//        ivHotel = (ImageView) itemView.findViewById(R.id.iv_hotel);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

        int[] image = { R.drawable.hotel1, R.drawable.hotel2, R.drawable.hotel3, R.drawable.hotel4, R.drawable.hotel5};

//        PagerAdapterImage adapter = new PagerAdapterImage(((AppCompatActivity) itemView.getContext()).getSupportFragmentManager(), image);
//        ViewPager pager = (ViewPager) itemView.findViewById(R.id.pager);
//        pager.setAdapter(adapter);

//        ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager().beginTransaction().add(R.id.container, ImageFragment.newInstance()).commit();

    }

//    public ImageView getIvHotel() {
//        return ivHotel;
//    }
//
//    public void setIvHotel(ImageView ivHotel) {
//        this.ivHotel = ivHotel;
//    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }
}
