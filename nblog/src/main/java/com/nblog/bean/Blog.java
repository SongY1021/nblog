package com.nblog.bean;

import com.nblog.base.STATE;

import java.util.List;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 10:11
 * @Email: syang_010@163.com
 * @Description:
 */
public class Blog {
    private Long id;
    private Long uid;
    private String username;
    private String title;
    private Integer typeid;
    private String typeName;
    private Integer state;
    private Integer top;
    private String summary;
    private String mdContent;
    private String htmlContent;
    private Long readCount;
    private Long commentCount;
    private Integer oncomment;
    private Integer isdel;
    private String createtime;
    private String modifytime;

    private User user;
    private List<Tag> tags;
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeid() {
        return this.typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypeName() {
        if(this.typeid != null){
            return STATE.DESC(this.typeid);
        }
        return "";
    }

    public Integer getState() {
//        if(this.state != null){
//            return STATE.DESC(this.state);
//        }
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getOncomment() {
        return oncomment;
    }

    public void setOncomment(Integer oncomment) {
        this.oncomment = oncomment;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", typeid=" + typeid +
                ", state=" + state +
                ", top=" + top +
                ", summary='" + summary + '\'' +
                ", mdContent='" + mdContent + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", readCount=" + readCount +
                ", commentCount=" + commentCount +
                ", isdel=" + isdel +
                ", createtime='" + createtime + '\'' +
                ", user=" + user +
                ", tags=" + tags +
                ", comments=" + comments +
                '}';
    }
}