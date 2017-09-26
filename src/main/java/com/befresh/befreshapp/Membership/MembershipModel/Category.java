package com.befresh.befreshapp.Membership.MembershipModel;

/**
 * Created by idongsu on 2017. 7. 1..
 */

public class Category {
    public int category;

    public Category(int category) {

        switch(category) {
            case 1:
                this.category=1;
                break;
            case 2:
                this.category=2;
                break;
            case 3:
                this.category=3;
                break;
        }
    }
}
