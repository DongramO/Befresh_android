package com.befresh.befreshapp.Recipe.RecipeAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.befresh.befreshapp.R;

/**
 * Created by idongsu on 2017. 7. 3..
 */

public class SourceInfoViewHolder extends RecyclerView.ViewHolder
{
    public TextView material;
    public SourceInfoViewHolder(View itemView)
    {
        super(itemView);
        material = (TextView)itemView.findViewById(R.id.material);
    }
}
