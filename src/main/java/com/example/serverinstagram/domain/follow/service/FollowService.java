package com.example.serverinstagram.domain.follow.service;


import com.example.serverinstagram.ui.dto.follow.response.FollowListResponseDto;


public interface FollowService {
    FollowListResponseDto getUserFollowingById(Long userId);
    FollowListResponseDto getUserFollowersById(Long userId);

}
