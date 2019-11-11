package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "面试信息表对象")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Interview implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "主键")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "应聘方主键")
    private Integer userId;
    @ApiModelProperty(name = "recruiterId", value = "招聘方主键")
    private Integer recruiterId;
    @ApiModelProperty(name = "jobId", value = "职位主键")
    private Integer jobId;
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
    @ApiModelProperty(name = "beginTime", value = "面试开始时间")
    private Date beginTime;
}
