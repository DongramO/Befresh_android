package com.befresh.befreshapp.Community.CommunityModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getRecipePhotoDetail
{
    public String msg;
    public ResultData data;

    public class ResultData
    {
        public PhotoInfo RecipePhoto;
        public ArrayList<CommentInfo> comment;
    }

}

