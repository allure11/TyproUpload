package com.zking.controller;


import com.zking.entity.MyFile;
import com.zking.service.FileService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("")
public class UserController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 项目根路径
     */
    private String location;


    private FileService fileServiceImpl;

    @Autowired
    public UserController(FileService fileServiceImpl){
        this.fileServiceImpl = fileServiceImpl;

    }


    @RequestMapping("index")
    public String index() {
        return "redirect:/index.html";
    }

    /**
     * @param file 文件
     * @param redirectAttributes 重定向之后还能带参数跳转
     * @return
     * @throws IOException
     */
    @PostMapping("updateFile")
    public String updateFile(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        // 获取当前项目根目录
        Resource resource = new ClassPathResource("/pages");
        location = resource.getFile().getAbsolutePath();
        logger.info(location);

        //获取上传文件名
        String path = "/" + file.getOriginalFilename();
        //location为配置文件配置的路径
        File dest = new File(location, path);

        //文件上传到准备好的文件
        file.transferTo(dest);

        MyFile myFile = new MyFile();
        myFile.setFilename(file.getOriginalFilename());
        fileServiceImpl.save(myFile);
        logger.info("---->"+path);
        redirectAttributes.addFlashAttribute("path", path);
        return "redirect:index";
    }
}
