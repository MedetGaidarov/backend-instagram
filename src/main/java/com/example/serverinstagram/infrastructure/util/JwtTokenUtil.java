package com.example.serverinstagram.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.serverinstagram.configuration.security.user.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String signingKey;


    @Value("${jwt.issuer}")
    private String jwtIssuer;


    @Value("${jwt.refresh-secret}")
    private String refreshSigningKey;


    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);




    public String generateAccessToken(UserPrincipal principal, Collection<? extends GrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(signingKey.getBytes());
        return JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 40 * 60 * 1000))
                .withIssuer(jwtIssuer)
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);


    }


    public String generateRefreshToken(UserPrincipal principal) {
        Algorithm algorithm = Algorithm.HMAC256(refreshSigningKey.getBytes());
        return JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(jwtIssuer)
                .sign(algorithm);


    }

}
