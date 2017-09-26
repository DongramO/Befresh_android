package com.befresh.befreshapp.Community.MyRecipe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.befresh.befreshapp.ApplicationController.ApplicationController;
import com.befresh.befreshapp.Community.CommunityModel.setReview;
import com.befresh.befreshapp.Navigationmain.MasterFragment;
import com.befresh.befreshapp.Navigationmain.NavigationActivity;
import com.befresh.befreshapp.Network.NetworkService;
import com.befresh.befreshapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class WriteMyRecipeFragment extends MasterFragment {
    private NavigationActivity mContext;
    String imgUrl = "";
    Uri data1;
    NetworkService networkService;
    public SharedPreferences auto;
    public SharedPreferences.Editor autoLogin;
    String token1;
    ImageView img;
    EditText title1;
    EditText content1;
    TextView complete;
    int width1;
    int height1;
    Button btn;
    ImageButton back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write_my_recipe, container, false);

        mContext = (NavigationActivity) getMasterActivity();

        networkService = ApplicationController.getInstance().getNetworkService();
        auto = getContext().getSharedPreferences("auto", Activity.MODE_PRIVATE);
        autoLogin = auto.edit();
        token1 = auto.getString("token", null);

        back = (ImageButton) view.findViewById(R.id.back);
        img = (ImageView) view.findViewById(R.id.img_write_my_recipe);
        title1 = (EditText) view.findViewById(R.id.title_write_my_recipe);
        content1 = (EditText) view.findViewById(R.id.content_write_my_recipe);
        complete = (TextView) view.findViewById(R.id.toolbar_complete);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 200);

            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteRegist();
                mContext.changeFragment(20);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onBackPressed();
            }
        });
        return view;
    }


    // 선택된 이미지 가져오기
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 200) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String name_Str = getImageNameToUri(data.getData());
                    this.data1 = data.getData();
                    Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(data1));
                    img.setImageBitmap(bitmap);
                    width1 = img.getWidth();
                    height1 = img.getHeight();
                    Toast.makeText(mContext, "width : " + width1 + ", height : " + height1, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                imgUrl = "";

            }
        }
    }

    // 선택된 이미지 파일명 가져오기
    public String getImageNameToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        imgUrl = imgPath;

        //불러온 사진을 이미지 뷰에 장착!
        File imgFile = new File(imgUrl);
        Log.i("mytag", "리뷰 사진불러오기" + imgFile.getAbsolutePath());

        return imgName;
    }


    public void WriteRegist() {
        if (title1.length() == 0 || content1.length() == 0) {
            Toast.makeText(getApplicationContext(), "제목 및 내용을 확인해주세요.", Toast.LENGTH_SHORT).show();
        } else {

            RequestBody title = RequestBody.create(MediaType.parse("multipart/form-data"), title1.getText().toString());
            RequestBody content = RequestBody.create(MediaType.parse("multipart/form-data"), content1.getText().toString());
            RequestBody width = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(width1));
            RequestBody height = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(height1));


            MultipartBody.Part body;

            if (imgUrl == "") {
                body = null;

            } else {
//                image_flag = RequestBody.create(MediaType.parse("multipart/form-data"), "true");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4; //얼마나 줄일지 설정하는 옵션 4--> 1/4로 줄이겠다

                InputStream in = null; // here, you need to get your context.
                try {
                    in = mContext.getContentResolver().openInputStream(data1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                        /*inputstream 형태로 받은 이미지로 부터 비트맵을 만들어 바이트 단위로 압축
                        그이우 스트림 배열에 담아서 전송합니다.
                         */

                Bitmap bitmap = BitmapFactory.decodeStream(in, null, options); // InputStream 으로부터 Bitmap 을 만들어 준다.
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);

                // 압축 옵션( JPEG, PNG ) , 품질 설정 ( 0 - 100까지의 int형 ), 압축된 바이트 배열을 담을 스트림
                RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());

                File photo = new File(imgUrl); // 가져온 파일의 이름을 알아내려고 사용합니다

                // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!
                body = MultipartBody.Part.createFormData("image", photo.getName(), photoBody);

            }

            Call<setReview> getWriteResult = networkService.setReview(body, token1, title, content, width, height);
            getWriteResult.enqueue(new Callback<setReview>() {
                @Override
                public void onResponse(Call<setReview> call, Response<setReview> response) {
                    Log.i("MyTag", response.message());
                    Log.i("MyTag", "response code : " + response.code());

                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "My Recipe 완료", Toast.LENGTH_LONG).show();
                    } else {
                        int statusCode = response.code();
                        Log.i("MyTag", "응답코드 : " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<setReview> call, Throwable t) {
                    Log.i("MyTag", "에러내용 : " + t.getMessage());
                }
            });
        }
    }
}