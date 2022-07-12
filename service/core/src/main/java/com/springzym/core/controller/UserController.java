package com.springzym.core.controller;

import com.springzym.core.service.Aouth.AouthService;
import com.springzym.util.HttpClientUtils;
import com.springzym.util.R;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    /**
     * 日志对象
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AouthService aouthService;

    /**
     * 跳转首页
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "redirect:/index.html";
    }

    /**
     * 用户登录
     */
    @RequestMapping("login/{loginMethod}")
    public String login(@PathVariable String loginMethod) {
        R login = aouthService.login();
        return "redirect:" + login.getData().get("url");
    }

    /**
     * github登录回调
     * @param code
     */
    @GetMapping("collback")
    @ResponseBody
    public R collback(@RequestParam String code) throws Exception {
        return aouthService.getUserInfo(code);
    }
}
