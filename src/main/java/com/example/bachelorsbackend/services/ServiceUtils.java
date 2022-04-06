package com.example.bachelorsbackend.services;

import com.example.bachelorsbackend.models.User;
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
}
