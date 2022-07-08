package com.springzym.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springzym.core.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author springzym
 * @since 2022-07-07
 */
public interface UserMapper extends BaseMapper<User> {

    User getIdAndNameByTripartiteId(String tripartiteId);
}
