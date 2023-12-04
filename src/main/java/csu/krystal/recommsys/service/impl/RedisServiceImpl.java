package csu.krystal.recommsys.service.impl;

import csu.krystal.recommsys.entity.Model;
import csu.krystal.recommsys.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements IRedisService {
    @Autowired
    private RedisTemplate redisTemplate;

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
