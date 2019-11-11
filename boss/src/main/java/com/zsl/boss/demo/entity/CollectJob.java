package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "收藏的职位中间表")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CollectJob implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "应聘方主键")
    private Integer userId;
    @ApiModelProperty(name = "jobId", value = "职位主键")
    private Integer jobId;
}
