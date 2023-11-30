package csu.krystal.recommsys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import csu.krystal.recommsys.dto.LoginRequest;
import csu.krystal.recommsys.entity.Admin;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.mapper.AdminMapper;
import csu.krystal.recommsys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;


    public Admin login(LoginRequest request) {
        // 根据用户名查询用户信息
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", request.getUsername());
        Admin admin = adminMapper.selectOne(queryWrapper);

        // 如果用户存在且密码匹配，返回用户信息，否则返回null
        if (admin != null && admin.getPassword().equals(request.getPassword())) {
            return admin;
        }
        return null;
    }

    public User userLogin(LoginRequest request) {
        // 根据用户名查询用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", request.getUsername());
        User user = userMapper.selectOne(queryWrapper);

        // 如果用户存在且密码匹配，返回用户信息，否则返回null
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return user;
        }
        return null;
    }



}