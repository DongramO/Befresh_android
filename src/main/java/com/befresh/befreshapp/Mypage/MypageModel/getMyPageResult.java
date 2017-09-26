package com.befresh.befreshapp.Mypage.MypageModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getMyPageResult {

    public ResultData data;
    //    public ArrayList<ResultData> data;
    public String msg;

    public class ResultData {
        public ArrayList<SavedRecipe> savedRecipe;

        public class SavedRecipe {
            public int id;
            public int from;
            public String image_url;
            public String title;
            public String subtitle;
            public String difficulty;
            public String cookingTime;
            public boolean checkSaveList;
        }
    }
}
