package com.inxedu.os.wechat.entity.school;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 学校实体
 * @author lisheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TSchool implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 838152101269081714L;
    private String id;// 主键  UUID
    private String name;// 学校名称
    private String pNo;// 省  编号
    private String cNo;// 市  编号
    private String dNo;// 区  编号
}
