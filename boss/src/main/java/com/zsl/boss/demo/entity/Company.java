package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "公司信息对象表")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "主键")
    private Integer id;
    @ApiModelProperty(name = "name", value = "公司名字")
    private String name;
    @ApiModelProperty(name = "scale", value = "公司规模 级别区分")
    private Integer scale;
    @ApiModelProperty(name = "financingStage", value = "融资阶段 级别区分")
    private Integer financingStage;
    @ApiModelProperty(name = "tmt", value = "所属行业")
    private String tmt;
    @ApiModelProperty(name = "officialWebsite", value = "公司官网")
    private String officialWebsite;
    @ApiModelProperty(name = "companyProfile", value = "公司简介")
    private String companyProfile;
    @ApiModelProperty(name = "workTime", value = "工作时间段,比如：9:00 - 18:00")
    private String workTime;
    @ApiModelProperty(name = "restTime", value = "休息时间, 单休 双休 大小周")
    private String restTime;
    @ApiModelProperty(name = "overWork", value = "加班情况 不加班 偶尔 弹性")
    private String overWork;
    @ApiModelProperty(name = "welfare", value = "公司福利")
    private String welfare;
    @ApiModelProperty(name = "images", value = "公司相册 空格分割")
    private String images;

    @ApiModelProperty(name = "hot", value = "公司的热度值")
    private Integer hot;
}
