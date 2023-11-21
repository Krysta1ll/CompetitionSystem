package csu.krystal.recommsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("csu.krystal.recommsys.mapper")
public class RecommSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommSysApplication.class, args);
    }


}
