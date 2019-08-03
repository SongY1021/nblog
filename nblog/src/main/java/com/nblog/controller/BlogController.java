package com.nblog.controller;

import com.nblog.base.Return;
import com.nblog.bean.Blog;
import com.nblog.service.BlogService;
import com.nblog.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: songyang03
 * @Date: 2019/8/2 14:44
 * @Email: syang_010@163.com
 * @Description:
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Return getBlogList(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords){
        int blogTotleCount = blogService.getBlogTotleCount(UserUtil.getCurrentUser().getId(), state, keywords);
        List<Blog> blogList = blogService.getBlogList(UserUtil.getCurrentUser().getId(), state, keywords, page, count);
        Map<String, Object> result = new HashMap<>();
        result.put("totle", blogTotleCount);
        result.put("blogs", blogList);
        Return ret = new Return(0, "success", result);
        return ret;
    }

    @RequestMapping(value = "/tip", method = RequestMethod.GET)
    public Return getTipCount(){
        Map<String, Integer> result = blogService.getTipCount();
        return new Return(0, "success", result);
    }
}
