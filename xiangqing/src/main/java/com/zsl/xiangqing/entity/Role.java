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
    @ApiModelProperty(name = "roleName", value = "角色名称")
    private String roleName;
    @ApiModelProperty(name = "description", value = "角色描述")
    private String description;
}
