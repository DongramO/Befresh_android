package com.befresh.befreshapp.Magazine;//package com.befresh.befreshapp.Magazine;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.befresh.befreshapp.Community.CommunityModel.getMagazineDetail;
//import com.befresh.befreshapp.R;
//
//import java.util.ArrayList;
//
///**
// * Created by student on 2017-06-29.
// */
//
//public class MagazineContentsAdapter extends RecyclerView.Adapter<MagazineContentsVH>
//{
//
//    Context context;
//    ArrayList<getMagazineDetail.ResultData> items = new ArrayList<>();
//
//    public MagazineContentsAdapter(Context context, ArrayList<getMagazineDetail.ResultData> items) {
//        this.context = context;
//        this.items = items;
//    }
//
//    @Override
//    public MagazineContentsVH onCreateViewHolder(ViewGroup parent, int viewType)
//    {
//
//        View view = LayoutInflater.from(context).inflate(R.layout.view_first_item_magazine_contents, parent, false);
//        return new MagazineContentsVH(view);
//    }
//
//    @Override
//    public void onBindViewHolder(MagazineContentsVH holder, int position)
//    {
//
//        if(position == 0 )
//        {
//            holder.title.setText(items.get(position).title);
//        }
//        else
//        {
//            holder.title.setVisibility(View.GONE);
//        }
//        holder.img.setImageResource(R.drawable.cook3);
//        holder.title.setText(items.get(position).title)aa;
//        holder.contents.setText(items.get(position).content);
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//}
