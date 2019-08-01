package com.nblog.mapper;

import com.nblog.bean.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 17:36
 * @Email: syang_010@163.com
 * @Description:
 */
@Mapper
public interface BlogMapper {
    List<Blog> getBlogList(@Param("uid") Long uid,@Param("state") Integer state, @Param("keywords") String keywords, @Param("start") Integer start, @Param("count") Integer count);
//SELECT u.username,b.title,b. FROM tbblog AS b INNER JOIN tbuser AS u ON b.uid = u.id WHERE b.`delete`=0
}
