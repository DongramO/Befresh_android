package com.befresh.befreshapp.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Login.LoginModel.LoginInfo;
import com.befresh.befreshapp.Login.LoginModel.getLoginResult;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends Activity {

    private TextView signup;
    private ImageView login,facebook;
    private CallbackManager callbackManager;
    private JSONObject jsonObject;
    private NetworkService networkService;
    private EditText login_email, login_pwd;
    private SharedPreferences auto;
    private SharedPreferences.Editor autoLogin;
    public int loginFlag = 0;
    public String useremail;
    public String useruid;
    public String userpwd;
    public String username;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login_page2);

        auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        String token = auto.getString("token",null);

        networkService = ApplicationController.getInstance().getNetworkService();
        signup = (TextView)findViewById(R.id.singup);
        login = (ImageView)findViewById(R.id.login1);
        facebook = (ImageView)findViewById(R.id.facebook);
        login_email = (EditText)findViewById(R.id.login_email);
        login_pwd = (EditText)findViewById(R.id.login_pwd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void facebookLoginOnClick(View v){

        loginFlag = 1;
        LoginManager.getInstance().logInWithReadPermissions(LoginPage.this,
                Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(final LoginResult result)
            {
                GraphRequest request;
                request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject user, GraphResponse response) {
                        if (response.getError() != null) {

                        } else {
                            Log.i("TAG", "user: " + user.toString());
                            Log.i("TAG", "AccessToken: " + result.getAccessToken().getToken());
                            setResult(RESULT_OK);

                            jsonObject = user;
                            try
                            {
                                useremail = jsonObject.get("email").toString();
                                username = jsonObject.get("name").toString();
                                useruid = jsonObject.get("id").toString();

                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                            setResult(RESULT_OK);
                            login();
//                            Intent i = new Intent(LoginPage.this, NavigationActivity.class);
//                            startActivity(i);
                            finish();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("test", "Error: " + error);
                //finish();
            }

            @Override
            public void onCancel() {
                //finish();
            }
        });
    }

    public void login()
    {
        final LoginInfo loginInfo = new LoginInfo();
        loginInfo.email = login_email.getText().toString();
        loginInfo.pwd = login_pwd.getText().toString();
        loginInfo.uid = "1";
        if(loginFlag == 1)
        {
            loginInfo.email = useremail;
            loginInfo.pwd =null;
            loginInfo.uid = useruid;
            loginInfo.name = username;
        }
        Call<getLoginResult> loginResultCall = networkService.getLoginResult(loginInfo);
        loginResultCall.enqueue(new Callback<getLoginResult>()
        {
            @Override
            public void onResponse(Call<getLoginResult> call, Response<getLoginResult> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful())
                {
                    getLoginResult signUpResult = response.body();
                    Log.i("mytag",signUpResult.token);
                    Toast.makeText(getApplicationContext(), "로그인 완료",Toast.LENGTH_LONG).show();
                    autoLogin.putString("email", loginInfo.email);
                    autoLogin.putString("pwd", loginInfo.pwd);
                    autoLogin.putString("token", signUpResult.token);
                    autoLogin.putString("name", signUpResult.name);
                    autoLogin.commit();

                    Intent signend = new Intent(getApplicationContext(), NavigationActivity.class);
                    signend.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    signend.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(signend);
                }
                else
                {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                    Toast.makeText(LoginPage.this, "아이디 및 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<getLoginResult> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
                finish();
            }
        });
    }
}
