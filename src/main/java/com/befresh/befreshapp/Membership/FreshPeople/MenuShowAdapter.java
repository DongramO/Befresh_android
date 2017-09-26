package com.befresh.befreshapp.Membership.FreshPeople;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befresh.befreshapp.Membership.MembershipModel.ScheduleResult;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.R;
import com.bumptech.glide.Glide;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.setid;

/**
 * Created by student on 2017-06-29.
 */

public class MenuShowAdapter extends RecyclerView.Adapter<deliveryViewHolder>
{

    NavigationActivity context;
    ScheduleResult.ResultData items;

    public MenuShowAdapter(NavigationActivity context, ScheduleResult.ResultData items)
    {
        this.context = context;
        this.items = items;
    }

    @Override
    public deliveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.fresh_people_delivery, parent, false);
        return new deliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final deliveryViewHolder holder, int position) {

        holder.id = items.recipe.get(position).id;
        Glide.with(context).load(items.recipe.get(position).image_url).into(holder.img);
        holder.deliveryDate.setText("배송예정일 "+items.recipe.get(position).delivery_date);
        holder.deliveryMenu.setText(items.recipe.get(position).title);
        holder.tag.setText(items.recipe.get(position).hashtag);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setid = holder.id;
                context.changeFragment(6);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.recipe.size();
    }
}
