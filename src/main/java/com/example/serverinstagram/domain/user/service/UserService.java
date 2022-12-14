package com.example.serverinstagram.domain.user.service;


import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<User> findByUsername(String username);

    User encodeAndSave(User user);

    ResponseEntity<?> updateUserPicture(UserPrincipal currentUser, MultipartFile image);
}
