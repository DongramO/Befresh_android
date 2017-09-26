package com.befresh.befreshapp.Community.SaveList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by JongBong on 2017-07-01.
 */

public class SLMyRecipeVH extends RecyclerView.ViewHolder {
    ImageView img;
    TextView title;
    CheckBox like;
    int id;

    public SLMyRecipeVH(View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.img_slmyrecipe);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        title = (TextView) itemView.findViewById(R.id.title_slmyrecipe);
        like = (CheckBox) itemView.findViewById(R.id.like_slmyrecipe);
    }
}
