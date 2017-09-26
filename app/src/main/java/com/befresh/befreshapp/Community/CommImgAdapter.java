package com.befresh.befreshapp.Community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befresh.befreshapp.Community.CommunityModel.getCommunityMain;
import com.befresh.befreshapp.R;
import com.bumptech.glide.Glide;


public class CommImgAdapter extends RecyclerView.Adapter<CommImgVH>
{

    Context context;
    int position2;
    getCommunityMain.ResultData data;
    View view;
    int length;
    public CommImgAdapter(Context context, getCommunityMain.ResultData data, int position) {
        this.context = context;
        this.position2 = position;
        this.data = data;
    }

    @Override
    public CommImgVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        length = data.SaveList.size();
        if(position2 == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.view_img_item_maga, parent, false);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.view_img_item_comm, parent, false);
        }

        return new CommImgVH(view);
    }

    @Override
    public void onBindViewHolder(CommImgVH holder, int position)
    {
        if (position2 != 2)
        {
            holder.content.setVisibility(View.GONE);
        }
        switch (position2)
        {
            case 0:
                if(data.RecipePhoto.size() > 0)
                {
                    Glide.with(context)
                            .load(data.RecipePhoto.get(position).imageUrl)
                            .into(holder.img);
                }
                break;
            case 1:
                if(data.Restaurant.size() >0)
                {
                    Glide.with(context)
                            .load(data.Restaurant.get(position).imageUrl)
                            .into(holder.img);
                }
                break;
            case 2:
                if(data.Magazine.size() > 0) {
                    Glide.with(context)
                            .load(data.Magazine.get(position).imageUrl)
                            .into(holder.img);
                    holder.content.setText(data.Magazine.get(position).title);
                }
                break;
            case 3:
                Log.i("나의 저장 리스트 ",data.SaveList.size()+"");
                if (data.SaveList.size() > 0)
                {
                    Glide.with(context)
                            .load(data.SaveList.get(position).imageUrl)
                            .into(holder.img);
                }
                break;
        }
    }

    
    @Override
    public int getItemCount()
    {

        switch (position2) {
            case 0:
                return data.RecipePhoto.size() < 6 ? data.RecipePhoto.size() : 6;
            case 1:
                return data.Restaurant.size() < 6 ? data.Restaurant.size() : 6;
            case 2:
                return data.Magazine.size() < 6 ? data.Magazine.size() : 6;
            case 3:
                return data.SaveList.size() < 6 ? data.SaveList.size() : 6;
            default:
                return 6;
        }
    }
}
