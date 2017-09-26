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

public class SLMagazineVH extends RecyclerView.ViewHolder {

    int id;
    ImageView img;
    TextView title;
    CheckBox like;

    public SLMagazineVH(View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img_slmagazine);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        title = (TextView) itemView.findViewById(R.id.title_slmagazine);
        like = (CheckBox) itemView.findViewById(R.id.like_slmagazine);
    }
}
