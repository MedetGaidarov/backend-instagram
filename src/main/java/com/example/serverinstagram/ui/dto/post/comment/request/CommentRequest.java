package com.example.serverinstagram.ui.dto.post.comment.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    Long userId;
    Long postId;
    String body;

}
