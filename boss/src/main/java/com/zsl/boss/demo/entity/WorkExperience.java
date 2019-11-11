package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "工作经历")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkExperience implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "companyName", value = "公司名字")
    private String companyName;
    @ApiModelProperty(name = "industry", value = "所在行业")
    private String industry;
    @ApiModelProperty(name = "entryTime", value = "入职时间")
    private String entryTime;
    @ApiModelProperty(name = "leaveTime", value = "离职时间")
    private String leaveTime;
    @ApiModelProperty(name = "jobType", value = "职位类型")
    private String jobType;
    @ApiModelProperty(name = "jobName", value = "职位名称")
    private String jobName;
    @ApiModelProperty(name = "title", value = "技能标签")
    private String title;
    @ApiModelProperty(name = "department", value = "所属部门")
    private String department;
    @ApiModelProperty(name = "workContent", value = "工作内容")
    private String workContent;
    @ApiModelProperty(name = "workPerformance", value = "工作业绩")
    private String workPerformance;
    @ApiModelProperty(name = "isHideToCompany", value = "是否对当前公司屏蔽")
    private Boolean isHideToCompany;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Integer userId;
}
