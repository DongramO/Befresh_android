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

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Mypage.MypageModel.getMyDeliveriedResult;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 고혜민 on 2017-06-27.
 */

public class MypageRightFragment extends MasterFragment {

    NetworkService networkService;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    MypageRightAdapter adapter;
    NavigationActivity mContext;

    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_right_mypage, container, false);

        networkService = ApplicationController.getInstance().getNetworkService();

        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_right_my_page);
        mContext = (NavigationActivity) getMasterActivity();
        manager = new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL, 1);
        recyclerView.setLayoutManager(manager);

        login();
        return view;
    }

    public void login() {
        Call<getMyDeliveriedResult> getMyDeliveriedResultCall = networkService.getMyDeliveriedResultCall(token);

        getMyDeliveriedResultCall.enqueue(new Callback<getMyDeliveriedResult>() {
            @Override
            public void onResponse(Call<getMyDeliveriedResult> call, Response<getMyDeliveriedResult> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "쿠킹박스");
                    getMyDeliveriedResult getMyDeliveriedResult = response.body();
                    adapter = new MypageRightAdapter(mContext, getMyDeliveriedResult.data.deliveriedRecipe);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<getMyDeliveriedResult> call, Throwable t) {

            }
        });
    }
}
