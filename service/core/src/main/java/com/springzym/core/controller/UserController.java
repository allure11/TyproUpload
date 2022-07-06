package com.springzym.core.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "redirect:/index.html";
    }

}
