package csu.krystal.recommsys.service.impl;

import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.mapper.UserMapper;
import csu.krystal.recommsys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
