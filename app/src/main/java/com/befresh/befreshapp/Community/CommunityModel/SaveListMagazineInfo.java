package com.befresh.befreshapp.Community.CommunityModel;

import java.util.ArrayList;

/**
 * Created by JongBong on 2017-07-05.
 */

public class SaveListMagazineInfo {
    public String msg;
    public ArrayList<ResultData> data;

    public class ResultData {
        public int id;
        public String imageUrl;
        public String title;
        public boolean checkSaveList;
    }

}
