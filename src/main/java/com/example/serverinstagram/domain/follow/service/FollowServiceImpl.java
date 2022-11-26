package com.example.serverinstagram.domain.follow.service;

import com.example.serverinstagram.domain.follow.model.Follow;
import com.example.serverinstagram.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;

    @Override
    public void save(Follow follow) {
        followRepository.save(follow);
    }
}
