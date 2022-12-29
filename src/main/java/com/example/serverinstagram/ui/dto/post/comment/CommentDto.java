package com.example.serverinstagram.ui.dto.post.comment;


import com.example.serverinstagram.ui.dto.user.UserSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    String body;
    UserSummary createdBy;
    Long postId;
}
