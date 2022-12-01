package com.example.serverinstagram.ui.dto.follow.response;


import com.example.serverinstagram.ui.dto.user.UserSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowListResponseDto {
    private List<UserSummary> userSummaryList;
}
