package com.befresh.befreshapp.Community.MyRecipe;

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
import com.befresh.befreshapp.Community.CommunityModel.getRecipePhoto;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;
import com.befresh.befreshapp.Recipe.Adpater.MainLeftAdapter;
import com.befresh.befreshapp.Recipe.Model.MainRecipe_info;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.setid;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by idongsu on 2017. 6. 30..
 */

public class MyrecipeLeftFragment extends MasterFragment
{

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager manager;
    getRecipePhoto getrecipePhoto;
    NavigationActivity mContext;
    MainLeftAdapter adapter1;
    ArrayList<MainRecipe_info> MRinfo;
    NetworkService networkService;public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;
    MyRecipeAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.myrecipe_left_fragment, container, false);

        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token",null);
        Log.i("mytoken",token);

        mContext = (NavigationActivity) getActivity();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_my_recipe);
        manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        login();
        return view;
    }

    public View.OnClickListener clicklistener1 = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            final int position = recyclerView.getChildPosition(v);
            mContext.flag = 3;
            setid =  getrecipePhoto.data.get(position).id;
            mContext.changeFragment(27);

        }
    };

    public void login()
    {
        Call<getRecipePhoto> communityMain = networkService.getRecipePhoto(token);
        communityMain.enqueue(new Callback<getRecipePhoto>()
        {
            @Override
            public void onResponse(Call<getRecipePhoto> call, Response<getRecipePhoto> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful())
                {
                    Log.i("MyTag", "메인 네트워크");
                    getrecipePhoto = response.body();
                    adapter= new MyRecipeAdapter(mContext, getrecipePhoto.data, clicklistener1);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                }
                else
                {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getRecipePhoto> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }
}
