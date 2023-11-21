package csu.krystal.recommsys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import csu.krystal.recommsys.entity.Admin;
import csu.krystal.recommsys.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SampleTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testSelectAllAdmins() {
        // 使用 MyBatis Plus 的 QueryWrapper 构建查询条件，这里表示查询全部记录
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();

        // 执行查询操作
        List<Admin> adminList = adminMapper.selectList(queryWrapper);

        // 输出查询结果
        for (Admin admin : adminList) {
            System.out.println(admin);
        }
    }

}
