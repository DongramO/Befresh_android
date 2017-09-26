package com.befresh.befreshapp.Magazine;

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
import com.befresh.befreshapp.Community.CommunityModel.getMagazineMain;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.setid;
import static com.facebook.FacebookSdk.getApplicationContext;

/*
*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MagazineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MagazineFragment#newInstance} factory method to
 * create an instance of this fragment.
*/
public class MagazineFragment extends MasterFragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    MagazineRecyclerAdapter adapter;
    NavigationActivity mContext;
    ArrayList<getMagazineMain.ResultData> data;
    NetworkService networkService;public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;
    getMagazineMain getCommunityMain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_magazine, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_magazine);
        mContext = (NavigationActivity)getMasterActivity();
        manager = new GridLayoutManager(mContext, 2);
        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token",null);
        Log.i("mytoken",token);


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
            mContext.changeFragment(25);
            setid = getCommunityMain.data.get(position).id;
        }
    };

    public void login()
    {
        Call<getMagazineMain> communityMain = networkService.getMagazineMain(token);
        communityMain.enqueue(new Callback<getMagazineMain>()
        {
            @Override
            public void onResponse(Call<getMagazineMain> call, Response<getMagazineMain> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful())
                {
                    Log.i("MyTag", "메인 네트워크");
                    getCommunityMain = response.body();
                    adapter = new MagazineRecyclerAdapter(mContext, getCommunityMain.data, clicklistener1);
                    recyclerView.setAdapter(adapter);

                }
                else
                {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getMagazineMain> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }
}
