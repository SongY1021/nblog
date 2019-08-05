package com.nblog.mapper;

import com.nblog.bean.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 17:36
 * @Email: syang_010@163.com
 * @Description:
 */
@Mapper
public interface BlogMapper {
    List<Blog> getBlogList(@Param("uid") Long uid,@Param("state") Integer state, @Param("keywords") String keywords, @Param("typeid") Integer typeid, @Param("start") Integer start, @Param("count") Integer count);

    int getBlogTotleCount(@Param("uid") Long uid,@Param("state") Integer state, @Param("keywords") String keywords, @Param("typeid") Integer typeid);

    Map<String, Integer> getTipCount();

    Blog getBlogDetail(@Param("bid") Long bid);

    int updateBlog(Blog blog);
}
