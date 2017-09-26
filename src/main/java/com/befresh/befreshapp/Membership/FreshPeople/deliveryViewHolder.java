package com.befresh.befreshapp.Membership.FreshPeople;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by student on 2017-06-29.
 */

public class deliveryViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView deliveryDate;
    TextView deliveryMenu;
    TextView tag;
    int id;

    public deliveryViewHolder(View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.image);
        deliveryDate = (TextView)itemView.findViewById(R.id.date);
        deliveryMenu = (TextView)itemView.findViewById(R.id.title);
        tag = (TextView)itemView.findViewById(R.id.hashtag);
    }
}
