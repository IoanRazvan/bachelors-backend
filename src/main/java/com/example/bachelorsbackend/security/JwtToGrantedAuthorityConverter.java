package com.example.bachelorsbackend.security;

import com.example.bachelorsbackend.models.UserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtToGrantedAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = source.getClaim("realm_access");
        if (realmAccess == null || realmAccess.isEmpty())
            return Collections.emptyList();
        List<String> roles = (List<String>) realmAccess.get("roles");
        return roles.stream().map(roleTitle -> {
            switch (roleTitle) {
                case "DEVELOPER":
                    return UserRole.ROLE_DEVELOPER;
                case "USER":
                    return UserRole.ROLE_USER;
                default:
                    return UserRole.ROLE_DEFAULT;
            }
        }).distinct().collect(Collectors.toList());
    }
}
