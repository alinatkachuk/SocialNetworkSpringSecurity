package com.alinatkachuk.socialnetwork.service;

import com.alinatkachuk.socialnetwork.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Override
    public String insertUser(User user) {
        return null;
    }

    @Override
    public String fetchUserToken(String user) {
        return null;
    }

    @Override
    public boolean alreadyRegistered(String email) {
        return false;
    }
}
