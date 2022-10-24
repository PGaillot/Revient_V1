package com.gayo.revient.service;

import com.gayo.revient.model.User;

import java.util.List;

public interface UserApiService {

     List<User> getUsers();

     User getUserById(String id);
}
