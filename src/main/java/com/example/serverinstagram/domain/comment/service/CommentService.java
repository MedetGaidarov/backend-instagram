package com.example.serverinstagram.domain.comment.service;


import com.example.serverinstagram.domain.comment.model.Comment;
import com.example.serverinstagram.ui.dto.post.comment.request.CommentRequest;
import com.example.serverinstagram.ui.dto.post.comment.response.CommentResponse;

public interface CommentService {
    CommentResponse getAllCommentsByPostId(Long postId);


    Comment saveComment(CommentRequest commentRequest);

}
