package com.inxedu.os.wechat.entity.room;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 空间实体
 * @author lisheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TRoom implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 838152271251081714L;
    private String id;			// 主键  UUID
    private String name;		// 空间名称
    private String tagId;		// 子标签，用,隔开
    private int isOpen;			// 是否对外开启
    private int isUsed;			//是否被占用
    private String userId;		//使用者id
    private String userName;	//使用者姓名
    private int action;			//使用用途
    private String schoolId;	// 学校id
    private String roomNo;   	//空间编号
}
