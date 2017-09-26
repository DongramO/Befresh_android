package com.befresh.befreshapp.Mypage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by 고혜민 on 2017-06-27.
 */

public class MypagePagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public MypagePagerAdapter(FragmentManager fm, int tabCount)
    {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position) {
            case 0:
                return new MypageLeftFragment();
            case 1:
                return new MypageRightFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
