package com.zsl.xiangqing.common.enums;

/**
 * 学历类型
 */
public enum EducationType {

    NOEDUCATION(1, "没读书"),
    PRIMARY(2, "小学学历"),
    HIGH(3, "高中学历"),
    BACHELOR(4, "本科学历"),
    GRADUATE(5, "研究生学历"),
    MASTER(6, "硕士学历"),
    DOCTOR(7, "博士学历");

    private final int code;
    private final String desc;
    EducationType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
