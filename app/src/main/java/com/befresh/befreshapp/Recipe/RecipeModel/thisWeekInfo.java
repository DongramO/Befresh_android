package com.befresh.befreshapp.Recipe.RecipeModel;

import java.io.Serializable;

/**
 * Created by idongsu on 2017. 7. 2..
 */

public class thisWeekInfo implements Serializable {
    public int id;
    public String image_url;
    public String title;
    public String subtitle;
    public String difficult;
    public int cookingTime;
    public int postTime;
    public boolean checkSaveList;
    public String hashtag;
}
