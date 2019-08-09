package com.nblog.service;

import com.nblog.bean.Blog;
import com.nblog.bean.Tag;
import com.nblog.mapper.BlogMapper;
import com.nblog.mapper.CommentMapper;
import com.nblog.mapper.TagsMapper;
import com.nblog.utils.PageUtils;
import com.nblog.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(BlogService.class);

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
            String[] tagList = tagsMapper.getTags(bid);
            blog.setTags(tagList);
        }
        return blog;
    }

    public int updateBlog(Blog blog){
        return blogMapper.updateBlog(blog);
    }

    public int addBlog(Blog blog){
        int result = -1;
        //截取文章摘要
        if(StringUtils.isEmpty(blog.getSummary())){
            String stripHtml = stripHtml(blog.getHtmlContent());
            blog.setSummary(stripHtml.substring(0, stripHtml.length() > 100 ? 100 : stripHtml.length()));
        }
        if(StringUtils.isEmpty(blog.getId())){//插入新文章
            blog.setUid(UserUtil.getCurrentUser().getId());
            result = blogMapper.addBlog(blog);
            if(result != 1) {
                return result;
            }
        }else{//更新文章
            result = blogMapper.updateBlog(blog);
            if(result != 1) {
                return result;
            }
        }
        if (!StringUtils.isEmpty(blog.getTags())
                && blog.getTags().length>0){
            int tags = addTags(blog.getTags(), blog.getId());
            LOG.info("文章【"+blog.getId()+"】,插入了"+tags+"个标签");
        }
        return result;
    }

    /**
     * 插入标签
     * @param tags
     * @param bid
     * @return
     */
    private int addTags(String[] tags, Long bid){
        int tagCount = tagsMapper.addTags(tags);
        List<Long> tagIds = tagsMapper.getTagIdByName(tags);
        if(StringUtils.isEmpty(tagIds) || tagIds.size() < 1){
            return -1;
        }
        int tagRelationCount = tagsMapper.addTagRelation(tagIds, bid);
        return tagRelationCount;
    }

    /**
     * 截取摘要
     * @param content
     * @return
     */
    private String stripHtml(String content){
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }
}
