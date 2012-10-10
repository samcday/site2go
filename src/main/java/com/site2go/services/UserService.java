package com.site2go.services;

import com.site2go.dto.User;

public interface UserService {
    public User login(String username, String password);
}
