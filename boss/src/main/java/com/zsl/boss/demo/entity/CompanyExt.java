package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "公司的拓展，公司发布了哪些职位")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyExt extends Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "主键")
    private Integer id;
    @ApiModelProperty(name = "jobs", value = "公司发布的职位信息")
    private List<Job> jobs;
}
