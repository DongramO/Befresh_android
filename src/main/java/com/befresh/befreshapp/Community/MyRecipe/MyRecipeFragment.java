package com.befresh.befreshapp.Community.MyRecipe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.R;

public class MyRecipeFragment extends MasterFragment {
    private NavigationActivity mContext;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyrecipeTabAdapter myrecipeTabAdapter;

    public MyRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_recipe, container, false);
        mContext = (NavigationActivity) getMasterActivity();

        tabLayout = (TabLayout)view.findViewById(R.id.recipe_tab);
        viewPager = (ViewPager)view.findViewById(R.id.recipe_viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("인기순"));
        tabLayout.addTab(tabLayout.newTab().setText("최신순"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        myrecipeTabAdapter = new MyrecipeTabAdapter(getFragmentManager(), tabLayout.getTabCount(), getActivity(),mContext);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(myrecipeTabAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_my_recipe);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.changeFragment(24);
            }
        });
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
