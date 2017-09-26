package com.befresh.befreshapp.Magazine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by student on 2017-06-29.
 */

public class MagazineContentsFirstVH extends RecyclerView.ViewHolder {

    ImageView img;
    TextView mainTitle;
    TextView subTitle;
    TextView contents;

    public MagazineContentsFirstVH(View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.img_first_magazine_contents);
        mainTitle = (TextView) itemView.findViewById(R.id.mainTitle_first_magazine_contents);
        subTitle = (TextView) itemView.findViewById(R.id.subTitle_first_magazine_contents);
        contents = (TextView) itemView.findViewById(R.id.textView4);
    }
}
