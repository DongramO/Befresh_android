package com.befresh.befreshapp.Community.CommunityModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getRecipePhoto
{
    public ArrayList<ResultData> data;
    public String msg;
    public class ResultData
    {
        public int id;
        public String imageUrl;
        public String title;
        public boolean checkSaveList;
    }

}
