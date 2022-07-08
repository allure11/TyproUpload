package com.springzym.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springzym.core.entity.User;
import com.springzym.core.mapper.UserMapper;
import com.springzym.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author springzym
 * @since 2022-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 通过三方id获取用户id和用户名
     * @param tripartiteId 第三方id
     * @return
     */
    @Override
    public User getIdAndNameByTripartiteId(String tripartiteId) {
        return userMapper.getIdAndNameByTripartiteId(tripartiteId);
    }
}
