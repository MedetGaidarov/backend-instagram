package com.example.serverinstagram.domain.comment.service;


import com.example.serverinstagram.domain.comment.model.Comment;
import com.example.serverinstagram.domain.comment.repository.CommentRepository;
import com.example.serverinstagram.domain.post.model.Post;
import com.example.serverinstagram.domain.post.repository.PostRepository;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.repository.UserRepository;
import com.example.serverinstagram.ui.dto.post.comment.CommentDto;
import com.example.serverinstagram.ui.dto.post.comment.request.CommentRequest;
import com.example.serverinstagram.ui.dto.post.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public CommentResponse getAllCommentsByPostId(Long postId ) {
       List<CommentDto> comments =  commentRepository.findAllByPostId(postId).stream().map(
               comment ->
                    new CommentDto(comment.getBody(), comment.getUser().getId(), comment.getPost().getId())

       ).collect(Collectors.toList());

       return CommentResponse.builder().comments(comments).build();
    }

    @Override
    public Comment saveComment(CommentRequest commentRequest) {
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow();
        User user = userRepository.findById(commentRequest.getUserId()).orElseThrow();
        Comment comment = Comment.builder().post(post).user(user).body(commentRequest.getBody()).build();
        return commentRepository.save(comment);
    }
}
