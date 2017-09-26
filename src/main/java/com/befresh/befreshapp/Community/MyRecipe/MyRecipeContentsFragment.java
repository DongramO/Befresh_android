package com.befresh.befreshapp.Community.MyRecipe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.CommentSendInfo;
import com.befresh.befreshapp.Community.CommunityModel.getMyrecipeCommentResult;
import com.befresh.befreshapp.Community.CommunityModel.getRecipePhotoDetail;
import com.befresh.befreshapp.Mypage.MypageModel.setMyPageRegist;
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

public class MyRecipeContentsFragment extends MasterFragment {

    NavigationActivity mContext;
    NetworkService networkService;
    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;
    RecyclerView recyclerView;
    SibalAdapter adapter;
    TextView title, content, email, count;
    CheckBox like;
    ImageView content_image;
    ImageView complete;
    EditText comment_myrecipe;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_recipe_contents, container, false);
        mContext = (NavigationActivity) getMasterActivity();

        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token", null);
        Log.i("mytoken", token);

        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);
        email = (TextView) view.findViewById(R.id.email);
        count = (TextView) view.findViewById(R.id.like_count_my_recipe_contents);
        comment_myrecipe = (EditText) view.findViewById(R.id.comment_myrecipe);
        like = (CheckBox) view.findViewById(R.id.like_my_recipe_contents);
        content_image = (ImageView) view.findViewById(R.id.content_image);
        recyclerView = (RecyclerView) view.findViewById(R.id.content_rcv);
        complete = (ImageView) view.findViewById(R.id.complete_comment_myrecipe);
        linearLayoutManager =
                new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
                Toast.makeText(mContext, like.isChecked() ? "저장되었습니다." : "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                int c = Integer.parseInt(count.getText().toString());
                count.setText(String.valueOf(like.isChecked() ? c + 1 : c - 1));

                Log.i("MyTag", "count : " + Integer.parseInt(count.getText().toString()));
                Log.i("MyTag", "count int : " + (like.isChecked() ? c + 1 : c - 1));
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment();
            }
        });
        login();
        return view;
    }

    public void check() {
        Call<setMyPageRegist> setMyPageRegistCall = networkService.setMyPageRegistCall(token, setid, 2);
        setMyPageRegistCall.enqueue(new Callback<setMyPageRegist>() {
            @Override
            public void onResponse(Call<setMyPageRegist> call, Response<setMyPageRegist> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "메인 네트워크");
                    setMyPageRegist setMyPageRegist = response.body();
                    Log.i("MyTag", setMyPageRegist.msg);

                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<setMyPageRegist> call, Throwable t) {

            }
        });
    }


    public void login() {
        Call<getRecipePhotoDetail> communityMain = networkService.getRecipePhotoDetail(token, setid);
        communityMain.enqueue(new Callback<getRecipePhotoDetail>() {
            @Override
            public void onResponse(Call<getRecipePhotoDetail> call, Response<getRecipePhotoDetail> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    getRecipePhotoDetail getRecipePhotoDetail = response.body();
                    if (getRecipePhotoDetail.data.comment != null) {
                        title.setText(getRecipePhotoDetail.data.RecipePhoto.title);
                        content.setText(getRecipePhotoDetail.data.RecipePhoto.content);
                        count.setText(String.valueOf(getRecipePhotoDetail.data.RecipePhoto.saveCount));
                        email.setText(getRecipePhotoDetail.data.RecipePhoto.writerEmail);
                        like.setChecked(getRecipePhotoDetail.data.RecipePhoto.checkSaveList);
                        Glide.with(mContext).load(getRecipePhotoDetail.data.RecipePhoto.imageUrl).into(content_image);
                        adapter = new SibalAdapter(mContext, getRecipePhotoDetail.data.comment);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getRecipePhotoDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }


    public void comment() {
        CommentSendInfo commentSendInfo = new CommentSendInfo();
        commentSendInfo.id = setid;
        commentSendInfo.comment = comment_myrecipe.getText().toString();
        Call<getMyrecipeCommentResult> communityMain = networkService.getMyrecipeCommentResultCall(token, commentSendInfo);
        communityMain.enqueue(new Callback<getMyrecipeCommentResult>() {
            @Override
            public void onResponse(Call<getMyrecipeCommentResult> call, Response<getMyrecipeCommentResult> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "메인 네트워크");
                    getMyrecipeCommentResult getCommunityMain = response.body();
                    mContext.changeFragment(27);
                } else

                {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getMyrecipeCommentResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }

}
