package com.example.serverinstagram.ui.dto;


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
