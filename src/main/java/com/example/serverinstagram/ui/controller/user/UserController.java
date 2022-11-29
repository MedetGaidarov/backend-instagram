package com.example.serverinstagram.ui.controller.user;


import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import com.example.serverinstagram.domain.user.model.Role;
import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.domain.user.repository.RoleRepository;
import com.example.serverinstagram.domain.user.service.UserService;
import com.example.serverinstagram.ui.controller.auth.AuthenticationController;
import com.example.serverinstagram.ui.dto.user.role.RoleDto;
import com.example.serverinstagram.ui.dto.user.UserDto;
import com.example.serverinstagram.ui.dto.user.UserSummary;
import com.example.serverinstagram.ui.dto.user.role.UserRoleDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    private final UserService userService;
    private final RoleRepository roleRepository;

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody UserDto userDto)
    {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return ResponseEntity.ok().body(userService.encodeAndSave(user));
    }


    @GetMapping("/me")
    public UserSummary getCurrentUser(@AuthenticationPrincipal UserPrincipal currentUser)
    {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getImagePath());
    }


    @PostMapping("role")
    public ResponseEntity<Object> saveRole(@RequestBody RoleDto roleDto)
    {
        Role role = new Role();
        role.setName(roleDto.getName());
        return ResponseEntity.ok().body(roleRepository.save(role));
    }

    @PostMapping("/setRole")
    public ResponseEntity<Object> setRole(@RequestBody UserRoleDto userRoleDto)
    {
        Optional<Role> role = roleRepository.findByName(userRoleDto.getRoleName());
        if(role.isPresent())
        {
            Optional<User> user = userService.findByUsername(userRoleDto.getUsername());
            if(user.isPresent())
            {

                Collection<Role> roles = new ArrayList<>();
                roles.add(role.get());
                user.get().setRoles(roles);
                userService.save(user.get());
                logger.info("User with username {} given role {} ", userRoleDto.getUsername(), userRoleDto.getRoleName());
                return ResponseEntity.ok(String.format("User with username %s saved role %s", userRoleDto.getUsername(), userRoleDto.getRoleName()));
            }
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.badRequest().body("Role not found");
    }
}
