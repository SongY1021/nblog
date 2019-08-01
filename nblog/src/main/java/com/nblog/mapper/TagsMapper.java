package com.nblog.mapper;

import com.nblog.bean.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 16:38
 * @Email: syang_010@163.com
 * @Description:
 */
@Mapper
public interface TagsMapper {
    //获取标签信息
    List<Tag> getTags(Long bid);
    //增加标签
    int addTag(List<Tag> tags);
}
