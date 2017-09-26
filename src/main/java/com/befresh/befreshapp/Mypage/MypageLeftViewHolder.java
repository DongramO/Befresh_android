package com.befresh.befreshapp.Mypage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by 고혜민 on 2017-06-27.
 */

public class MypageLeftViewHolder extends RecyclerView.ViewHolder
{
    int id;
    ImageView item_img;
    CheckBox item_like;
    TextView item_title;
    TextView item_description;

    public MypageLeftViewHolder(View itemView){
        super(itemView);

        item_img = (ImageView)itemView.findViewById(R.id.img_left_my_page);
        item_like = (CheckBox)itemView.findViewById(R.id.btn_like_my_page);
        item_title = (TextView)itemView.findViewById(R.id.title_left_my_page);
        item_description = (TextView)itemView.findViewById(R.id.desc_left_my_page);
    }
}
