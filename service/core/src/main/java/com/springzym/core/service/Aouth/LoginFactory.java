package com.springzym.core.service.Aouth;

import com.springzym.core.service.Aouth.impl.GitHub;
import com.springzym.core.service.GithubUserService;
import com.springzym.core.service.UserAssociationService;
import com.springzym.core.service.UserService;
import com.springzym.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 登录服务工厂类
 */
@Service
public class LoginFactory implements BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(LoginFactory.class);

    /**
     * 登录方式集合
     */
    public static HashMap<String, AouthService> loginServiceMap = new HashMap<>();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        loginServiceMap.put("github", beanFactory.getBean(GitHub.class));
    }

    /**
     * 发起登录请求
     * @param loginType
     * @return
     */
    public R login(String loginType) {
        switch (loginType) {
            case "github":
                return loginServiceMap.get("github").login();
            default:
                return R.error("登录方式不存在");
        }
    }

    /**
     * 获取用户信息
     * @param loginType
     * @param code
     * @return
     * @throws Exception
     */
    public R getUserInfo(String loginType, String code) throws Exception {
        switch (loginType) {
            case "github":
                return loginServiceMap.get("github").getUserInfo(code);
            default:
                return R.error("登录方式不存在");
        }
    }
}
