package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "求职期望")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobExpectation implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "jobName", value = "期望职位")
    private String jobName;
    @ApiModelProperty(name = "industry", value = "期望行业")
    private String industry;
    @ApiModelProperty(name = "workPlace", value = "工作城市")
    private String workPlace;
    @ApiModelProperty(name = "salaryRange", value = "薪资范围")
    private String salaryRange;
    @ApiModelProperty(name = "userId", value = "用户主键Id")
    private Integer userId;
}
