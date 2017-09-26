package com.befresh.befreshapp.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by 고혜민 on 2017-06-27.
 */

public class MypageRightViewHolder extends RecyclerView.ViewHolder {

    int id;
    int checked;
    ImageView item_img;
    TextView item_delivery;
    TextView item_title;
    ImageButton item_items;
    LinearLayout btnLayout;

    public MypageRightViewHolder(View itemView) {
        super(itemView);
        btnLayout = (LinearLayout) itemView.findViewById(R.id.btn_layout);
        item_img = (ImageView) itemView.findViewById(R.id.img_right_my_page);
        item_delivery = (TextView) itemView.findViewById(R.id.delivery_right_my_page);
        item_title = (TextView) itemView.findViewById(R.id.title_right_my_page);
        item_items = (ImageButton) itemView.findViewById(R.id.items_right_my_page);
    }
}