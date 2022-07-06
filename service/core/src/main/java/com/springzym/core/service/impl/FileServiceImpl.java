package com.springzym.core.service.impl;

import com.springzym.core.entity.MyFile;
import com.springzym.core.mapper.FileMapper;
import com.springzym.core.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springzym
 * @since 2022-07-05
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, MyFile> implements FileService {

    /**
     * 日志对象
     */
    private static final Logger logger =  LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 文件上传
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean uploadFile(MultipartFile file) throws IOException {

        // 获取当前项目根目录
        Resource resource = new ClassPathResource("/pages");
        String location = resource.getFile().getAbsolutePath();

        // 保存文件上传信息
        MyFile myFile = new MyFile();
        myFile.setFilename(file.getOriginalFilename());
        save(myFile);

        // location 为配置文件配置的路径，根据文件 id 拼接文件名
        File dest = new File(location, "/" + myFile.getId() + ".html");
        //保存文件
        file.transferTo(dest);

        logger.info(myFile.getFilename() + " --- >" + dest.getName());
        return true;
    }
}
