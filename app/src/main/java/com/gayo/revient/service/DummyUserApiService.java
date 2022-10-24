package com.gayo.revient.service;

import com.gayo.revient.model.User;

import java.util.List;

public class DummyUserApiService implements UserApiService {

    List<User> users = DummyUserGenerator.getUsers();

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUserById(String id) {
        User u = null;
        for (User user :
                users) {
            if (user.getuId().equals(id)){
                u = user;
            }
        } if (u == null){
            System.out.println("Error : no User with the id : " + id);
        }
        return u;
    }
}
