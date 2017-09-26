package com.befresh.befreshapp.Account;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by 고혜민 on 2017-07-01.
 */

public class ChangepwFragment extends MasterFragment {

    NavigationActivity mContext;
    ImageView change_btn;
    EditText cur_pw, newpw, renewpw;
    TextView error_msg;
    String password;
    NetworkService networkService;public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.account_changepw, container, false);
        mContext = (NavigationActivity) getMasterActivity();
        change_btn = (ImageView) view.findViewById(R.id.btn_changepw);
        cur_pw = (EditText) view.findViewById(R.id.current_pw);
        newpw = (EditText) view.findViewById(R.id.new_pw);
        renewpw = (EditText) view.findViewById(R.id.re_new_pw);
        error_msg = (TextView) view.findViewById(R.id.text_pw_error);
        error_msg.setVisibility(View.INVISIBLE);

        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token = auto.getString("token",null);
        Log.i("mytoken",token);

        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        renewpw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = newpw.getText().toString().trim();
                String re_password = charSequence.toString().trim();
                Log.i("mytag", re_password);
                if(password.equals(re_password)){
                    error_msg.setVisibility(View.INVISIBLE);
                }
                else{
                    error_msg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

  //      change_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
  //          public void onClick(View view) {
//
  //              String new_password = newpw.getText().toString();
            //    String re_password = renewpw.getText().toString();
//
//                if (new_password.equals(re_password)) {
//
//                } else {
  //                  text.setVisibility(View.VISIBLE);
//
    //            }
      //      }
   //     });
        return view;
    }

    public void login()
    {
        PassInfo pass = new PassInfo();
        pass.oldPwd = cur_pw.getText().toString();
        pass.newPwd= newpw.getText().toString();

        Call<ChangePw> communityMain = networkService.ChangePw(token,pass);
        communityMain.enqueue(new Callback<ChangePw>()
        {
            @Override
            public void onResponse(Call<ChangePw> call, Response<ChangePw> response)
            {
                Log.i("MyTag", response.message());
                Log.i("MyTag", "response code : " + response.code());

                if (response.isSuccessful())
                {
                    Log.i("MyTag", "메인 네트워크");
                    ChangePw getCommunityMain = response.body();
                    Toast.makeText(mContext, "성공적으로 변경 되었습니다.", Toast.LENGTH_SHORT).show();
                    mContext.changeFragment(90);

                }
                else
                {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드 : " + statusCode);
                    if(statusCode == 400)
                    {
                        Toast.makeText(mContext, "비밀번호 양식을 맞춰주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(statusCode == 401)
                    {
                        Toast.makeText(mContext, "기존 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePw> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_LONG).show();
                Log.i("MyTag", "에러내용 : " + t.getMessage());
            }
        });
    }
}
