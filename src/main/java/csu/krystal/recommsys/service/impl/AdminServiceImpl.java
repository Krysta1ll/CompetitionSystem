package csu.krystal.recommsys.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import csu.krystal.recommsys.mapper.AdminMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IService<Admin> {

}
