package com.befresh.befreshapp.Mypage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Mypage.MypageModel.getMyPageResult;
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
 * Created by 고혜민 on 2017-06-27.
 */

public class MypageLeftAdapter extends RecyclerView.Adapter<MypageLeftViewHolder> {

    NavigationActivity mContext;
    ArrayList<getMyPageResult.ResultData.SavedRecipe> items = new ArrayList<>();
    NetworkService networkService;

    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;

    public MypageLeftAdapter(NavigationActivity mContext, ArrayList<getMyPageResult.ResultData.SavedRecipe> items) {
        this.mContext = mContext;
        this.items.addAll(items);

        networkService = ApplicationController.getInstance().getNetworkService();

        auto = mContext.getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);
    }

    @Override
    public MypageLeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_rcy_item_left_mypage, parent, false);
        MypageLeftViewHolder mypageLeftViewHolder = new MypageLeftViewHolder(view); // 뷰홀더를 통해 메모리 할당
        return mypageLeftViewHolder;
    }

    public void onUpdateViewHolder(ArrayList<getMyPageResult.ResultData.SavedRecipe> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MypageLeftViewHolder holder, int position) {
        Glide.with(mContext).load(items.get(position).image_url).into(holder.item_img);
        holder.id = items.get(position).id;
        holder.item_description.setText(items.get(position).subtitle);
        holder.item_title.setText(items.get(position).title);
        holder.item_like.setChecked(items.get(position).checkSaveList);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setid = holder.id;
                mContext.changeFragment(6);
            }
        });
        holder.item_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(holder.id);
                ArrayList<getMyPageResult.ResultData.SavedRecipe> newItems = new ArrayList<getMyPageResult.ResultData.SavedRecipe>();
                newItems.addAll(items);
                newItems.remove(holder.getAdapterPosition());
                onUpdateViewHolder(newItems);
                Toast.makeText(mContext, holder.item_like.isChecked() ? "저장 되었습니다." : "삭제 되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void check(int id) {
        Call<setMyPageRegist> setMyPageRegistCall = networkService.setMyPageRegistCall(token, id, 1);
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
