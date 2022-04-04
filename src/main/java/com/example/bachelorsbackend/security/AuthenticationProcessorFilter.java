package com.example.bachelorsbackend.security;

import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.models.UserRole;
import com.example.bachelorsbackend.repositories.IUserRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationProcessorFilter extends OncePerRequestFilter {
    private final IUserRepository userRepository;

    public AuthenticationProcessorFilter(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User registeredUser = userRepository.findByEmail(jwt.getClaim("email"));
        User user = getUser(registeredUser, jwt);
        updateUser(user, jwt);
        changeSecurityContextAuthentication(new UserJwtAuthenticationToken(jwt, user), request);
        filterChain.doFilter(request, response);
    }

    private User getUser(User registeredUser, Jwt jwt) {
        if (registeredUser != null)
            return registeredUser;
        User user = new User();
        user.setEmail(jwt.getClaim("email"));
        user.setRole(UserRole.ROLE_USER);
        return user;
    }

    private void updateUser(User user, Jwt jwt) {
        boolean needsUpdate = false;
        if (!jwt.getClaim("given_name").equals(user.getFirstName())) {
            needsUpdate = true;
            user.setFirstName(jwt.getClaim("given_name"));
        }
        if (!jwt.getClaim("family_name").equals(user.getLastName())) {
            needsUpdate = true;
            user.setLastName(jwt.getClaim("family_name"));
        }
        if (needsUpdate) {
            userRepository.save(user);
        }
    }

    private void changeSecurityContextAuthentication(UserJwtAuthenticationToken newAuthentication, HttpServletRequest request) {
        newAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(newAuthentication);
        SecurityContextHolder.setContext(newContext);
    }
}
