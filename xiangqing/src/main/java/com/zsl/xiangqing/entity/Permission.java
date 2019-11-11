package com.zsl.xiangqing.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 参考博文 https://blog.csdn.net/shitianhang123/article/details/84942061
 */
@ApiModel(description = "权限对象")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "权限唯一标识ID")
    private Integer id;
    @NotNull
    @ApiModelProperty(name = "permissionName", value = "权限名称")
    private String permissionName;
    @ApiModelProperty(name = "description", value = "权限描述")
    private String description;
}
