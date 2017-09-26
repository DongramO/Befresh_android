package com.befresh.befreshapp.Recipe.RecipeModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 2..
 */

public class getRecipeSearch
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
        ArrayList<searchinfo> search;
    }

    public class searchinfo
    {
        public int id;
        public String image_url;
        public String title;
        public String subtitle;
        public String difficulty;
        public int cookingTime;
        public boolean checkSaveList;

    }
}
