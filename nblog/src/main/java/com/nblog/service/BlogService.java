package com.nblog.service;

import com.nblog.bean.Blog;
import com.nblog.mapper.BlogMapper;
import com.nblog.mapper.CommentMapper;
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

    public List<Blog> getBlogList(Long uid, Integer state, String keywords, Integer page, Integer count){
        List<Blog> blogList = blogMapper.getBlogList(uid, state, keywords, PageUtils.getStart(page, count), count);
        if(StringUtils.isEmpty(blogList)){
            blogList = new ArrayList<Blog>();
        }

        for (Blog blog : blogList) {
            long commentCount = commentMapper.getCommentCount(blog.getId());
            blog.setCommentCount(commentCount);
        }
        return blogList;
    }

    public Integer getBlogTotleCount(Long uid, Integer state, String keywords){
        return blogMapper.getBlogTotleCount(uid, state, keywords);
    }

    public Map getTipCount(){
        return blogMapper.getTipCount();
    }
}
