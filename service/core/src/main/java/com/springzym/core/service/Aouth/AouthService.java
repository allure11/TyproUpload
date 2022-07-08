package com.springzym.core.service.Aouth;

import com.springzym.util.R;

/**
 * 第三方登录服务接口
 */
public interface AouthService {

    /**
     * 发起登录请求
     */
    R login();

    /**
     * 获取 token 信息
     * @param code github登录返回的code
     */
    R getToken(String code) throws Exception;
}
