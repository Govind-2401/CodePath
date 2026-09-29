package com.codepath.backend.service.impl;

import com.codepath.backend.entity.User;
import com.codepath.backend.repository.UserRepository;
import com.codepath.backend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
}