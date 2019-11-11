package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "职位对象表")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "name", value = "职位名字")
    private String name;
    @ApiModelProperty(name = "experience", value = "经验要求 级别来区分,好计算")
    private Integer experience;
    @ApiModelProperty(name = "education", value = "学历要求 级别来区分,好计算")
    private Integer education;
    @ApiModelProperty(name = "salaryRange", value = "薪资范围 级别来区分,好计算")
    private Integer salaryRange;
    @ApiModelProperty(name = "description", value = "职位详细描述")
    private String description;
    @ApiModelProperty(name = "workPlace", value = "工作地址")
    private String workPlace;
    @ApiModelProperty(name = "status", value = "0 = 正常开放中， 1 = 待开放, 2 = 已关闭, 3 = 审核失败")
    private Integer status;
    @ApiModelProperty(name = "recruitersId", value = "发布者主键")
    private Integer recruitersId;
    @ApiModelProperty(name = "industry", value = "行业 级别来区分,好计算")
    private Integer industry;
    @ApiModelProperty(name = "companyId", value = "公司主键")
    private Integer companyId;
}
