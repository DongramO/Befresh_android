package com.befresh.befreshapp.Membership.FreshPeople.FreshPeople.FreshPeopleModel;

/**
 * Created by JongBong on 2017-07-02.
 */

public class getMemberWeekInfo {

    public String msg;
    public ResultData data;

    class ResultData {
        String imageUrl;
        String title;
        String subtitle;
        int cookingTime; //String?
        char difficulty;
    }
}


