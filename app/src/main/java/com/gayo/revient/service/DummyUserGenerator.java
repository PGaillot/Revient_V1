package com.gayo.revient.service;

import com.gayo.revient.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DummyUserGenerator {

    public static List<User> mUsers = Arrays.asList(
            new User("0", "pierre", "gaillot", new ArrayList<String>(), new ArrayList<String>() ),
            new User("1", "léa", "coulomb", new ArrayList<String>(), new ArrayList<String>() ),
            new User("2", "thomas", "henocque", new ArrayList<String>(), new ArrayList<String>()  ),
            new User("3", "kevin", "mille", new ArrayList<String>(), new ArrayList<String>()  ),
            new User("4", "romain", "chalbi", new ArrayList<String>(), new ArrayList<String>()  ),
            new User("5", "camille", "gaillot", new ArrayList<String>(), new ArrayList<String>()  ),
            new User("6", "pauline", "gaillot", new ArrayList<String>(), new ArrayList<String>() )
            );

    static List<String> GenerateDummyFriends(User user) {
        Random random = new Random();
        List<User> userList = mUsers;
        List<String> dummyFriends = Arrays.asList();
        // delete the user from the friend list
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getuId() == user.getuId()){
                userList.remove(i);
            }
        }
        int listSize = (random.nextInt(1 - (userList.size() -1)) + 1);
        for (int i = 0; i < listSize; i++) {
            int dumRandFriendIndex = random.nextInt(userList.size());
            dummyFriends.add(userList.get(dumRandFriendIndex).getuId());
            userList.remove(dumRandFriendIndex);
        }
        return dummyFriends;
    };

}
