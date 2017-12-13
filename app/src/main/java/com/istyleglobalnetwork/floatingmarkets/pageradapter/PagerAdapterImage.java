package com.istyleglobalnetwork.floatingmarkets.pageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.istyleglobalnetwork.floatingmarkets.fragment.ImageFragment;

/**
 * Created by Sung on 30/11/2017 AD.
 */

public class PagerAdapterImage extends FragmentPagerAdapter {

    int[] image;

    public PagerAdapterImage(FragmentManager fm, int[] image) {
        super(fm);
        this.image = image;
    }

    public int getCount() {
        return image.length;
    }

    public Fragment getItem(int position) {
        return ImageFragment.newInstance(image[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (position+1) + "/" + getCount();
    }

}
