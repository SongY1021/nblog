package com.nblog.base;

/**
 * @Author: songyang03
 * @Date: 2019/8/1 10:46
 * @Email: syang_010@163.com
 * @Description:
 */
public enum FC {
    STATE_DRAFT(0, "草稿"),
    STATE_OPEN(1, "公开"),
    STATE_PRIVATE(2, "私密"),
    STATE_RECYCLE_BIN(3, "回收站"),
    TYPE_ORIGINAL(11, "原创"),
    TYPE_REPRINT(12, "转载"),
    TYPE_TRANSLATE(13, "翻译");

    private int code;
    private String desc;

    private FC(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String DESC(int code) {
        for (FC fc : FC.values()) {
            if (fc.getCode() == code) {
                return fc.desc;
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
