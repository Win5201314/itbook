package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "简历对象拓展对象")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResumeExt extends Resume implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "jobExpectations", value = "求职期望")
    private JobExpectation jobExpectations;
    @ApiModelProperty(name = "workExperiences", value = "工作经历")
    private List<WorkExperience> workExperiences;
    @ApiModelProperty(name = "projectExpectations", value = "项目经历")
    private List<ProjectExpectation> projectExpectations;
    @ApiModelProperty(name = "educations", value = "教育经历")
    private List<EducationExpectation> educations;
    @ApiModelProperty(name = "socialHomePages", value = "社交主页")
    private List<SocialHomePage> socialHomePages;
    @ApiModelProperty(name = "voluntarys", value = "志愿服务经历")
    private List<Voluntary> voluntarys;
}
