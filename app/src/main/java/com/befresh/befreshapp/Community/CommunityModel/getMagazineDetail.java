package com.befresh.befreshapp.Community.CommunityModel;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class getMagazineDetail {
    public String msg;
    public ResultData data;

    public class ResultData {
        public int id;
        public String content;
        public boolean checkSaveList;
        public int width;
        public int height;
    }
}
