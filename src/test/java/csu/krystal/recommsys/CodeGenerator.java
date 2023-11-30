package csu.krystal.recommsys;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(new DataSourceConfig.Builder(
                        "jdbc:mysql://localhost:3306/compkey?useUnicode=true&useSSL=false&characterEncoding=utf8",
                        "root",
                        "tassadarwty2003"))
                .globalConfig(builder -> {
                    builder.author("Krystal") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("csu.krystal.recommsys") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper")); // 设置mapper.xml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("admin", "model", "record", "score", "user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder().enableLombok() // 开启Lombok模型
                            .controllerBuilder().enableRestStyle(); // 开启生成@RestController控制器
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板
                .execute();
    }
}
