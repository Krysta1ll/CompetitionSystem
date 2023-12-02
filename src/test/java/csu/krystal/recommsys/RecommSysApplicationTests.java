package csu.krystal.recommsys;

import csu.krystal.recommsys.entity.Model;
import csu.krystal.recommsys.mapper.ModelMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RecommSysApplicationTests {

    @Autowired
    ModelMapper modelMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void modelMapperTest() {
        List<Model> models = modelMapper.selectList(null);
        System.out.println(models);
    }
}
