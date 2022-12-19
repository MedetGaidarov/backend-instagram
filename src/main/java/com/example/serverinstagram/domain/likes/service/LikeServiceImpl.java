package com.example.serverinstagram.domain.likes.service;


import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.likes.model.Like;
import com.example.serverinstagram.domain.likes.repository.LikeRepository;
import com.example.serverinstagram.domain.post.model.Post;
import com.example.serverinstagram.domain.post.repository.PostRepository;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.ui.dto.like.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl  implements LikeService{


    public final LikeRepository likeRepository;
    public final PostRepository postRepository;
    public final UserRepository userRepository;


    @Override
    public LikeResponse doLike(Long postId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userPrincipal.getId()).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        Like like = Like.builder().post(post).user(user).build();
        likeRepository.save(like);
    }

    @Override
    public LikeResponse doUnlike(Long postId) {

    }
}
