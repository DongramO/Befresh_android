package com.befresh.befreshapp.Intro;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.befresh.befreshapp.R;

public class IntroActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    IntroPagerAdapter introPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = (ViewPager)findViewById(R.id.pager_intro);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);


        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        introPagerAdapter = new IntroPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),this);
        viewPager.setAdapter(introPagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
}
