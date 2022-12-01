package com.example.serverinstagram.domain.post.service;

import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.post.model.Post;
import com.example.serverinstagram.ui.dto.PagedResponse;
import com.example.serverinstagram.ui.dto.post.response.PostResponseDto;

import java.util.Optional;

public interface PostService {
    Optional<Post> findById(Long id);
    PagedResponse<PostResponseDto> getAllPosts(int page, int size, UserPrincipal currentUser);
}
