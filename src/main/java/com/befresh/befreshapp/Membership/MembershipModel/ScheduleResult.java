package com.befresh.befreshapp.Membership.MembershipModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 5..
 */

public class ScheduleResult
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
        public String category;
        public ArrayList<Integer> delivery;
        public String week;
        public ArrayList<RecipeInfo> recipe;
    }
    public class RecipeInfo
    {
        public int id;
        public String image_url;
        public String title;
        public String hashtag;
        public String delivery_date;
    }
}
