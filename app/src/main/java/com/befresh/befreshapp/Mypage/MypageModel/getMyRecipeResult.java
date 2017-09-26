package com.befresh.befreshapp.Mypage.MypageModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getMyRecipeResult{

    public ResultData data;
    public String msg;
    public class ResultData
    {
        ArrayList<MyRecipeInfo> myRecipeInfo;
    }

    public class MyRecipeInfo
    {
        public int id;
        public String imageUrl;
        public String date;
        public String titlle;
        public String material;
        public boolean chekcWritten;
    }
}
