package com.springzym.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springzym.core.entity.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author springzym
 * @since 2022-07-05
 */
public interface FileService extends IService<MyFile> {

    boolean uploadFile(MultipartFile file) throws IOException;
}
