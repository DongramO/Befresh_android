package com.befresh.befreshapp.Community.MyRecipe;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.befresh.befreshapp.Navigationmain.NavigationActivity;

/**
 * Created by idongsu on 2017. 6. 30..
 */

public class MyrecipeTabAdapter extends FragmentStatePagerAdapter
{
    // Count number of tabs
    private int tabCount;
    public Activity activity;
    NavigationActivity context;

    public MyrecipeTabAdapter(FragmentManager fm, int tabCount, Activity activity, NavigationActivity context)
    {
        super(fm);
        this.tabCount = tabCount;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position)
    {

        // Returning the current tabs
        switch (position)
        {
            case 0:
//                context.changeFragment(70);
                MyrecipeLeftFragment rank1 = new MyrecipeLeftFragment();
                return rank1;
            case 1:
                MyrecipeRightFragment rank2 = new MyrecipeRightFragment();
                return rank2;

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
