package com.nblog.controller;

import com.nblog.base.Return;
import com.nblog.base.STATE;
import com.nblog.bean.Blog;
import com.nblog.service.BlogService;
import com.nblog.utils.UserUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Return getBlogList(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords, Integer typeid){
        int blogTotleCount = blogService.getBlogTotleCount(UserUtil.getCurrentUser().getId(), state, keywords, typeid);
        List<Blog> blogList = blogService.getBlogList(UserUtil.getCurrentUser().getId(), state, keywords, typeid, page, count);
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

    @RequestMapping(value = "/{bid}", method = RequestMethod.GET)
    public Return getBlogDetail(@PathVariable Long bid){
        if (bid == null) {
            return new Return(-1, "参数校验失败");
        }
        Blog blog = blogService.getBlogDetail(bid);
        if (blog == null){
            return new Return(-1, "未找到相关文章");
        }else{
            return new Return(0, "success", blog);
        }
    }

    @RequestMapping(value = "/setstatus", method = RequestMethod.POST)
    public Return setBlogStatus(@RequestParam(value = "bid") Long bid, @RequestParam(value = "opt") String opt, @RequestParam(value = "state") Integer state){
        if(bid == null || opt == null || state == null){
            return new Return(-1, "参数校验失败");
        }
        Blog blog = new Blog();
        blog.setId(bid);
        if(opt.equalsIgnoreCase("top")){
            blog.setTop(1-state);
        }else if(opt.equalsIgnoreCase("oncomment")){
            blog.setOncomment(1-state);
        }else if(opt.equalsIgnoreCase("delete")){
            if(state == STATE.STATE_DEFAULT.getCode() || state == STATE.STATE_DRAFT.getCode() ){
                blog.setIsdel(1);
            }
            blog.setTop(0);
            blog.setOncomment(1);
            blog.setState(STATE.STATE_RECYCLE_BIN.getCode());
        }else if(opt.equalsIgnoreCase("recovery")){
            blog.setIsdel(0);
            blog.setState(STATE.STATE_DRAFT.getCode());
        }
        Integer count = blogService.updateBlog(blog);
        if(count > 0){
            Map result = new HashMap();
            result.put("count", count);
            result.put("state", 0);
            result.put("desc", "修改成功");
            return new Return(0, "success", result);
        }
        return new Return(-1, "状态修改失败");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Return addBlog(Blog blog){
        int result = blogService.addBlog(blog);
        if(result != 1){
            return new Return(-1, "文章发布失败");
        }
        return Return.OK;
    }
}
