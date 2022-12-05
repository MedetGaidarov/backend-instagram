package com.example.serverinstagram.domain.follow.service;


import com.example.serverinstagram.domain.follow.model.Follow;
import com.example.serverinstagram.domain.follow.repository.FollowRepository;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.infrastructure.mapper.ModelMapper;
import com.example.serverinstagram.ui.dto.follow.response.FollowListResponseDto;
import com.example.serverinstagram.ui.dto.follow.response.FollowResponseDto;
import com.example.serverinstagram.ui.dto.user.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final UserRepository userRepository;

    private final FollowRepository followRepository;


    @Override
    public FollowListResponseDto getUserFollowingById(Long userId) {
        List<Follow> followings = followRepository.findAllByFollowerId(userId);
        List<UserSummary> followingsUserSummary = new ArrayList<>();


        List<Long> userIds = followings.stream().map(
                follow -> follow.getFollowing().getId()
        ).collect(Collectors.toList());

        List<User> followingsUsers = userRepository.findByIdIn(userIds);
        followingsUsers.forEach(user -> {
            followingsUserSummary.add(ModelMapper.mapUsersToUserSummaries(user));
        });
        return new FollowListResponseDto(followingsUserSummary);
    }

    @Override
    public FollowListResponseDto getUserFollowersById(Long userId) {
        return null;
    }

    @Override
    public FollowResponseDto follow(Follow follow) {
        try {

            followRepository.save(follow);
            return new FollowResponseDto(
                    String.format("User %s followed to %s", follow.getFollower().getUsername(), follow.getFollowing().getUsername()), true);
        } catch (Exception e) {
            return new FollowResponseDto(e.getMessage(), false);
        }
    }


}
