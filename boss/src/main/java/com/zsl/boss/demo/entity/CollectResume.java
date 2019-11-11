package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "收藏牛人的简历")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectResume implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "主键")
    private Integer id;
    @ApiModelProperty(name = "resumeId", value = "简历主键")
    private Integer resumeId;
    @ApiModelProperty(name = "recruiterId", value = "招聘方主键")
    private Integer recruiterId;
}
