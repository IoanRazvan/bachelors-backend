package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserRole;
import com.example.bachelorsbackend.security.UserJwtAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class ServiceUtils {
    private ServiceUtils() {
    }

    static User getLoggedInUser() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (NullPointerException e) {
            return null;
        }
    }

    static UserJwtAuthenticationToken getAuthentication() {
        try {
            return (UserJwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    static boolean hasDeveloperRole(UserJwtAuthenticationToken authenticationToken) {
        return authenticationToken.getAuthorities().contains(UserRole.ROLE_DEVELOPER);
    }
}
