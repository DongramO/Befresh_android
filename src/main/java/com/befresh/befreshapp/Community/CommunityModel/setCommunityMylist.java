package com.befresh.befreshapp.Community.CommunityModel;

import java.util.ArrayList;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class setCommunityMylist
{
    public ResultData data;
    public String msg;

    public class ResultData
    {
        ArrayList<MyListInfo> mylistInfo;
    }


}
class MyListInfo
{
    public int id;
    public String imageUrl;
    public String title;
    public boolean checkSaveList;
}
