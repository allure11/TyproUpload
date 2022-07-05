package com.zking.service.impl;

import com.zking.entity.MyFile;
import com.zking.mapper.FileMapper;
import com.zking.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zym
 * @since 2022-07-05
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, MyFile> implements FileService {

}
