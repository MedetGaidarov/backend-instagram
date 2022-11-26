package com.example.serverinstagram.ui.controller.follow;

import com.example.serverinstagram.domain.follow.model.Follow;
import com.example.serverinstagram.domain.follow.service.FollowService;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.service.UserService;
import com.example.serverinstagram.ui.dto.follow.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("follow")
public class FollowController {

    private final UserService userService;
    private final FollowService followService;

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody FollowDto followDto) {
        try {
            Long followerId = userService.findByUsername(followDto.getFollowerUsername()).get().getId();
            Long followedId = userService.findByUsername(followDto.getFollowedUsername()).get().getId();


            Follow follow = new Follow();
            follow.setFollowerId(followerId);
            follow.setFollowedId(followedId);
            followService.save(follow);


            return ResponseEntity.ok().body("Saved");

        }catch (Exception e)
        {
            return ResponseEntity.ok("Not saved");
        }


    }
}
