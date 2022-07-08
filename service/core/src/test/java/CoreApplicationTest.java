import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.springzym.core.CoreApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * mybatis-plus 代码生成器
 * @author springzym
 */
@SpringBootTest(classes = CoreApplication.class)
public class CoreApplicationTest {

    @Test
    public void context(){
        FastAutoGenerator.create("jdbc:mysql://121.37.217.252:3306/TUDB?useUnicode=true&characterEncoding=utf8&useSSL=false",
                        "root",
                        "zxc001")
                .globalConfig(builder -> {
                    builder.author("springzym") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\IDEA_work\\TyporaUpload\\service\\core\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.springzym.core") // 设置父包名
                            .mapper("mapper") // 设置生成的Mapper类存放路径
//                            .moduleName("core") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\IDEA_work\\TyporaUpload\\service\\core\\src\\main\\java\\com\\springzym\\core\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user_association") // 设置需要生成的表名
//                            .addTablePrefix("tb_")
                            .serviceBuilder()
                            .formatServiceFileName("%sService"); // 设置过滤表前缀
                })
                .execute();
    }

}
