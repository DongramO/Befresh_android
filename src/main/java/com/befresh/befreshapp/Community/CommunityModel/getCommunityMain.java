package com.befresh.befreshapp.Community.CommunityModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getCommunityMain {
    public String msg;
    public ResultData data;

    public class ResultData {
        public ArrayList<RecipePhoto> RecipePhoto;
        public ArrayList<Restaurant> Restaurant;
        public ArrayList<Magazine> Magazine;
        public ArrayList<Savelist> SaveList;
    }
}
