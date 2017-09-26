package com.befresh.befreshapp.Search;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.setid;
import static com.facebook.FacebookSdk.getApplicationContext;

public class SearchFragment extends MasterFragment
{

    EditText search;
    ImageView close;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    SearchRecyclerAdapter adapter;
    private NavigationActivity mContext;
    NetworkService networkService;public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    LinearLayoutManager linearLayoutManager;
    String token;
    ImageView imgsearch;
    SearchResult searchResult;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = (NavigationActivity) getMasterActivity();
        View view = inflater.inflate(R.layout.activity_sch, container, false);
        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token",null);
        Log.i("mytoken",token);

        search = (EditText) view.findViewById(R.id.edit_search);
        imgsearch = (ImageView)view.findViewById(R.id.img_search);
        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_search);
        recyclerView.setLayoutManager(linearLayoutManager);

        imgsearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               login();
           }
        });
//        adapter = new SearchRecyclerAdapter(mContext, items);

        return view;
    }

    public View.OnClickListener clicklistener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position =recyclerView.getChildPosition(v);
            setid = searchResult.data.search.get(position).id;
            Log.i("SetId", setid+"");
            mContext.changeFragment(6);
        }
    };

    public void login()
    {
        String word = search.getText().toString();
        Call<SearchResult> communityMain = networkService.SearchResult(token, word);
        communityMain.enqueue(new Callback<SearchResult>()
        {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response)
            {
                if (response.isSuccessful())
                {
                    Log.i("MyTag", "검색 네트워크");
                    searchResult = response.body();
                    if(searchResult.data.search.size() == 0)
                    {
                        Log.i("Searching Result","NO NO NO");
                        Toast.makeText(mContext, "결과가 없습니다 *^^*", Toast.LENGTH_SHORT).show();
                    }
                    adapter = new SearchRecyclerAdapter(mContext, searchResult.data.search,clicklistener1);
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
            public void onFailure(Call<SearchResult> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }
}
