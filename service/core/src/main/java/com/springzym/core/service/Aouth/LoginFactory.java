package com.springzym.core.service.Aouth;

import com.springzym.core.service.Aouth.impl.GitHub;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginFactory {

    /**
     * 登录方式集合
     */
    public HashMap<String, AouthService> loginServiceMap = new HashMap<>();

}
