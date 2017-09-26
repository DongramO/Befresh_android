package com.befresh.befreshapp.Community.CommunityModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 2..
 */

public class RecipePhoto2
{
    public ResultData data;

    public class ResultData
    {
        ArrayList<RecipePhoto> recipephoto;
    }

    public class RecipePhoto
    {
        public int id;
        public String imageUrl;
        public String title;
        public boolean checkSaveList;
    }
}
