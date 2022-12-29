package com.example.serverinstagram.ui.controller.follow;


import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.follow.model.Follow;
import com.example.serverinstagram.domain.follow.service.FollowService;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.ui.dto.DefaultResponseDto;
import com.example.serverinstagram.ui.dto.follow.request.FollowRequestDto;
import com.example.serverinstagram.ui.dto.follow.response.FollowResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("follow")
public class FollowController {


    private final FollowService followService;
    private final UserRepository userRepository;



    @PostMapping()
    public ResponseEntity<Object> follow(@RequestBody FollowRequestDto followRequestDto)
    {

        try
        {
            Follow follow = new Follow();
            UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<User> follower = userRepository.findById(currentUser.getId());
            Optional<User> following = userRepository.findByUsername(followRequestDto.getFollowingUsername());

            if(follower.isPresent() && following.isPresent())
            {
                follow.setFollower(follower.get());
                follow.setFollowing(following.get());
                FollowResponseDto followResponseDto =  followService.follow(follow);
                return ResponseEntity.ok().body(new DefaultResponseDto("Success", "Followed",followResponseDto));
            }
            return ResponseEntity.ok().body(new DefaultResponseDto("Fault", "Not followed", ""));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new DefaultResponseDto("Fault", "ERERER", e.getMessage()));
        }
    }

    @GetMapping("followings")
    public ResponseEntity<Object> getFollowings()
    {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(followService.getUserFollowingById(currentUser.getId()));
    }

}
