package com.nblog.bean;

import com.nblog.base.FC;

import java.util.List;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 10:11
 * @Email: syang_010@163.com
 * @Description:
 */
public class Blog {
    private Long id;
    private String title;
    private Integer typeid;
    private Integer state;
    private Integer top;
    private String summary;
    private String mdContent;
    private Integer delete;
    private String htmlContent;
    private String createtime;

    private User user;
    private Read read;
    private List<Tag> tags;
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeid() {
        return FC.DESC(this.typeid);
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getState() {
        return FC.DESC(this.state);
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

    public Read getRead() {
        return read;
    }

    public void setRead(Read read) {
        this.read = read;
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

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", typeid=" + typeid +
                ", state=" + state +
                ", summary='" + summary + '\'' +
                ", mdContent='" + mdContent + '\'' +
                ", delete=" + delete +
                ", htmlContent='" + htmlContent + '\'' +
                ", createtime='" + createtime + '\'' +
                ", user=" + user +
                ", read=" + read +
                ", tags=" + tags +
                ", comments=" + comments +
                '}';
    }
}
