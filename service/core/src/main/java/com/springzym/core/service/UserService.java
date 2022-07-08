package com.springzym.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springzym.core.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author springzym
 * @since 2022-07-07
 */
public interface UserService extends IService<User> {

    /**
     * 通过三方id获取用户id和用户名
     * @param tripartiteId 第三方id
     * @return
     */
    User getIdAndNameByTripartiteId(String tripartiteId);
}
