package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 参考博文 https://blog.csdn.net/shitianhang123/article/details/84942061
 */
@ApiModel(description = "权限对象")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "权限唯一标识ID")
    private Integer id;
    @ApiModelProperty(name = "permissionName", value = "权限名称")
    private String permissionName;
    @ApiModelProperty(name = "pAlias", value = "权限别名")
    private String pAlias;
    @ApiModelProperty(name = "description", value = "权限描述")
    private String description;
    @ApiModelProperty(name = "available", value = "是否锁定")
    private boolean available;
    @ApiModelProperty(name = "rid", value = "此权限关联角色的id")
    private int rid;
}
