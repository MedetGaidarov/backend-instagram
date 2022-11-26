package com.example.serverinstagram.ui.controller.user;


import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.service.UserService;
import com.example.serverinstagram.ui.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {


    private final UserService userService;


    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody UserDto userDto)
    {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return ResponseEntity.ok().body(userService.save(user));

    }
}
