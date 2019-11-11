package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "用户权限关联对象")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRoleCorrelation implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Integer userId;
    @ApiModelProperty(name = "roleId", value = "角色主键")
    private Integer roleId;
}
