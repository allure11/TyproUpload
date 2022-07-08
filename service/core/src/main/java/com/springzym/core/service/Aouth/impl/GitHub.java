package com.springzym.core.service.Aouth.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springzym.core.entity.GithubUser;
import com.springzym.core.entity.User;
import com.springzym.core.entity.UserAssociation;
import com.springzym.core.service.Aouth.AouthService;
import com.springzym.core.service.GithubUserService;
import com.springzym.core.service.UserAssociationService;
import com.springzym.core.service.UserService;
import com.springzym.util.HttpClientUtils;
import com.springzym.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

/**
 * GitHub登录服务接口实现类
 * @author zym
 */
@Service
public class GitHub implements AouthService {

    private static final Logger logger = LoggerFactory.getLogger(GitHub.class);

    private UserService userServiceImpl;
    private UserAssociationService userAssociationServiceImpl;
    private GithubUserService githubUserServiceImpl;

    @Autowired
    public GitHub(UserService userServiceImpl, UserAssociationService userAssociationServiceImpl, GithubUserService githubUserServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.userAssociationServiceImpl = userAssociationServiceImpl;
        this.githubUserServiceImpl = githubUserServiceImpl;
    }

    /**
     * 发起登录请求
     */
    @Override
    public R login() {
        return R.ok().setData("url", "https://github.com/login/oauth/authorize?client_id=1aa1f52ece4abbe7c36f");
    }

    /**
     * 获取 token 信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R getToken(String code) throws Exception {
        boolean flag = false;
        User user = new User();
        // 根据code获取token
        HashMap<String, String> parameter = new HashMap<String, String>(1);
        parameter.put("code", code);
        parameter.put("client_id","1aa1f52ece4abbe7c36f");
        parameter.put("client_secret","3b53da2f67d792a46fe6d853fa0e092f9fed93fb");
        String token = HttpClientUtils.postData("https://github.com/login/oauth/access_token", parameter, "UTF-8");

        // 根据token获取用户信息
        HashMap<String, String> map = new HashMap<>(2);
        map.put("accept", "application/json");
        map.put("Authorization","token "+token.split("&")[0].split("=")[1]);
        String s1 = HttpClientUtils.get("https://api.github.com/user", map);
        logger.info(s1);

        // 将 json 信息转换成hashmap
        HashMap<String, Object> userinfo = (HashMap<String, Object>) JSONUtils.parse(s1);
        GithubUser githubUser = new GithubUser(userinfo);

        if (!Optional.ofNullable(githubUserServiceImpl.getById(githubUser.getId())).isPresent()){
            flag = githubUserServiceImpl.save(githubUser);
            logger.info(githubUser.toString());

            // 保存用户信息
            user.setName(String.valueOf(githubUser.getName()));
            user.setAvatarUrl(String.valueOf(githubUser.getAvatarUrl()));
            user.setEmail(String.valueOf(githubUser.getEmail()));
            flag = userServiceImpl.save(user);
            logger.info(user.toString());

            UserAssociation userAssociation = new UserAssociation();
            userAssociation.setUserId(user.getId());
            userAssociation.setTripartiteId(githubUser.getId());
            flag = userAssociationServiceImpl.save(userAssociation);
        } else {
            user = userServiceImpl.getIdAndNameByTripartiteId(githubUser.getId());
//            QueryWrapper<UserAssociation> wrapper = new QueryWrapper<>();
//            wrapper.eq("tripartite_id", githubUser.getId());
//            UserAssociation one = userAssociationServiceImpl.getOne(wrapper);
//            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//            userQueryWrapper.eq("id", one.getUserId());
//            user = userServiceImpl.getOne(userQueryWrapper);
        }


        return R.ok(flag).setData("user", user);
    }
}
