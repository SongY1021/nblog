package com.nblog.controller;

import com.nblog.base.Env;
import com.nblog.base.Return;
import com.nblog.base.STATE;
import com.nblog.bean.Blog;
import com.nblog.service.BlogService;
import com.nblog.utils.BaseUtils;
import com.nblog.utils.UserUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    Environment env;

    private static final Logger LOG = LoggerFactory.getLogger(BlogController.class);

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

    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public Return uploadImg(HttpServletRequest req, MultipartFile image) {
        if(StringUtils.isEmpty(image)){
            return new Return(-1, "error", "图片上传失败, 图片为空");
        }
        //1.初始化文件地址
        StringBuffer url = new StringBuffer();
        //2.初始化文件存放位置
        String filePath = env.getProperty("img.upload.path", "/blogimg/") + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        //3.获取绝对路径
        String imgFolderPath = req.getServletContext().getRealPath(filePath);
        //3.初始化文件夹
        File imgFolder = new File(imgFolderPath);
        if(!imgFolder.exists()){
            imgFolder.mkdirs();
        }
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = BaseUtils.UUID() + "_" + image.getOriginalFilename().replace(" ","");
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            Map data = new HashMap();
            data.put("imgPath", url.toString());
            return new Return(0, "success", data);
        } catch (IOException e) {
            LOG.error("图片上传失败！", e.getMessage());
        }
        return new Return(-1, "error", "图片上传失败");
    }
}
