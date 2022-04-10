package com.example.bachelorsbackend.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_USER,
    ROLE_DEVELOPER,
    ROLE_DEFAULT;

    @Override
    public String getAuthority() {
        return toString();
    }
}
