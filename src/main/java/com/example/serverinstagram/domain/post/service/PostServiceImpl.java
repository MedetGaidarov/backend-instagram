package com.example.serverinstagram.domain.post.service;


import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.post.model.Post;
import com.example.serverinstagram.ui.dto.PagedResponse;
import com.example.serverinstagram.ui.dto.post.response.PostResponseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements  PostService{
    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public PagedResponse<PostResponseDto> getAllPosts(int page, int size, UserPrincipal currentUser) {

        // TODO: get all followed users

        return null;
    }
}
