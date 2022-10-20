package com.gayo.revient.model;

import java.io.Serializable;
import java.util.Date;

public class Stuff implements Serializable {


    String name;
    String owner;
    String borrower;
    String type;
    Date creationDate;
    Date currentLoanDate;
    int initialLoanPeriodInDay;




    public Stuff(String name,  String owner, String borrower, String type, Date creationDate, Date currentLoanDate, int initialLoanPeriodInDay) {
        this.name = name;
        this.owner = owner;
        this.borrower = borrower;
        this.type = type;
        this.creationDate =creationDate;
        this.currentLoanDate=currentLoanDate;
        this.initialLoanPeriodInDay = initialLoanPeriodInDay;
    };

    public Stuff(String name, String owner, String type,  Date creationDate, Date currentLoanDate, int initialLoanPeriodInDay) {
        this.name = name;
        this.owner = owner;
        this.type = type;
        this.creationDate = creationDate;

    };

    public Stuff(){
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getCurrentLoanDate() {
        return currentLoanDate;
    }

    public void setCurrentLoanDate(Date currentLoanDate) {
        this.currentLoanDate = currentLoanDate;
    }

    public int getInitialLoanPeriodInDay() {
        return initialLoanPeriodInDay;
    }

    public void setInitialLoanPeriodInDay(int initialLoanPeriodInDay) {
        this.initialLoanPeriodInDay = initialLoanPeriodInDay;
    }
}
