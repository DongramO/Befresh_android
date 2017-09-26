package com.befresh.befreshapp.Community.MyRecipe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by idongsu on 2017. 7. 2..
 */

public class MyRecipeContentViewHolder extends RecyclerView.ViewHolder {


    TextView comment_email;
    TextView comment_content;

    public MyRecipeContentViewHolder(View itemView)
    {
        super(itemView);
        comment_email = (TextView) itemView.findViewById(R.id.comment_email);
        comment_content = (TextView) itemView.findViewById(R.id.comment_content);
    }
}
