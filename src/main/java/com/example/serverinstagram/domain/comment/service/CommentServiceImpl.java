package com.example.serverinstagram.domain.comment.service;


import com.example.serverinstagram.domain.comment.model.Comment;
import com.example.serverinstagram.domain.comment.repository.CommentRepository;
import com.example.serverinstagram.domain.post.model.Post;
import com.example.serverinstagram.domain.post.repository.PostRepository;
import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.ui.dto.post.comment.request.CommentRequest;
import com.example.serverinstagram.ui.dto.post.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public CommentResponse getAllCommentsByPostId(Long postId ) {
       List<Comment> comments =  commentRepository.findAllByPostId(postId);
       return CommentResponse.builder().comments(comments).build();
    }

    @Override
    public Comment saveComment(CommentRequest commentRequest) {
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow();
    }


}
