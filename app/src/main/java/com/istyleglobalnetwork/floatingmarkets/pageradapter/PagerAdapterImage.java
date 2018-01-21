package com.istyleglobalnetwork.floatingmarkets.pageradapter;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.istyleglobalnetwork.floatingmarkets.fragment.ImageFragment;

import java.util.ArrayList;

/**
 * Created by Sung on 30/11/2017 AD.
 */

public class PagerAdapterImage extends FragmentPagerAdapter {

    ArrayList<Uri> image = new ArrayList<Uri>();

    public PagerAdapterImage(FragmentManager fm, ArrayList<Uri> image) {
        super(fm);
        this.image = image;
    }

    public int getCount() {
        return image.size();
    }

    public Fragment getItem(int position) {
        return ImageFragment.newInstance(image.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (position+1) + "/" + getCount();
    }

}
