package com.befresh.befreshapp.Community.RecommendStore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by JongBong on 2017-06-29.
 */

public class RecommendVH extends RecyclerView.ViewHolder {

    ImageView img;
    ImageView loc;
    TextView title;
    TextView content;

    public RecommendVH(View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.img_item_recommend);
        loc = (ImageView) itemView.findViewById(R.id.loc_item_recommend);
        title = (TextView) itemView.findViewById(R.id.title_item_recommend);
        content = (TextView) itemView.findViewById(R.id.content_item_recommend);
    }
}
