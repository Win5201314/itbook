package com.zsl.boss.demo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description = "角色")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "角色唯一标识符")
    private Integer id;
    @NotNull(message = "角色名称不能为空")
    @ApiModelProperty(name = "rName", value = "角色名称")
    private String rName;
    @NotNull(message = "角色别名不能为空")
    @ApiModelProperty(name = "rAlias", value = "角色别名")
    private String rAlias;
    @ApiModelProperty(name = "description", value = "角色描述")
    private String description;
    @ApiModelProperty(name = "available", value = "是否锁定")
    private boolean available;
}
