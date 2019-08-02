package com.nblog.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: songyang03
 * @Date: 2019/7/18 09:41
 * @Email: syang_010@163.com
 * @Description:
 */
public final class Return implements Serializable {
    private int code;
    private String msg;
    private Object reqData;
    public static final Return OK = new Return(0, "success");

    public Return() {
        this.code = 0;
        this.msg = "";
        this.reqData = new HashMap();
    }

    public Return(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.reqData = new HashMap();
    }

    public Return(int code, String msg, Object reqData) {
        this.code = code;
        this.msg = msg;
        this.reqData = reqData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getReqData() {
        return reqData;
    }

    public void setReqData(Object reqData) {

        this.reqData = reqData;
    }

    @Override
    public String toString() {
        return "Return{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", reqData=" + reqData +
                '}';
    }
}
