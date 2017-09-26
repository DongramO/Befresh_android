package com.befresh.befreshapp.Community.CommunityModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getRestaurantResult
{

    public ArrayList<ResultDataRe> data;
    public String msg;

    public class ResultDataRe
    {
        public int id;
        public String imageUrl;
        public String title;
        public String location;
        public Boolean checkSaveList;
        public String content;

    }
}
