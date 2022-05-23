package com.example.bachelorsbackend.controllers;

import com.example.bachelorsbackend.models.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("1")
    public String test2(@AuthenticationPrincipal User user) {
        return user.getUsername();
    }

    @GetMapping("2")
    @Secured("ROLE_USER")
    public String test3(@AuthenticationPrincipal User user) {
        return user.getUsername();
    }

    @GetMapping("3")
    @Secured("ROLE_DEVELOPER")
    public String test4(@AuthenticationPrincipal User user) {
        return user.getUsername();
    }
}
