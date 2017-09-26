package com.befresh.befreshapp.Community.SaveList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.SaveListRecommendInfo;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLRecommendFragment extends MasterFragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    private NavigationActivity mContext;
    ArrayList<SaveListRecommendInfo.ResultData> items;
    SLRecommendAdapter adapter;
    CheckBox like;

    NetworkService networkService;

    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        networkService = ApplicationController.getInstance().getNetworkService();

        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);

        mContext = (NavigationActivity) getMasterActivity();
        View view = inflater.inflate(R.layout.fragment_slrecommend, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_slrecommend);
        manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);
        items = new ArrayList<>();
        login();
        return view;

    }

    public void login() {
        Call<SaveListRecommendInfo> saveListRecommendInfoCall = networkService.saveListRecommendInfoCall(token);
        saveListRecommendInfoCall.enqueue(new Callback<SaveListRecommendInfo>() {
            @Override
            public void onResponse(Call<SaveListRecommendInfo> call, Response<SaveListRecommendInfo> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "메인 네트워크");
                    SaveListRecommendInfo saveListRecommendInfo = response.body();
                    adapter = new SLRecommendAdapter(mContext, saveListRecommendInfo.data);
                    recyclerView.setAdapter(adapter);

                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<SaveListRecommendInfo> call, Throwable t) {

            }
        });
    }
}
