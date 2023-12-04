package csu.krystal.recommsys.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import csu.krystal.recommsys.dto.LoginRequest;
import csu.krystal.recommsys.dto.RegisterRequest;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.mapper.UserMapper;
import csu.krystal.recommsys.common.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectUserByName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }

    @Override
    public boolean login(LoginRequest loginDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginDto.getUsername());
        queryWrapper.eq("password", loginDto.getPassword());
        queryWrapper.eq("role", loginDto.getRole());
        return userMapper.exists(queryWrapper);
    }

    @Override
    public boolean register(RegisterRequest registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setRole(registerDto.getRole());
        user.setEmail(registerDto.getEmail());
        Date nowDate = new Date();
        user.setCreateTime(nowDate);
        user.setUpdateTime(nowDate);

        int count = userMapper.insert(user);
        return count != 0;
    }

    @Override
    public boolean updateUserInfoById(int id, User user) {
        User user1 = userMapper.selectById(id);
        if(user1 != null){
            user.setId(id);
            user.setPassword(user1.getPassword());
            if(userMapper.updateById(user) != 0){
                return true;
            }
        }
        return false;
    }
}
