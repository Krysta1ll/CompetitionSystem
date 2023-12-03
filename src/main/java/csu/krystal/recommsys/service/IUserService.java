package csu.krystal.recommsys.service;

import csu.krystal.recommsys.dto.LoginRequest;
import csu.krystal.recommsys.dto.RegisterRequest;
import csu.krystal.recommsys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import csu.krystal.recommsys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
public interface IUserService extends IService<User> {

    User selectUserById(int id);

    User selectUserByName(String username);

    List<User> getUserList();

    boolean login(LoginRequest loginDto);

    boolean register(RegisterRequest registerDto);

    boolean updateUserInfoById(int id, User user);
}
