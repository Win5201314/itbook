package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "屏蔽公司")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShieldCompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Integer userId;
    @ApiModelProperty(name = "companyId", value = "公司主键")
    private Integer companyId;
}
