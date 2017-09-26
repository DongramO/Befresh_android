package com.befresh.befreshapp.Mypage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Mypage.MypageModel.getMyPageResult;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by 고혜민 on 2017-06-27.
 */

public class MypageLeftFragment extends MasterFragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    MypageLeftAdapter adapter;
    NavigationActivity mContext;
    NetworkService networkService;
    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_left_mypage, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_left_my_page);
        mContext = (NavigationActivity) getMasterActivity();

        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);
        Log.i("mytoken", token);

        manager = new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL, 1);
        recyclerView.setLayoutManager(manager);

        login();
        return view;
    }


    public void login() {
        Call<getMyPageResult> communityMain = networkService.getMyPageResult(token);
        communityMain.enqueue(new Callback<getMyPageResult>() {
            @Override
            public void onResponse(Call<getMyPageResult> call, Response<getMyPageResult> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "메인 네트워크");
                    getMyPageResult getMyPageResult = response.body();

                    adapter = new MypageLeftAdapter(mContext, getMyPageResult.data.savedRecipe);
                    recyclerView.setAdapter(adapter);

                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getMyPageResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }
}
