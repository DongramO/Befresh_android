package com.befresh.befreshapp.Recipe.RecipeModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 2..
 */
public class getRecipeMainWellbeing
{
    public String msg;
    public ResultData data;

    public class ResultData {
        public thisWeekInfo thisWeek;
        public ArrayList<pastWeekInfo> pastWeek;
    }
}
