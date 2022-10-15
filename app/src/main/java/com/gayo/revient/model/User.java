package com.gayo.revient.model;

import java.util.List;

public class User {

    String uId;
    String firstName;
    String lastName;
    List<String> stuffOwned;
    List<String> friends;


    public User(String uId, String firstName, String lastName, List<String> stuffOwned, List<String> friends) {
        this.uId = uId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stuffOwned = stuffOwned;
        this.friends = friends;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getStuffOwned() {
        return stuffOwned;
    }

    public void setStuffOwned(List<String> stuffOwned) {
        this.stuffOwned = stuffOwned;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {this.friends = friends;}

    /**
     * Return the user name with this form : Firstname N.
     *
     * @param user
     * @return Firstname N.
     */
    public String GetShortName(User user) {

        char lastnameLetter = user.lastName.charAt(0);
        Character.toUpperCase(lastnameLetter);
        String fName = user.firstName;
        fName.toUpperCase().charAt(0);

        return fName + " " + lastnameLetter + ".";
    }

    ;

}
