package com.example.serverinstagram.domain.user.service;


import com.example.serverinstagram.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();

    Optional<User> findByUsername(String username);
}
