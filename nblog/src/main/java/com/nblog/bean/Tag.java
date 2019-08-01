package com.nblog.bean;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 10:25
 * @Email: syang_010@163.com
 * @Description:
 */
public class Tag {
    private Long id;
    private String name;
    private String createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
