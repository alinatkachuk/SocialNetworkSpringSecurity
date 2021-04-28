package com.alinatkachuk.socialnetwork.service;

import com.alinatkachuk.socialnetwork.model.User;

public interface UserService {

    String insertUser(User user);

    String fetchUserToken(String user);

    boolean alreadyRegistered(String email);

}
