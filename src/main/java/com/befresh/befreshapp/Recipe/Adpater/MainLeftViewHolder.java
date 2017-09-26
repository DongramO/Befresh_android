package com.befresh.befreshapp.Recipe.Adpater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;


/**
 * Created by idongsu on 2017. 5. 12..
 */

public class MainLeftViewHolder extends RecyclerView.ViewHolder
{
    ImageView img;
    TextView menu_name,menu_info,cooktime,material;
    CheckBox like;
    int id;
    TextView week;
    int length;
    public MainLeftViewHolder(View view)
    {
        super(view);
        img = (ImageView)view.findViewById(R.id.menu_image);
        menu_name = (TextView)view.findViewById(R.id.menu_name);
        menu_info = (TextView)view.findViewById(R.id.menu_info);
        cooktime = (TextView)view.findViewById(R.id.cooktime);
        material = (TextView)view.findViewById(R.id.material);
        like = (CheckBox) view.findViewById(R.id.like_main);
        week = (TextView)view.findViewById(R.id.week_title);
    }

}
