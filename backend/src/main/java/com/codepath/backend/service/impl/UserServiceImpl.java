package com.codepath.backend.service.impl;

import com.codepath.backend.entity.User;
import com.codepath.backend.repository.UserRepository;
import com.codepath.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codepath.backend.exception.EmailAlreadyExistsException;
import com.codepath.backend.exception.InvalidCredentialsException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException(
                        "Invalid email or password"
                ));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        return user;
    }
}