package com.example.serverinstagram.ui.dto.post.response;


import com.example.serverinstagram.ui.dto.user.UserSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String description;
    private String imagePath;
    private UserSummary createdBy;
    private Instant creationDateTime;
}
