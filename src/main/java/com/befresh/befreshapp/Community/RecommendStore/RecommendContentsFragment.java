package com.befresh.befreshapp.Community.RecommendStore;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.getRestaurantContent;
import com.befresh.befreshapp.Mypage.MypageModel.setMyPageRegist;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.befresh.befreshapp.Navigationmain.NavigationActivity.setid;
import static com.facebook.FacebookSdk.getApplicationContext;

public class RecommendContentsFragment extends MasterFragment implements OnMapReadyCallback
{
    private NavigationActivity mContext;
    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    private NetworkService networkService;
    GoogleMap googleMap = null;
    private MapView mapView = null;
    private String token;
    int id;
    getRestaurantContent.ResultData data;
    ImageView img;
    TextView title;
    TextView content;
    TextView workTime;
    TextView restTime;
    TextView lastOrder;
    TextView priceInfo;
    TextView address;
    CheckBox like;
    ImageView title_item_recommend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend_contents, container, false);

        mContext = (NavigationActivity) getMasterActivity();
        auto = getActivity().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        networkService = ApplicationController.getInstance().getNetworkService();
        autoLogin = auto.edit();
        token = auto.getString("token", null);
        Log.i("mytoken", token);
        img = (ImageView) view.findViewById(R.id.img_item_recommend);
        title = (TextView) view.findViewById(R.id.content_item_recommend);
        content = (TextView) view.findViewById(R.id.textView4);
        workTime = (TextView) view.findViewById(R.id.work_time_info_recommend_contents);
        restTime = (TextView) view.findViewById(R.id.rest_time_info_recommend_contents);
        lastOrder = (TextView) view.findViewById(R.id.last_order_info_recommend_contents);
        priceInfo = (TextView) view.findViewById(R.id.price_info_recommend_contents);
        address = (TextView) view.findViewById(R.id.address_recommend_contents);
        title_item_recommend = (ImageView)view.findViewById(R.id.title_item_recommend);
        like = (CheckBox) view.findViewById(R.id.like_item_recommend);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(id);
                Toast.makeText(mContext, like.isChecked()?"저장되었습니다.":"삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        mapView = (MapView)view.findViewById(R.id.map);
        mapView.getMapAsync(this);

        login();
        return view;
    }

    public void check(int id) {
        Call<setMyPageRegist> setMyPageRegistCall = networkService.setMyPageRegistCall(token, id, 3);
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
        Call<getRestaurantContent> getRestaurantContent = networkService.getRestaurantContent(token, setid);
        getRestaurantContent.enqueue(new Callback<getRestaurantContent>() {
            @Override
            public void onResponse(Call<getRestaurantContent> call, Response<getRestaurantContent> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    Log.i("MyTag", "추천상세");
                    getRestaurantContent getRestaurantContent = response.body();
                    data = getRestaurantContent.data;
                    Glide.with(mContext)
                            .load(getRestaurantContent.data.imageUrl)
                            .into(img);
                    id = data.id;
                    title.setText(data.title);
                    content.setText(data.content);
                    workTime.setText(data.open);
                    restTime.setText(data.breaking);
                    lastOrder.setText(data.lastorder);
                    priceInfo.setText(data.price);
                    like.setChecked(data.checkSaveList);
                    address.setText(data.detaillocation);
                    Glide.with(mContext).load(data.simplelocation).into(title_item_recommend);
                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<getRestaurantContent> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수
        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        LatLng SEOUL = new LatLng(Double.parseDouble(data.locationLatitude),Double.parseDouble(data.locationLongtitude));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title(data.title);
        markerOptions.snippet(data.content);
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));

    }
}
