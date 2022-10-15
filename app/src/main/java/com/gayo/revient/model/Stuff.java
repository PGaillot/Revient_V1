package com.gayo.revient.model;

public class Stuff {

    public Stuff(String uId, User owner, User borrower, StuffType type) {
        this.uId = uId;
        this.owner = owner;
        this.borrower = borrower;
        this.type = type;
    }

    public Stuff(String uId, User owner, StuffType type) {
        this.uId = uId;
        this.owner = owner;
        this.type = type;
    }

    String uId;
    User owner;
    User borrower;
    StuffType type;
}
