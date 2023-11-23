package csu.krystal.recommsys.controller;

import csu.krystal.recommsys.dto.LoginRequest;
import csu.krystal.recommsys.entity.Admin;
import csu.krystal.recommsys.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Admin login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}