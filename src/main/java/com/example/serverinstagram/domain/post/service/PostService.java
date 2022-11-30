package com.example.serverinstagram.domain.post.service;

import com.example.serverinstagram.domain.post.model.Post;

import java.util.Optional;

public interface PostService {
    Optional<Post> findById(Long id);

}
