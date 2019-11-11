package com.inxedu.os.edu.entity.letter;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : xiaokun
 * @ClassName com.inxedu.os.core.entity.weibo.Letter
 * @description 站内信
 * @Create Date : 2013-12-13 下午12:43:02
 * @author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MsgSystem implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2972088766561758830L;
    private Long id;// 主键Id
    private String content;// 信内容
    private Date addTime;// 添加时间
    private Date updateTime;// 更新时间
    private int status;//
    private String addTimeStr;

    //查询辅助字段
    private Date endTime;//结束时间
}
