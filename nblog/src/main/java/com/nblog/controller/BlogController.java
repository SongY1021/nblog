package com.nblog.controller;

import com.nblog.base.Return;
import com.nblog.bean.Blog;
import com.nblog.service.BlogService;
import com.nblog.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: songyang03
 * @Date: 2019/8/2 14:44
 * @Email: syang_010@163.com
 * @Description:
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    public Return getBlogList(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
//        List<Blog> blogList = blogService.getBlogList(UserUtil.getCurrentUser().getId())
        return Return.OK;
    }
}
