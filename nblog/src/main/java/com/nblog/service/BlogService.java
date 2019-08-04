package com.nblog.service;

import com.nblog.bean.Blog;
import com.nblog.bean.Tag;
import com.nblog.mapper.BlogMapper;
import com.nblog.mapper.CommentMapper;
import com.nblog.mapper.TagsMapper;
import com.nblog.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: songyang03
 * @Date: 2019/8/2 11:38
 * @Email: syang_010@163.com
 * @Description:
 */
@Service
public class BlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    TagsMapper tagsMapper;

    public List<Blog> getBlogList(Long uid, Integer state, String keywords, Integer typeid, Integer page, Integer count){
        PageUtils pageUtils = new PageUtils();
        List<Blog> blogList = blogMapper.getBlogList(uid, state, keywords, typeid, pageUtils.getStart(page, count), count);
        if(StringUtils.isEmpty(blogList)){
            blogList = new ArrayList<Blog>();
        }

        for (Blog blog : blogList) {
            long commentCount = commentMapper.getCommentCount(blog.getId());
            blog.setCommentCount(commentCount);
        }
        return blogList;
    }

    public Integer getBlogTotleCount(Long uid, Integer state, String keywords, Integer typeid){
        return blogMapper.getBlogTotleCount(uid, state, keywords, typeid);
    }

    public Map getTipCount(){
        return blogMapper.getTipCount();
    }

    public Blog getBlogDetail(Long bid){
        Blog blog = blogMapper.getBlogDetail(bid);
        if(blog != null){
            List<Tag> tagList = tagsMapper.getTags(bid);
            blog.setTags(tagList);
        }
        return blog;
    }
}
