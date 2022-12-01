package com.example.serverinstagram.domain.follow.service;


import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.ui.dto.follow.response.FollowListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final UserRepository userRepository;


    @Override
    public FollowListResponseDto getUserFollowingById(Long userId) {

        return null;
    }

    @Override
    public FollowListResponseDto getUserFollowersById(Long userId) {
        return null;
    }
}
