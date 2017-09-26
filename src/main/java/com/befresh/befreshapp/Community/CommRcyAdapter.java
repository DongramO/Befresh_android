package com.befresh.befreshapp.Community;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.befresh.befreshapp.Community.CommunityModel.getCommunityMain;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.R;

import java.util.ArrayList;

/**
 * Created by JongBong on 2017-06-24.
 */

public class CommRcyAdapter extends RecyclerView.Adapter<CommVH> {

    NavigationActivity context;
    ArrayList<CommRcyItem> items = new ArrayList<>();
    TextView show_all_community;
    getCommunityMain.ResultData data;
    public CommRcyAdapter(NavigationActivity context, ArrayList<CommRcyItem> items, getCommunityMain.ResultData data)
    {
        this.context = context;
        this.data = data;
        this.items = items;
    }

    public void onUpdateViewHolder(ArrayList<CommRcyItem> newItems) {
        items.clear();
        items.addAll(newItems);
    }

    @Override
    public CommVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rcy_item_comm, parent, false);
        return new CommVH(view);
    }

    @Override
    public void onBindViewHolder(final CommVH holder, final int position)
    {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        CommImgAdapter adapter = new CommImgAdapter(context, data, position);

        holder.recyclerView.setLayoutManager(manager);
        holder.recyclerView.setAdapter(adapter);

        switch(position)
        {
            case 0: holder.name.setText("나의 레시피");break;
            case 1: holder.name.setText("식당 추천");break;
            case 2: holder.name.setText("비프레시 메거진");break;
            case 3: holder.name.setText("나의 저장 목록");break;
        }
        holder.showAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(position) {
                    case 0: context.changeFragment(20);break;
                    case 1: context.changeFragment(21);break;
                    case 2: context.changeFragment(22);break;
                    case 3: context.changeFragment(23);break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }


}
