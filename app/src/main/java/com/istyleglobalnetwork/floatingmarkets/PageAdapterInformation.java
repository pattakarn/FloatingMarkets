package com.istyleglobalnetwork.floatingmarkets;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sung on 30/11/2017 AD.
 */

public class PageAdapterInformation extends FragmentPagerAdapter {

    public PageAdapterInformation(FragmentManager fm) {
        super(fm);
    }

    public int getCount() {
        return 3;
    }

    public Fragment getItem(int position) {
        if(position == 0)
            return InformationFragment.newInstance("1", "3");
        else if(position == 1)
            return InformationFragment.newInstance("2", "3");
        else if(position == 2)
            return InformationFragment.newInstance("3", "3");
        return null;
    }

}
