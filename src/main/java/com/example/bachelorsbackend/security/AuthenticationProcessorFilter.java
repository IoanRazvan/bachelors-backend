package com.example.bachelorsbackend.security;

import com.example.bachelorsbackend.models.User;
import com.example.bachelorsbackend.repositories.IUserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class AuthenticationProcessorFilter extends OncePerRequestFilter {
    private final IUserRepository userRepository;
    private final Converter<Jwt, Collection<GrantedAuthority>> jwtCollectionConverter;

    public AuthenticationProcessorFilter(IUserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtCollectionConverter = new JwtToGrantedAuthorityConverter();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> registeredUser = userRepository.findBySubject(jwt.getSubject());
        User user = registeredUser.orElseGet(() -> createNewUser(jwt));
        changeSecurityContextAuthentication(new UserJwtAuthenticationToken(jwt, user, jwtCollectionConverter), request);
        filterChain.doFilter(request, response);
    }

    private User createNewUser(Jwt jwt) {
        User user = new User();
        user.setSubject(jwt.getSubject());
        user.setUsername(jwt.getClaim("preferred_username"));
        return userRepository.save(user);
    }

    private void changeSecurityContextAuthentication(UserJwtAuthenticationToken newAuthentication, HttpServletRequest request) {
        newAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContext newContext = SecurityContextHolder.createEmptyContext();
        newContext.setAuthentication(newAuthentication);
        SecurityContextHolder.setContext(newContext);
    }
}
