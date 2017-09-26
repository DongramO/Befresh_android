package com.befresh.befreshapp.Mypage.MypageModel;

import java.util.ArrayList;

/**
 * Created by JongBong on 2017-07-06.
 */

public class getMyDeliveriedResult {

    public String msg;
    public ResultData data;

    public class ResultData
    {
        public ArrayList<DeliveriedRecipe> deliveriedRecipe;
    }
    public class DeliveriedRecipe {
        public int id;
        public String image_url;
        public String title;
        public String deliveried_date;
        public int check_review;
    }
}
