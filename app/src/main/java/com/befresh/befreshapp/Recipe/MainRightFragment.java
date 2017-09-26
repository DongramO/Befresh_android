package com.befresh.befreshapp.Recipe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;
import com.befresh.befreshapp.Recipe.Adpater.MainRightAdapter;
import com.befresh.befreshapp.Recipe.Model.MainRecipe_info;
import com.befresh.befreshapp.Recipe.RecipeModel.getRecipeMainWellbeing;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.bundle;
import static com.befresh.befreshapp.Navigationmain.NavigationActivity.filterFlag;
import static com.befresh.befreshapp.Navigationmain.NavigationActivity.kind1;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by JongBong on 2017-06-25.
 */

public class MainRightFragment extends Fragment {
    private RecyclerView productrecyclerview;
    private LinearLayoutManager linearLayoutManager;
    NavigationActivity mContext;
    MainRightAdapter adapter1;
    ArrayList<MainRecipe_info> MRinfo;
    NetworkService networkService;
    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;
    getRecipeMainWellbeing getRecipeMainWellbeing;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_left_main, container, false);

        mContext = (NavigationActivity) getActivity();
        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);

        productrecyclerview = (RecyclerView) view.findViewById(R.id.main_left_rcv1);
        productrecyclerview.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productrecyclerview.setLayoutManager(linearLayoutManager);

        if (filterFlag == 1 && kind1 ==1)
        {
            if(bundle != null) {
                getRecipeMainWellbeing.ResultData data = bundle;
                adapter1 = new MainRightAdapter(mContext, clicklistener1, data.pastWeek, data.thisWeek);
                adapter1.notifyDataSetChanged();
                productrecyclerview.setAdapter(adapter1);
                filterFlag = 0;
            }
        }
        else
        {
            login();
        }

        return view;
    }

    public View.OnClickListener clicklistener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = productrecyclerview.getChildPosition(v);
            mContext.flag = 3;

        }
    };

    public void login() {
        Call<getRecipeMainWellbeing> communityMain = networkService.getRecipeVeg(token);
        communityMain.enqueue(new Callback<getRecipeMainWellbeing>()
        {
            @Override
            public void onResponse(Call<getRecipeMainWellbeing> call, Response<getRecipeMainWellbeing> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    getRecipeMainWellbeing = response.body();
                    adapter1 = new MainRightAdapter(mContext, clicklistener1, getRecipeMainWellbeing.data.pastWeek, getRecipeMainWellbeing.data.thisWeek);
                    productrecyclerview.setAdapter(adapter1);


                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getRecipeMainWellbeing> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }
}
