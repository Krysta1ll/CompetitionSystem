package csu.krystal.recommsys.controller;


import csu.krystal.recommsys.dto.RegisterRequest;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @GetMapping("/getUserList")
    public List<User> getUserList() {
        return userService.getAllUsers();
    }


}
