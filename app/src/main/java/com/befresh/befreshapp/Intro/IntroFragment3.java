package com.befresh.befreshapp.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.befresh.befreshapp.Login.LoginPage;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.R;

/**
 * Created by idongsu on 2017. 6. 27..
 */

public class IntroFragment3 extends MasterFragment
{
    ImageButton start;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.intro_fragment3, container, false);
        start = (ImageButton)view.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginPage.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

    }

}
