package com.befresh.befreshapp.Recipe.RecipeModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 2..
 */

public class getRecipeReview
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
        public ArrayList<RecipeReview> review;
    }
    public class RecipeReview
    {
        public String writer;
        public String content;
        public int score;
    }
}
