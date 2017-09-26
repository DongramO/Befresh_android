package com.befresh.befreshapp.Recipe.RecipeModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 2..
 */

public class getRecipeMainVegbeingfilter
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
        ArrayList<FromTime> fromTime;
    }
    public class FromTime
    {
        public int id;
        public String imageUrl;
        public String title;
        public String subtitle;
        public String difficulty;
        public int cookingTime;
        public boolean checkSaveList;
    }
}
