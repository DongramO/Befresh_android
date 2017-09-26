package com.befresh.befreshapp.Search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

import static com.befresh.befreshapp.R.id.material;

/**
 * Created by idongsu on 2017. 7. 6..
 */

public class SearchingViewHolder extends RecyclerView.ViewHolder
{
    TextView content;
    ImageView image;
    TextView title,time,hashtag,level;
    ImageView tag;


    public SearchingViewHolder(View itemView)
    {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.menu_image);
        title = (TextView) itemView.findViewById(R.id.menu_name);
        content = (TextView) itemView.findViewById(R.id.menu_info);
        time = (TextView) itemView.findViewById(R.id.cooktime);
        hashtag = (TextView) itemView.findViewById(material);
        level = (TextView) itemView.findViewById(R.id.level);
        tag = (ImageView)itemView.findViewById(R.id.tag);
    }
}
