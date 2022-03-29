package com.example.bachelorsbackend.security;

import com.example.bachelorsbackend.models.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collections;

public class UserJwtAuthenticationToken extends JwtAuthenticationToken {
    private User user;

    public UserJwtAuthenticationToken(Jwt jwt, User user) {
        super(jwt, Collections.singletonList(user.getRole()));
        this.user = user;
    }

    @Override
    public User getPrincipal() {
        return user;
    }
}
