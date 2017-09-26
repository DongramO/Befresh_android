package com.befresh.befreshapp.Intro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.R;

/**
 * Created by idongsu on 2017. 6. 27..
 */

public class IntroFragment2 extends MasterFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.intro_fragment2, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

    }

}
