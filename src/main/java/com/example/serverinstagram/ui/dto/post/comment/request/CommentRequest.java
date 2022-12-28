package com.example.serverinstagram.ui.dto.post.comment.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    Long userId;
    Long postId;
    String body;

}
