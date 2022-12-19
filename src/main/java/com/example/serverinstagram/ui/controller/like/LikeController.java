package com.example.serverinstagram.ui.controller.like;


import com.example.serverinstagram.domain.likes.service.LikeService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("like")
public class LikeController {

    public final LikeService likeService;




}
