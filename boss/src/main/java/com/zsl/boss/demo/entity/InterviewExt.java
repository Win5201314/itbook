package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "面试信息表对象拓展对象")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InterviewExt extends Interview implements Serializable {

    @ApiModelProperty(name = "user", value = "应聘方对象")
    private Users user;
    @ApiModelProperty(name = "recruiters", value = "招聘方对象")
    private Recruiters recruiters;
    @ApiModelProperty(name = "job", value = "职位对象")
    private Job job;
}
