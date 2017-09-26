package com.befresh.befreshapp.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.befresh.befreshapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by JongBong on 2017-06-25.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchingViewHolder> {

    Context context;
    ArrayList<SearchResult.searching> searchings = new ArrayList<>();
    View.OnClickListener clickListener1;
    public SearchRecyclerAdapter(Context context,  ArrayList<SearchResult.searching> searchings, View.OnClickListener clickListener)
    {
        this.context = context;
        this.searchings = searchings;
        this.clickListener1 = clickListener;
    }

    @Override

    public SearchingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        {
            View view = LayoutInflater.from(context).inflate(R.layout.view_rcy_item_sch, parent, false);
            view.setOnClickListener(clickListener1);
            return new SearchingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(SearchingViewHolder holder, int position)
    {
        if(searchings.size() > 0)
        {
            holder.title.setText(searchings.get(position).title);
            holder.content.setText(searchings.get(position).subtitle);
            holder.time.setText(searchings.get(position).cookingTime + "분");
            holder.hashtag.setText(searchings.get(position).hashtag);
            Glide.with(context).load(searchings.get(position).image_url).into(holder.image);
        }
        else
        {

        }
    }

    @Override
    public int getItemCount() {
        return searchings.size();
    }

}
