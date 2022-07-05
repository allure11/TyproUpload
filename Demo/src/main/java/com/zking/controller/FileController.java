package com.zking.controller;

import com.zking.entity.MyFile;
import com.zking.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zym
 * @since 2022-07-05
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileServiceImpl;

    @GetMapping("/getAll")
    public List getAll(){
        List<MyFile> list = fileServiceImpl.list();
        return list;
    }
}
