package com.nblog.controller;

import com.nblog.base.Return;
import com.nblog.bean.User;
import com.nblog.utils.UserUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: songyang03
 * @Date: 2019/7/18 11:33
 * @Email: syang_010@163.com
 * @Description:
 */
@RestController
public class UserController {

    @RequestMapping("/currentUserInfo")
    public Return getCurrentUserInfo(){
        User user = UserUtil.getCurrentUser();
        return new Return(0,"succes", user);
    }

    @RequestMapping("/isAdmin")
    public Return isAdmin(){
        Boolean isAdmin = false;
        Map result = new HashMap();
        List<GrantedAuthority> authorities = UserUtil.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("admin")) {
                isAdmin = true;
            }
        }
        result.put("isAdmin", isAdmin);
        return new Return(0, "success", result);
    }
}
