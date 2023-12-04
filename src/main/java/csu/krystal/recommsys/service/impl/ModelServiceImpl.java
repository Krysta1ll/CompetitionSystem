package csu.krystal.recommsys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import csu.krystal.recommsys.entity.Model;
import csu.krystal.recommsys.mapper.ModelMapper;
import csu.krystal.recommsys.service.IModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<Model> selectBySeed(String seed) {
        return modelMapper.selectList(new QueryWrapper<Model>().eq("s", seed));
    }

    /**
     * 插入modelist
     * @param modelList
     * @return 插入后填充完 id 的modelList
     */
    @Override
    public List<Model> insertModelList(List<Model> modelList) {
        for(Model model: modelList){
            modelMapper.insert(model);
        }
        List<Model> res = modelMapper.selectList(new QueryWrapper<Model>().eq("s", modelList.get(0).getS()));
        return res;
    }

    @Override
    public List<Model> getAllModelInMemory() {

        Set<String> keys = redisTemplate.keys("model:*");

        //缓存中不存在 model:**
        if(keys == null){
            return null;
        }

        List<Model> allModels = new ArrayList<>();

        List<List<Model>> modelLists = redisTemplate.opsForValue().multiGet(keys);
        if(modelLists == null)
            return null;
        for(List<Model> modelList: modelLists){
            allModels.addAll(modelList);
        }

        return allModels;
    }
}
