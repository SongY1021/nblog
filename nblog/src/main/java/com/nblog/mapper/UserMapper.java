package com.nblog.mapper;

import com.nblog.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: songyang03
 * @Date: 2019/7/18 10:03
 * @Email: syang_010@163.com
 * @Description:
 */
@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);
}
