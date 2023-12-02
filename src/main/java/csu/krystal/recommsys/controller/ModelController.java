package csu.krystal.recommsys.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import csu.krystal.recommsys.common.compkey.CompKey;
import csu.krystal.recommsys.common.util.ResponseVo;
import csu.krystal.recommsys.entity.Model;
import csu.krystal.recommsys.entity.User;
import csu.krystal.recommsys.service.IModelService;
import csu.krystal.recommsys.service.IRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Krystal
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/models")
@Api(tags = "关键字相关接口",description = "")
public class ModelController {
    @Autowired
    private IModelService modelService;

    @Autowired
    private IRecordService recordService;

    @Autowired
    RedisTemplate redisTemplate;

    long expirTime = 120; // 设置过期时间为120分钟

//    // JSON工具, 手动序列化可以用到 如下
//    private static final ObjectMapper mapper = new ObjectMapper();
//Person person = new Person("我是一棵卷心菜", 21);
//    //  手动序列化
//    String json = mapper.writeValueAsString(person);
//        redisTemplate.opsForValue().set("person", json);
//    String personJson = redisTemplate.opsForValue().get("person");
//    // 反序列化
//    Person person1 = mapper.readValue(personJson, Person.class);
//        System.out.println(person1);

    @GetMapping
    @ApiOperation(notes = "查询关键字列表",value = "返回缓存中所有的关键字数据")
    public ResponseVo<List<Model>> getModelsInMemory() {
        return null;
    }

    @GetMapping("/{seed}")
    @ApiOperation(notes = "查询关键字model",value = "查找缓存中是否有该关键字,从缓存中取出后返回,如果没有就去数据库中搜索,还没有则调用compkey算法搜索,将结果存入缓存并返回数据")
    public ResponseVo<List<Model>> searchBySeed(@PathVariable String seed){

        //在缓存中查找该关键字
        List<Model> modelList = (List<Model>) redisTemplate.opsForValue().get(seed);
        if(modelList == null){
            //缓存中没有查找到该关键字,试着从数据库中查找
            modelList = modelService.selectBySeed(seed);
            if(modelList.isEmpty()){
                 //在数据库model表中也没找到该关键字, 这时需要调用算法了
                // compkey算法中, 第一个参数为种子关键字, 第二个参数为指定 联合关键字数量
                modelList = CompKey.compKey(seed, 3);
            }
        }

        //todo  记录这次搜索记录, 包括搜索时间, 搜索人
        return ResponseVo.success("搜索成功", modelList);
    }

}
