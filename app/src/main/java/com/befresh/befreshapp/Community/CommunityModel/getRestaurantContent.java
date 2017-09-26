package com.befresh.befreshapp.Community.CommunityModel;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getRestaurantContent
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
        public int id;
        public String imageUrl;
        public String title;
        public String simplelocation;
        public String content;
        public String open;
        public String breaking;
        public String lastorder;
        public String price;
        public String detaillocation;
        public String locationImage;
        public String locationLatitude;
        public String locationLongtitude;
        public boolean checkSaveList;
    }
}
