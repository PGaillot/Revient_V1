package com.gayo.revient.model;

public class StuffType {

    String uId;
    String name;
    //TODO : find out how to add an image, an icon and a color.

    public StuffType(String uId, String name) {
        this.uId = uId;
        this.name = name;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
