package com.nblog.bean;

import java.util.List;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 10:26
 * @Email: syang_010@163.com
 * @Description:
 */
public class Comment {
    private Long id;
    private Long bid;
    private Long uid;
    private String content;

    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", bid=" + bid +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
