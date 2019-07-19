package com.nblog.service;

import com.nblog.bean.User;
import com.nblog.mapper.RolesMapper;
import com.nblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Author: songyang03
 * @Date: 2019/7/18 10:11
 * @Email: syang_010@163.com
 * @Description:
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesMapper rolesMapper;

    @Override
    public User loadUserByUsername(String username){
        User user = userMapper.loadUserByUsername(username);
        if(user ==null){
            return new User();
        }
        user.setRoles(rolesMapper.getRolesByUid(user.getId()));
        return user;
    }
}
