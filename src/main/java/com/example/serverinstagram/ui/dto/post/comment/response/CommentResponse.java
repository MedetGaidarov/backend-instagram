package com.example.serverinstagram.ui.dto.post.comment.response;


import com.example.serverinstagram.ui.dto.post.comment.CommentDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
    List<CommentDto> comments;
    Long postId;

}
