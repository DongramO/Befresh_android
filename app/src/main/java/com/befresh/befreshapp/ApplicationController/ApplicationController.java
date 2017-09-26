package com.befresh.befreshapp.ApplicationController;

/**
 * Created by idongsu on 2017. 6. 26..
 */

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.befresh.befreshapp.Network.NetworkService;
import com.tsengvn.typekit.Typekit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApplicationController extends Application {

    private static ApplicationController instance;

    private static String baseUrl = "xxxxxxxxxxxx";
    private  final int PORT = xxxx;

    private NetworkService networkService;
    private static ApplicationController mInstance;
    private static volatile Activity currentActivity = null;

    public static ApplicationController getInstance() {
        return instance;
    }

    public NetworkService getNetworkService() {
        return networkService;
    }


    @Override
    public void onCreate()
    {
        super.onCreate();
        ApplicationController.instance = this;
        mInstance = this;
//        KakaoSDK.init(new KaKaoSDKAdapter());
        buildService();
        Typekit.getInstance()
        .addNormal(Typekit.createFromAsset(this, "NanumBarunGothic.ttf"))
        .addBold(Typekit.createFromAsset(this, "NanumBarunGothicBold.ttf"));
    }



    public void buildService() {


        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);
    }



    public static Activity getCurrentActivity()
    {
        Log.d("TAG", "++ currentActivity : " + (currentActivity != null ? currentActivity.getClass().getSimpleName() : ""));
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        ApplicationController.currentActivity = currentActivity;
    }

    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static ApplicationController getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit ");
        return instance;
    }

    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }


}

