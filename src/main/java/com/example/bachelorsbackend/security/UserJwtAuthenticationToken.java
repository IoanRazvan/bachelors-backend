package com.example.bachelorsbackend.security;

import com.example.bachelorsbackend.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class UserJwtAuthenticationToken extends JwtAuthenticationToken {
    private User user;

    public UserJwtAuthenticationToken(Jwt jwt, User user, Converter<Jwt, Collection<GrantedAuthority>> jwtCollectionConverter) {
        super(jwt, jwtCollectionConverter.convert(jwt));
        this.user = user;
    }

    @Override
    public User getPrincipal() {
        return user;
    }
}
