package csu.krystal.recommsys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import csu.krystal.recommsys.dto.RegisterRequest;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User register(RegisterRequest registerRequest) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", registerRequest.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            return null;
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setEmail(registerRequest.getEmail());
        userMapper.insert(newUser);
        return newUser;
    }


    public List<User> getAllUsers() {
        return userMapper.selectList(null); // 这里使用了MyBatis Plus的selectList方法
    }
}
