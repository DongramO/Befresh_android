package com.befresh.befreshapp.Community.SaveList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.SaveListRecommendInfo;
import com.befresh.befreshapp.Mypage.MypageModel.setMyPageRegist;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.setid;

/**
 * Created by JongBong on 2017-07-01.
 */

public class SLRecommendAdapter extends RecyclerView.Adapter<SLRecommendVH> {


    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;

    NetworkService networkService;

    NavigationActivity mContext;
    ArrayList<SaveListRecommendInfo.ResultData> items = new ArrayList<>();

    public SLRecommendAdapter(NavigationActivity mContext, ArrayList<SaveListRecommendInfo.ResultData> items) {
        this.mContext = mContext;
        this.items.addAll(items);
        networkService = ApplicationController.getInstance().getNetworkService();

        auto = mContext.getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);
    }

    public void onUpdateViewHolder(ArrayList<SaveListRecommendInfo.ResultData> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public SLRecommendVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_recommend_save_list, parent, false);
        return new SLRecommendVH(view);
    }

    @Override
    public void onBindViewHolder(final SLRecommendVH holder, final int position)
    {
        holder.id = items.get(position).id;
        Glide.with(mContext).load(items.get(position).imageUrl).into(holder.img);
        holder.title.setText(items.get(position).title);
        holder.like.setChecked(items.get(position).checkSaveList);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(holder.id);
                ArrayList<SaveListRecommendInfo.ResultData> newItems = new ArrayList<SaveListRecommendInfo.ResultData>();
                newItems.addAll(items);
                newItems.remove(holder.getAdapterPosition());
                onUpdateViewHolder(newItems);
                Toast.makeText(mContext, holder.like.isChecked() ? "저장 되었습니다." : "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                //mContext.changeFragment(23);
            }
        });
        Glide.with(mContext).load(items.get(position).location).into(holder.loc);
        holder.content.setText(items.get(position).content);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setid = holder.id;
                mContext.changeFragment(26);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void check(int id) {
        Call<setMyPageRegist> setMyPageRegistCall = networkService.setMyPageRegistCall(token, id, 3);
        setMyPageRegistCall.enqueue(new Callback<setMyPageRegist>() {
            @Override
            public void onResponse(Call<setMyPageRegist> call, Response<setMyPageRegist> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "메인 네트워크");
                    setMyPageRegist setMyPageRegist = response.body();
                    Log.i("MyTag", setMyPageRegist.msg);

                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<setMyPageRegist> call, Throwable t) {

            }
        });
    }

    /*public void check(int id) {
        Call<SaveListRecommendInfo> saveListRecommendInfoCall = networkService.saveListRecommendInfoCall(token);
        saveListRecommendInfoCall.enqueue(new Callback<SaveListRecommendInfo>() {
            @Override
            public void onResponse(Call<SaveListRecommendInfo> call, Response<SaveListRecommendInfo> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "메인 네트워크");
                    SaveListRecommendInfo saveListRecommendInfo = response.body();
                    Log.i("MyTag", saveListRecommendInfo.msg);
                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<SaveListRecommendInfo> call, Throwable t) {

            }
        });
    }*/
}
