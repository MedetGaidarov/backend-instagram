package com.example.serverinstagram.ui.controller.auth;

import com.example.serverinstagram.domain.user.model.User;
import com.example.serverinstagram.infrastructure.util.JwtTokenUtil;
import com.example.serverinstagram.ui.dto.auth.request.AuthRequestDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("login")
    public ResponseEntity<Object> authentication(@RequestBody AuthRequestDto authRequestDto) {
        try {
            logger.info("username {}, password {}", authRequestDto.getUsername(), authRequestDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), new User(authRequestDto.getUsername(), authRequestDto.getPassword())));

            String principal = (String) authentication.getPrincipal();
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(principal, authentication.getAuthorities())).body(jwtTokenUtil.generateRefreshToken(principal));
        } catch (AuthenticationException ex) {
            logger.error("Authentication exception: " + ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            logger.error("Error in LOGIN: {}", e.getMessage());
            JSONObject response = new JSONObject();
            response.put("message", "Server corrupted by Chaos! We call an Inquisition to handle problems!.");
            return ResponseEntity.internalServerError().body(response);
        }

    }

}
