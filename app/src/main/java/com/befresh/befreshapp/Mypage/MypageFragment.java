package com.befresh.befreshapp.Mypage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.R;

/**
 * Created by idongsu on 2017. 6. 28..
 */

public class MypageFragment extends MasterFragment
{
    ViewPager viewPager;
    TabLayout tabLayout;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mypage_fragment, container, false);

        viewPager = (ViewPager)view.findViewById(R.id.pager_my_page);
        tabLayout = (TabLayout)view.findViewById(R.id.tab_my_page);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.mypage_left_pager));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.mypage_right_pager));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        leftRecyclerView = (RecyclerView)view.findViewById(R.id.main_left_rcv1);
//        rightRecyclerView = (RecyclerView)view.findViewById(R.id.main_);

        final MypagePagerAdapter pagerAdapter = new MypagePagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != viewPager.getCurrentItem())
                {
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

     //   adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
