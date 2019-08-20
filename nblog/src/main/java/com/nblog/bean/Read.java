package com.nblog.bean;

import java.io.Serializable;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 10:19
 * @Email: syang_010@163.com
 * @Description:
 */
public class Read implements Serializable {
    private Long id;
    private Long bid;
    private Long uid;
    private Long pv;

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

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    @Override
    public String toString() {
        return "Read{" +
                "id=" + id +
                ", bid=" + bid +
                ", uid=" + uid +
                ", pv=" + pv +
                '}';
    }
}
