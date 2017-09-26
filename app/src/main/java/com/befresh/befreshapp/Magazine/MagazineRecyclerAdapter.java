package com.befresh.befreshapp.Magazine;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.getMagazineMain;
import com.befresh.befreshapp.Mypage.MypageModel.setMyPageRegist;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JongBong on 2017-06-25.
 */

public class MagazineRecyclerAdapter extends RecyclerView.Adapter<MagazineViewHolder> {
    Context context;
    ArrayList<getMagazineMain.ResultData> items = new ArrayList<>();
    View.OnClickListener clickListener;

    private SharedPreferences auto;
    private SharedPreferences.Editor autoLogin;
    String token;


    NetworkService networkService;

    public MagazineRecyclerAdapter(Context context, ArrayList<getMagazineMain.ResultData> items, View.OnClickListener clickListener) {
        this.context = context;
        this.items = items;
        this.clickListener = clickListener;

        networkService = ApplicationController.getInstance().getNetworkService();

        auto = context.getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);
    }

    public void onUpdateViewHolder(ArrayList<getMagazineMain.ResultData> items) {
        items.clear();
        items.addAll(items);
    }

    @Override
    public MagazineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rcy_item_maga, parent, false);
        MagazineViewHolder magazineViewHolder = new MagazineViewHolder(view);
        view.setOnClickListener(clickListener);
        return magazineViewHolder;
    }

    @Override
    public void onBindViewHolder(final MagazineViewHolder holder, int position)
    {
        holder.id = items.get(position).id;
        Glide.with(context)
                .load(items.get(position).imageUrl)
                .into(holder.img);
        holder.img.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.title.setText(items.get(position).title);
        holder.like.setChecked(items.get(position).checkSaveList);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(holder.id);
                Toast.makeText(context, holder.like.isChecked() ? "저장 되었습니다." : "삭제 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void check(int id)
    {
        Call<setMyPageRegist> setMyPageRegistCall = networkService.setMyPageRegistCall(token, id, 4);
        setMyPageRegistCall.enqueue(new Callback<setMyPageRegist>() {
            @Override
            public void onResponse(Call<setMyPageRegist> call, Response<setMyPageRegist> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful())
                {
                    setMyPageRegist setMyPageRegist = response.body();
                }
                else
                {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<setMyPageRegist> call, Throwable t) {

            }
        });
    }

}
