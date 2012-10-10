package com.site2go.services.impl;

import com.site2go.dao.repositories.UserRepository;
import com.site2go.dto.User;
import com.site2go.services.UserService;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        throw new NotImplementedException();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
