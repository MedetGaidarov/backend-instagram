package com.example.serverinstagram.domain.follow.service;


import com.example.serverinstagram.domain.follow.model.Follow;
import com.example.serverinstagram.ui.dto.follow.response.FollowListResponseDto;
import com.example.serverinstagram.ui.dto.follow.response.FollowResponseDto;


public interface FollowService {
    FollowListResponseDto getUserFollowingById(Long userId);
    FollowListResponseDto getUserFollowersById(Long userId);
    FollowResponseDto follow(Follow follow);

}
