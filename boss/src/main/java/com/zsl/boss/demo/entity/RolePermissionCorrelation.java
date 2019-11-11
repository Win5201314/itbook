package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "角色权限关联对象")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolePermissionCorrelation implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "roleId", value = "角色主键")
    private Integer roleId;
    @ApiModelProperty(name = "permissionId", value = "权限主键")
    private Integer permissionId;
}
