package com.befresh.befreshapp.Community.CommunityModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getMagazineMain
{
    public String msg;
    public ArrayList<ResultData> data;

    public class ResultData
    {
        public int id;
        public String imageUrl;
        public String title;
        public String content;
        public boolean checkSaveList;
    }
}
