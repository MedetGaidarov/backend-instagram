package com.example.serverinstagram.ui.controller.like;


import com.example.serverinstagram.domain.likes.service.LikeService;
import com.example.serverinstagram.ui.dto.like.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("like")
public class LikeController {

    public final LikeService likeService;


    @PostMapping()
    public ResponseEntity<Object> doLike(@PathVariable(value = "postId") Long postId)
    {

    }




}
