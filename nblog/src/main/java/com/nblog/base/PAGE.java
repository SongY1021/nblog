package com.nblog.base;

import org.springframework.util.StringUtils;

/**
 * @Author: songyang03
 * @Date: 2019/8/2 11:49
 * @Email: syang_010@163.com
 * @Description:
 */
public enum PAGE {
    COUNT(10,"每页条数"),
    START(0, "开始条数");


    private int index;
    private String desc;

    private PAGE(int index, String desc){
        this.index = index;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }

}

