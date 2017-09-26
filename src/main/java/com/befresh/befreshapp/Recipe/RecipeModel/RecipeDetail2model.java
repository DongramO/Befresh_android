package com.befresh.befreshapp.Recipe.RecipeModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 2..
 */

public class RecipeDetail2model
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
        ArrayList<cardinfo> card;
    }

    public class cardinfo
    {
        public String title;
        public String content;
        public String image;
    }
}
