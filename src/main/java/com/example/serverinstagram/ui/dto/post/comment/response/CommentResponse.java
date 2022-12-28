package com.example.serverinstagram.ui.dto.post.comment.response;


import com.example.serverinstagram.domain.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
    List<Comment> comments;

}
