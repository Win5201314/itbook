package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "关注的公司")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttentionCompany implements Serializable {
    @ApiModelProperty(name = "id", value = "主键")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "应聘方主键")
    private Integer userId;
    @ApiModelProperty(name = "companyId", value = "公司主键")
    private Integer companyId;
}
