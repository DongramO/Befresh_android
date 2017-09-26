package com.befresh.befreshapp.Recipe.RecipeModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 2..
 */

public class getRecipeContent
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
        public RecipeContent recipe;
        public ArrayList<ReviewInfo> review;
    }

    public class imageInfo
    {
        public ArrayList<urlInfo> image;
    }
    public class urlInfo
    {
        public String url;
    }
    public class cardInfo
    {
        public ArrayList<cardInfo2> card;
    }
    public class cardInfo2
    {
        public String title;
        public String content;
        public String image;
    }
    public class ReviewInfo
    {
        public String writer;
        public String content;
        public int score;

    }
    public class RecipeContent
    {
        public int id;
        public String title;
        public imageInfo imageSet;
        public String subtitle;
        public String hashtag;
        public int cookingTime;
        public String difficulty;
        public cardInfo method;
        public String description;
        public String material_image;
        public ArrayList<String> material_list;
        public boolean checkSaveList;
        public int review_count;


    }
}
