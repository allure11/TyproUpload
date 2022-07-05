import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.zking.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {

    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://121.37.217.252:3306/TUDB?useUnicode=true&characterEncoding=utf8&useSSL=false",
                        "root",
                        "zxc001")
                .globalConfig(builder -> {
                    builder.author("zym") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\IDEA_work\\TyproUpload\\Demo\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zking") // 设置父包名
                            .mapper("mapper") // 设置生成的Mapper类存放路径
//                        .moduleName("dao") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\IDEA_work\\TyproUpload\\Demo\\src\\main\\java\\com\\zking\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tb_file") // 设置需要生成的表名
                            .addTablePrefix("tb_")
                            .serviceBuilder()
                            .formatServiceFileName("%sService"); // 设置过滤表前缀
                })
                .execute();
    }
}
