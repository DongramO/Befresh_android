package com.befresh.befreshapp.Community;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by JongBong on 2017-06-25.
 */

public class CommVH extends RecyclerView.ViewHolder {

    RecyclerView recyclerView;
    TextView name;
    ImageView showAll;

    public CommVH(View itemView) {
        super(itemView);
        recyclerView = (RecyclerView)itemView.findViewById(R.id.img_rcy_comm);
        name = (TextView) itemView.findViewById(R.id.text_item_recycler_community);
        showAll = (ImageView) itemView.findViewById(R.id.show_all_community);

    }

}
