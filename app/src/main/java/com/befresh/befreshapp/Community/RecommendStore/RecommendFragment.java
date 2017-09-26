package com.befresh.befreshapp.Community.RecommendStore;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.getRestaurantResult;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class RecommendFragment extends MasterFragment {

    private NavigationActivity mContext;
    NetworkService networkService;
    RecyclerView recyclerView;
    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        mContext = (NavigationActivity) getMasterActivity();
        networkService = ApplicationController.getInstance().getNetworkService();

        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token",null);
        Log.i("mytoken",token);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_recommend);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);
        login();

        return view;
    }


    public void login()
    {
        Call<getRestaurantResult> communityMain = networkService.getRestaurantResult(token);
        communityMain.enqueue(new Callback<getRestaurantResult>()
        {
            @Override
            public void onResponse(Call<getRestaurantResult> call, Response<getRestaurantResult> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful())
                {
                    Log.i("MyTag", "추천");
                    getRestaurantResult getCommunityMain = response.body();

                    ArrayList<getRestaurantResult.ResultDataRe> items = new ArrayList<>();
                    items = getCommunityMain.data;
                    RecommendAdapter adapter = new RecommendAdapter(mContext, items);
                    recyclerView.setAdapter(adapter);

                }
                else
                {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getRestaurantResult> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }

}
