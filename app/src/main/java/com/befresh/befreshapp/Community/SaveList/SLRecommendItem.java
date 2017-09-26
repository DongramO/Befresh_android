package com.befresh.befreshapp.Community.SaveList;

/**
 * Created by JongBong on 2017-07-01.
 */

public class SLRecommendItem {

    int img;
    int loc;
    String name;
    String content;
    boolean like;

    public SLRecommendItem(int img, int loc, String name, String content, boolean like) {
        this.img = img;
        this.loc = loc;
        this.name = name;
        this.content = content;
        this.like = like;
    }
}
