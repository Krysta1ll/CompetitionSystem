package csu.krystal.recommsys;

import csu.krystal.recommsys.common.compkey.CompKey;
import csu.krystal.recommsys.entity.Model;
import csu.krystal.recommsys.mapper.ModelMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class RecommSysApplicationTests {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void modelMapperTest() {
        List<Model> models = modelMapper.selectList(null);
        System.out.println(models);
    }

    @Test
    void compkeyAlogrithm(){
        List<Model> models = CompKey.compKey("周杰伦", 3);
            System.out.println(models);
    }

    @Test
    void redisTemplateTest(){
        // 设置键值对
        redisTemplate.opsForValue().set("key", "value");

// 获取值
        String value = (String) redisTemplate.opsForValue().get("key");

        System.out.println(value);
    }

    @Test
    void midWordTest(){




    }


}
