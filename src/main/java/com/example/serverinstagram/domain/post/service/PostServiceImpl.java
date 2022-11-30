package com.example.serverinstagram.domain.post.service;


import com.example.serverinstagram.domain.post.model.Post;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements  PostService{
    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }
}
