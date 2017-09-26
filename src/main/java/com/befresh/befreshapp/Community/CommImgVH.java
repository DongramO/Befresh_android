package com.befresh.befreshapp.Community;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by JongBong on 2017-06-25.
 */

public class CommImgVH extends RecyclerView.ViewHolder {

    ImageView img;
    TextView content;
    public CommImgVH(View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img_item_rcy_comm);
        content = (TextView) itemView.findViewById(R.id.content);

    }
}
