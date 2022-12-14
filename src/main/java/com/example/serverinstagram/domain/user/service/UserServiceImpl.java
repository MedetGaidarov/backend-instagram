package com.example.serverinstagram.domain.user.service;

import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.infrastructure.service.FileStorageService;
import com.example.serverinstagram.ui.dto.DefaultResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileStorageService fileStorageService;
    private String defaultImagePath = "http://localhost:8001/posts/images/default_avatar.jpg";

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public User encodeAndSave(User user) {
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        return userRepository.save(user);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<?> updateUserPicture(UserPrincipal currentUser, MultipartFile image) {
        String fileName = fileStorageService.storeFile(image);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("posts/images/")
                .path(fileName)
                .toUriString();

        User userToUpdate = userRepository.findById(currentUser.getId()).orElseThrow();
        if (fileDownloadUri == null)
            userToUpdate.setImagePath(defaultImagePath);
        else
            userToUpdate.setImagePath(fileDownloadUri);
        userRepository.save(userToUpdate);

        return ResponseEntity.ok().body(new DefaultResponseDto("Success", "Image was updated", fileDownloadUri));
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
