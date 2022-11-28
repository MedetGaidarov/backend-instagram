package com.example.serverinstagram.ui.dto.user;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    String email;
    String username;
    String password;

}
