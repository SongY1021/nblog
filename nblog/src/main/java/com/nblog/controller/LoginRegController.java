package com.nblog.controller;

import com.nblog.base.Return;
import com.nblog.bean.User;
import com.nblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: songyang03
 * @Date: 2019/7/18 09:34
 * @Email: syang_010@163.com
 * @Description:
 */
@RestController
public class LoginRegController {
    @Autowired
    UserService userService;

    @RequestMapping("/login_page")
    public Return login_page(){
        return new Return(-1, "尚未登录");
    }
}
