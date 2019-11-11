package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "教育经历")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationExpectation implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "schoolName", value = "学校名字")
    private String schoolName;
    @ApiModelProperty(name = "education", value = "学历")
    private String education;
    @ApiModelProperty(name = "major", value = "专业")
    private String major;
    @ApiModelProperty(name = "time", value = "时间段")
    private String time;
    @ApiModelProperty(name = "experienceInSchool", value = "在校经历")
    private String experienceInSchool;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Integer userId;
}
