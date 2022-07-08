package com.springzym.core.service.impl;

import com.springzym.core.entity.GithubUser;
import com.springzym.core.mapper.GithubUserMapper;
import com.springzym.core.service.GithubUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springzym
 * @since 2022-07-08
 */
@Service
public class GithubUserServiceImpl extends ServiceImpl<GithubUserMapper, GithubUser> implements GithubUserService {

}
