package com.nblog.mapper;

import com.nblog.bean.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    String[] getTags(Long bid);
    //增加标签
    int addTag(List<Tag> tags);

    List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);

    int addTags(@Param("tags") String[] tags);

    int addTagRelation(@Param("tags") List<Long> tags, @Param("bid") Long bid);

    int delTagsByBid(Long bid);
}
