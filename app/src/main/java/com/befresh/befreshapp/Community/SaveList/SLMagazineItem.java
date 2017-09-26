package com.befresh.befreshapp.Community.SaveList;

/**
 * Created by JongBong on 2017-07-01.
 */

public class SLMagazineItem {
    int img;
    String title;
    String content;
    boolean like;

    public SLMagazineItem(int img, String title, String content, boolean like) {
        this.img = img;
        this.title = title;
        this.content = content;
        this.like = like;
    }
}
