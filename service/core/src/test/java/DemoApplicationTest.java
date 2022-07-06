import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.springzym.core.CoreApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * mybatis-plus 代码生成器
 * @author springzym
 */
@SpringBootTest(classes = CoreApplication.class)
class DemoApplicationTests {

//    @Test
    public void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://121.37.217.252:3306/TUDB?useUnicode=true&characterEncoding=utf8&useSSL=false",
                        "root",
                        "zxc001")
                .globalConfig(builder -> {
                    builder.author("springzym") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\IDEA_work\\TyproUpload\\Demo\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.springzym") // 设置父包名
                            .mapper("mapper") // 设置生成的Mapper类存放路径
//                        .moduleName("dao") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\IDEA_work\\TyproUpload\\Demo\\src\\main\\java\\com\\springzym\\mapper")); // 设置mapperXml生成路径
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
