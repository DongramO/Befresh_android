package com.befresh.befreshapp.Community.RecommendStore;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.getRestaurantResult;
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
 * Created by JongBong on 2017-06-29.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommentListViewHolder> {

    NetworkService networkService;
    NavigationActivity context;
    ArrayList<getRestaurantResult.ResultDataRe> items = new ArrayList<>();

    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;

    public RecommendAdapter(NavigationActivity context, ArrayList<getRestaurantResult.ResultDataRe> items) {
        this.context = context;
        this.items = items;
        networkService = ApplicationController.getInstance().getNetworkService();


        auto = context.getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);
    }

    @Override
    public RecommentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_item_recommend, parent, false);
        return new RecommentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecommentListViewHolder holder, int position) {

        holder.id = items.get(position).id;
        Glide.with(context)
                .load(items.get(position).imageUrl)
                .into(holder.image);

        holder.title.setText(items.get(position).title);
        holder.content.setText(items.get(position).content);
        Glide.with(context).load(items.get(position).location).into(holder.location);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.changeFragment(26);
                setid = holder.id;
            }
        });
        holder.like.setChecked(items.get(position).checkSaveList);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(holder.id);
                Toast.makeText(context, holder.like.isChecked()?"저장되었습니다.":"삭제되었습니다.", Toast.LENGTH_SHORT).show();
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

}
