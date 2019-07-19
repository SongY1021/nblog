package com.nblog.utils;

import com.nblog.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: songyang03
 * @Date: 2019/7/18 11:32
 * @Email: syang_010@163.com
 * @Description:
 */
public class UserUtil {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
