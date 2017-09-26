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

public class SLRecommendVH extends RecyclerView.ViewHolder {
    ImageView img;
    ImageView loc;
    TextView title;
    CheckBox like;
    TextView content;
    int id;

    public SLRecommendVH(View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.img_slrecommend);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        loc = (ImageView) itemView.findViewById(R.id.loc_slrecommend);
        title = (TextView) itemView.findViewById(R.id.name_slrecommend);
        like = (CheckBox) itemView.findViewById(R.id.like_slrecommend);
        content = (TextView)itemView.findViewById(R.id.content_slrecommend);

    }
}
