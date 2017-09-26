package com.befresh.befreshapp.Search;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 6..
 */

public class SearchResult
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
       public ArrayList<searching> search;
    }

    public class searching
    {
        public int id;
        public String image_url;
        public String title;
        public String subtitle;
        public String difficulty;
        public String cookingTime;
        public String hashtag;
        public boolean checkSaveList;
    }
}
