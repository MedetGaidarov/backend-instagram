package com.example.serverinstagram.infrastructure.mapper;

import com.example.serverinstagram.domain.post.model.Post;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.ui.dto.post.response.PostResponseDto;
import com.example.serverinstagram.ui.dto.user.UserSummary;

public class ModelMapper {
    public static PostResponseDto mapPostToPostResponse(Post post, User author) {
        PostResponseDto postResponse = new PostResponseDto();

        postResponse.setId(post.getId());
        postResponse.setDescription(post.getDescription());
        postResponse.setCreationDateTime(post.getCreatedAt());
        postResponse.setImagePath(post.getImagePath());

        UserSummary userSummary = new UserSummary(author.getId(), author.getUsername(), author.getName(), author.getImagePath());
        postResponse.setCreatedBy(userSummary);
        return postResponse;
    }

    public static UserSummary mapUsersToUserSummaries(User user) {
        UserSummary userSummary = new UserSummary();

        userSummary.setId(user.getId());
        userSummary.setName(user.getName());
        userSummary.setImagePath(user.getImagePath());
        userSummary.setUsername(user.getUsername());

        return userSummary;
    }

}
