package com.jsm.user.service.services;

import com.jsm.user.service.entities.User;

import java.util.List;

public interface UserService {
    // user operations here

    // create
    User saveUser(User user);

    // get all user
    List<User> getAllUser();

    // get single user of given userId
    User getUser(String userId);

    // TODO: delete
    // TODO: update

}
