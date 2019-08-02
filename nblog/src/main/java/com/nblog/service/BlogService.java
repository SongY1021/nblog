package com.nblog.service;

import com.nblog.base.PAGE;
import com.nblog.bean.Blog;
import com.nblog.mapper.BlogMapper;
import com.nblog.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    public List<Blog> getBlogList(Long uid, Integer state, String keywords, Integer start, Integer count){
        if(StringUtils.isEmpty(start)){
            start = PAGE.START.getIndex();
        }
        if (StringUtils.isEmpty(count)){
            count = PAGE.COUNT.getIndex();
        }
        List<Blog> blogList = blogMapper.getBlogList(uid, state, keywords, start, count);
        if(StringUtils.isEmpty(blogList)){
            blogList = new ArrayList<Blog>();
        }

        for (Blog blog : blogList) {
            long commentCount = commentMapper.getCount(blog.getId());
            blog.setCommentCount(commentCount);
        }
        return blogList;
    }
}
