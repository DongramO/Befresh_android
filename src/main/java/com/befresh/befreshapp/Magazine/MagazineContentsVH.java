package com.befresh.befreshapp.Magazine;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by student on 2017-06-29.
 */

public class MagazineContentsVH extends RecyclerView.ViewHolder{
    ImageView img;
    TextView title;
    TextView title2;
    TextView contents;

    public MagazineContentsVH(View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.img_first_magazine_contents);
        title = (TextView)itemView.findViewById(R.id.mainTitle_first_magazine_contents);
        title2 = (TextView)itemView.findViewById(R.id.title2);
        contents = (TextView)itemView.findViewById(R.id.subTitle_first_magazine_contents);
    }
}
