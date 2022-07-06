package com.springzym.core.controller;

import com.springzym.core.service.FileService;
import com.springzym.util.R;
import com.springzym.exception.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

/**
 * <p>
 *  文件操作前端控制器
 * </p>
 *
 * @author springzym
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 文件相关服务
     */
    private FileService fileServiceImpl;

    /**
     * 项目根路径
     */
    private String location;

    @Autowired
    public FileController(FileService fileServiceImpl) {
        this.fileServiceImpl = fileServiceImpl;
    }

    /**
     * 获取所有文件信息
     * @return
     */
    @GetMapping("/getAll")
    public R getAll(){
        try {
            return R.ok().setData("files", fileServiceImpl.list());
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 上传文件，将文件名改为 id.html 并保存
     * @param file 文件
     * @param redirectAttributes 重定向之后还能带参数跳转
     * @return
     * @throws IOException
     */
    @PostMapping("uploadFile")
    public R uploadFile(MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            if (fileServiceImpl.uploadFile(file)){
                return R.ok("文件上传成功");
            }else {
                return R.error("文件上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e);
        }
    }
}
