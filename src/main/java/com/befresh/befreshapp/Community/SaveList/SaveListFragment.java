package com.befresh.befreshapp.Community.SaveList;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.R;

public class SaveListFragment extends MasterFragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    private NavigationActivity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = (NavigationActivity) getMasterActivity();

        View view = inflater.inflate(R.layout.fragment_save_list, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_save_list);
        viewPager = (ViewPager) view.findViewById(R.id.pager_save_list);

        tabLayout.addTab(tabLayout.newTab().setText("나의 레시피"));
        tabLayout.addTab(tabLayout.newTab().setText("매거진"));
        tabLayout.addTab(tabLayout.newTab().setText("식당 추천"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final SaveListPagerAdapter saveListPagerAdapter = new SaveListPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(saveListPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != viewPager.getCurrentItem()) {
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() != viewPager.getCurrentItem()) {
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }
        });
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);

        return view;
    }

}


