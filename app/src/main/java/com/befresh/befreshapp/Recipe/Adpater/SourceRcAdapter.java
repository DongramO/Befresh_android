package com.befresh.befreshapp.Recipe.Adpater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.R;
import com.befresh.befreshapp.Recipe.RecipeAdapter.SourceInfoViewHolder;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 3..
 */

public class SourceRcAdapter extends RecyclerView.Adapter<SourceInfoViewHolder>
{
    private ArrayList<String> material = new ArrayList<>();
    NavigationActivity mContext;
    public SourceRcAdapter(NavigationActivity mContext, ArrayList<String> material)
    {
        this.mContext = mContext;
        this.material = material;
    }
    @Override
    public SourceInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_detail_source_adapter, parent, false);
        SourceInfoViewHolder sourceInfoViewHolder = new SourceInfoViewHolder(view); // 뷰홀더를 통해 메모리 할당

        return sourceInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(SourceInfoViewHolder holder, int position)
    {
        holder.material.setText(material.get(position).toString());
    }

    @Override
    public int getItemCount()
    {
        return material.size();
    }
}
