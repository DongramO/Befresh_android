package com.befresh.befreshapp.Magazine;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.getMagazineDetail;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.setid;
import static com.facebook.FacebookSdk.getApplicationContext;

public class MagazineContentsFragment extends MasterFragment {
    private NavigationActivity mContext;
    NetworkService networkService;
    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;
    ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        mContext = (NavigationActivity) getMasterActivity();
        token = auto.getString("token", null);


        View view = inflater.inflate(R.layout.fragment_magazine_contents, container, false);
        img = (ImageView) view.findViewById(R.id.recycler_magazine_contents);
        login();

        return view;
    }

    public void login() {
        Call<getMagazineDetail> communityMain = networkService.getMagazineDetail(token, setid);
        communityMain.enqueue(new Callback<getMagazineDetail>() {
            @Override
            public void onResponse(Call<getMagazineDetail> call, Response<getMagazineDetail> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "메거진 컨텐츠");
                    final getMagazineDetail getCommunityMain = response.body();
                    int image_width = getCommunityMain.data.width;
                    int image_height = getCommunityMain.data.height;
                    int k = (image_width * 640) / image_height;
                    Log.i("k", k+"");
//                    img.getLayoutParams().width = 128*7;
                    img.getLayoutParams().height = getCommunityMain.data.height*3;
                    img.requestLayout();
                    Log.i("widhtandheight",img.getLayoutParams().height+"");
                    Glide.with(mContext).load(getCommunityMain.data.content).into(img);

                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getMagazineDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }

}
