package com.befresh.befreshapp.Community.SaveList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by JongBong on 2017-07-01.
 */

public class SaveListPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public SaveListPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SLMyRecipeFragment();
            case 1:
                return new SLMagazineFragment();
            case 2:
                return new SLRecommendFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
