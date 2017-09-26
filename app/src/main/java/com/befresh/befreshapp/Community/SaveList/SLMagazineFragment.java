package com.befresh.befreshapp.Community.SaveList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.SaveListMagazineInfo;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLMagazineFragment extends MasterFragment {

    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    private NavigationActivity mContext;
    ArrayList<SLMagazineItem> items;
    SLMagazineAdapter adapter;


    NetworkService networkService;
    CheckBox like;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);
        networkService = ApplicationController.getInstance().getNetworkService();

        mContext = (NavigationActivity) getMasterActivity();
        View view = inflater.inflate(R.layout.fragment_slmagazine, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_slmagazine);
        manager = new GridLayoutManager(mContext, 2);

        recyclerView.setLayoutManager(manager);

        items = new ArrayList<>();
        login();
        return view;
    }

    public void login() {
        Call<SaveListMagazineInfo> saveListMagazineInfoCall = networkService.saveListMagazineInfoCall(token);
        saveListMagazineInfoCall.enqueue(new Callback<SaveListMagazineInfo>() {
            @Override
            public void onResponse(Call<SaveListMagazineInfo> call, Response<SaveListMagazineInfo> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "메인 네트워크");
                    SaveListMagazineInfo saveListMagazineInfo = response.body();
                    adapter = new SLMagazineAdapter(mContext, saveListMagazineInfo.data);
                    recyclerView.setAdapter(adapter);

                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }


            @Override
            public void onFailure(Call<SaveListMagazineInfo> call, Throwable t) {

            }
        });
    }
}
