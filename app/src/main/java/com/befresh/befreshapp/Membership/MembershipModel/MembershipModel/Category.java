package com.befresh.befreshapp.Membership.MembershipModel.MembershipModel;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class Category {
    public int category;

    public Category(String category) {

        switch(category) {
            case "W":
                this.category=1;
                break;
            case "V":
                this.category=2;
                break;
            case "B":
                this.category=3;
                break;
        }
    }
}
