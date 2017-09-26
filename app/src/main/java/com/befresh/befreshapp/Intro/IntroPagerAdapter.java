package com.befresh.befreshapp.Intro;


import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class IntroPagerAdapter extends FragmentStatePagerAdapter
{
    // Count number of tabs
    private int tabCount;
    public Activity activity;
    public IntroPagerAdapter(FragmentManager fm, int tabCount, Activity activity)
    {
        super(fm);
        this.tabCount = tabCount;
        this.activity = activity;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                IntroFragment rank1 = new IntroFragment();
                return rank1;
            case 1:
                IntroFragment2 rank2 = new IntroFragment2();
                return rank2;
            case 2:
                IntroFragment3 rank3 = new IntroFragment3();
                return rank3;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(Object object)
    {
        return super.getItemPosition(object);
    }


}
