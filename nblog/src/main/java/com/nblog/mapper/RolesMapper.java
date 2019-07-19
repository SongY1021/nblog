package com.nblog.mapper;

import com.nblog.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: songyang03
 * @Date: 2019/7/18 10:50
 * @Email: syang_010@163.com
 * @Description:
 */
@Mapper
public interface RolesMapper {
    List<Role> getRolesByUid(Long uid);
}
