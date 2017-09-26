package com.befresh.befreshapp.Community.RecommendStore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by idongsu on 2017. 7. 5..
 */

public class RecommentListViewHolder extends RecyclerView.ViewHolder
{
    int id;
    ImageView image;
    ImageView location;
    TextView title;
    TextView content;
    CheckBox like;
    public RecommentListViewHolder(View itemView) {
        super(itemView);

        image = (ImageView)itemView.findViewById(R.id.img_item_recommend);
        location = (ImageView)itemView.findViewById(R.id.loc_item_recommend);
        title = (TextView)itemView.findViewById(R.id.title_item_recommend);
        content = (TextView)itemView.findViewById(R.id.content_item_recommend);
        like = (CheckBox)itemView.findViewById(R.id.like_item_recommend);

    }
}
