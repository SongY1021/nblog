package com.nblog.base;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 10:46
 * @Email: syang_010@163.com
 * @Description:
 */
public enum STATE {
    STATE_DEFAULT(-1, "默认"),
    STATE_DRAFT(0, "草稿"),
    STATE_OPEN(1, "公开"),
    STATE_PRIVATE(2, "私密"),
    STATE_RECYCLE_BIN(3, "回收站"),
    TYPE_ORIGINAL(11, "原创"),
    TYPE_REPRINT(12, "转载"),
    TYPE_TRANSLATE(13, "翻译");

    private int code;
    private String desc;

    private STATE(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String DESC(int code) {
        for (STATE STATE : STATE.values()) {
            if (STATE.getCode() == code) {
                return STATE.desc;
            }
        }
        return "";
    }


    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
