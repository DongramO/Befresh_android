package com.befresh.befreshapp.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Login.LoginModel.UserInfo;
import com.befresh.befreshapp.Login.LoginModel.getSigninResult;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends Activity {


    ImageView complete;
    final int REQ_CODE_SELECT_IMAGE = 100;
    MultipartBody.Part body;
    NetworkService networkService;
    EditText nm, em, pw, re_pw;
    TextView error_msg;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        networkService = ApplicationController.getInstance().getNetworkService();
        complete = (ImageView) findViewById(R.id.complete);
        nm = (EditText) findViewById(R.id.insert_nm);
        em = (EditText) findViewById(R.id.insert_em);
        pw = (EditText) findViewById(R.id.insert_pw);
        re_pw = (EditText) findViewById(R.id.re_insert_pw);
        error_msg = (TextView) findViewById(R.id.error_msg);
        error_msg.setVisibility(View.INVISIBLE);

        re_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = pw.getText().toString().trim();
                String re_password = charSequence.toString().trim();
                Log.i("mytag", re_password);
                if (password.equals(re_password)) {
                    error_msg.setVisibility(View.INVISIBLE);
                } else {
                    error_msg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mytag", "request signup");
                signup();
            }
        });
    }

    public void signup() {
        UserInfo user = new UserInfo();

        user.name = nm.getText().toString();
        user.email = em.getText().toString();
        user.pwd = pw.getText().toString();

        Log.i("mytag", "request signup ~ing");
        Call<getSigninResult> signupResultCall = networkService.getSigninResult(user);
        signupResultCall.enqueue(new Callback<getSigninResult>() {
            @Override
            public void onResponse(Call<getSigninResult> call, Response<getSigninResult> response) {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful()) {
                    getSigninResult signUpResult = response.body();
                    String k = signUpResult.msg;
                    Log.i("MyTag", "결과 : " + signUpResult);
                    Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_LONG).show();
                    Intent signend = new Intent(getApplicationContext(), LoginPage.class);
                    signend.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    signend.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(signend);
                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                    Toast.makeText(SignUp.this, "회원가입 양식을 지켜주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<getSigninResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
                finish();
            }
        });
    }

}