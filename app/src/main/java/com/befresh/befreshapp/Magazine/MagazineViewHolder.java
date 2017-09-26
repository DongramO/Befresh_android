package com.befresh.befreshapp.Magazine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by JongBong on 2017-06-25.
 */

public class MagazineViewHolder extends RecyclerView.ViewHolder {
    int id;
    ImageView img;
    TextView title;
    CheckBox like;
    public MagazineViewHolder(View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.img_item_magazine);
        title = (TextView) itemView.findViewById(R.id.title_item_magazine);
        like = (CheckBox) itemView.findViewById(R.id.like_item_magazine);
    }
}
