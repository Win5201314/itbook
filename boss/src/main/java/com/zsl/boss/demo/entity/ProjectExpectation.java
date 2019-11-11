package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "项目经验")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectExpectation implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "name", value = "项目名字")
    private String name;
    @ApiModelProperty(name = "role", value = "担任角色")
    private String role;
    @ApiModelProperty(name = "begin", value = "开始时间")
    private String begin;
    @ApiModelProperty(name = "end", value = "结束时间")
    private String end;
    @ApiModelProperty(name = "description", value = "项目描述")
    private String description;
    @ApiModelProperty(name = "performance", value = "项目业绩")
    private String performance;
    @ApiModelProperty(name = "url", value = "项目链接")
    private String url;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Integer userId;
}
