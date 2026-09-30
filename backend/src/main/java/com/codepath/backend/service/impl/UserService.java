package com.codepath.backend.service;
import com.codepath.backend.entity.User;

public interface UserService {
    User register(User user);
    User login(String email, String password);
}