package csu.krystal.recommsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import csu.krystal.recommsys.entity.Model;
import csu.krystal.recommsys.mapper.ModelMapper;
import csu.krystal.recommsys.service.IModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements IModelService {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Model> selectBySeed(String seed) {
        return modelMapper.selectList(new QueryWrapper<Model>().eq("s", seed));
    }
}
