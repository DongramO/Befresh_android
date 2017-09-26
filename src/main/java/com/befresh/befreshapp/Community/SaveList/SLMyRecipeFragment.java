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
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.SaveListMyRecipeInfo;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SLMyRecipeFragment extends MasterFragment {

    private NavigationActivity mContext;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    ArrayList<SaveListMyRecipeInfo.ResultData> items;
    SLMyRecipeAdapter adapter;

    NetworkService networkService;

    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = (NavigationActivity) getMasterActivity();
        View view = inflater.inflate(R.layout.fragment_slmy_recipe, container, false);

        networkService = ApplicationController.getInstance().getNetworkService();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_slmy_recipe);
        manager = new GridLayoutManager(mContext, 2);

        recyclerView.setLayoutManager(manager);

        items = new ArrayList<>();

        auto = getContext().getSharedPreferences("auto",Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token",null);

        //adapter = new SLMyRecipeAdapter(mContext, items);
        //recyclerView.setAdapter(adapter);
        login();
        return view;
    }


    public void login() {
        Call<SaveListMyRecipeInfo> saveListMyRecipeInfoCall = networkService.saveListRecipeInfoCall(token);
        saveListMyRecipeInfoCall.enqueue(new Callback<SaveListMyRecipeInfo>() {
            @Override
            public void onResponse(Call<SaveListMyRecipeInfo> call, Response<SaveListMyRecipeInfo> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if(response.isSuccessful()) {
                    Log.i("MyTag", "메인 네트워크");
                    SaveListMyRecipeInfo saveListMyRecipeInfo = response.body();
                    if (saveListMyRecipeInfo.data != null) {
                        items.addAll(saveListMyRecipeInfo.data);
                        adapter = new SLMyRecipeAdapter(mContext, items);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<SaveListMyRecipeInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }
}
