package com.befresh.befreshapp.Mypage;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.befresh.befreshapp.Mypage.MypageModel.getMyDeliveriedResult;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.R;
import com.befresh.befreshapp.Recipe.ReviewWriteFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.setid;

/**
 * Created by 고혜민 on 2017-06-27.
 */

public class MypageRightAdapter extends RecyclerView.Adapter<MypageRightViewHolder> {
    NavigationActivity context;
    ArrayList<getMyDeliveriedResult.DeliveriedRecipe> items = new ArrayList<>();
    static boolean isUserSelected = false;


    public MypageRightAdapter(NavigationActivity context, ArrayList<getMyDeliveriedResult.DeliveriedRecipe> items) {
        this.context = context;
        this.items.addAll(items);
    }

    @Override
    public MypageRightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_rcy_item_right_mypage, parent, false);
        MypageRightViewHolder mypageRightViewHolder = new MypageRightViewHolder(view); // 뷰홀더를 통해 메모리 할당
        return mypageRightViewHolder;
    }

    @Override
    public void onBindViewHolder(final MypageRightViewHolder holder, final int position)
    {
        holder.item_delivery.setText(items.get(position).deliveried_date);
        Glide.with(context).load(items.get(position).image_url).into(holder.item_img);
        holder.item_title.setText(items.get(position).title);
        holder.id = items.get(position).id;
        holder.checked = items.get(position).check_review;

        if(holder.checked == 1)
        {
            holder.item_items.setImageResource(R.drawable.mycookingbox_completebutton);
        }
        else
        {
            holder.item_items.setImageResource(R.drawable.mycookingbox_writereviewbutton);
            holder.item_items.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setid = position;
                    ReviewWriteFragment fragment = new ReviewWriteFragment(); // Fragment 생성
                    Bundle bundle = new Bundle(3); // 파라미터는 전달할 데이터 개수
                    bundle.putString("image", items.get(position).image_url); // key , value
                    bundle.putString("title",items.get(position).title);
                    bundle.putInt("id",items.get(position).id);
                    fragment.setArguments(bundle);
                    context.ReplaceFragement(fragment);

                }
            });
        }

        holder.btnLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                setid = holder.id;
                context.changeFragment(6);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
