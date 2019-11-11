package com.zsl.xiangqing.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@ApiModel(description = "用户角色连接表对象")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class UsersAndRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键")
    private Integer id;
    @ApiModelProperty(name = "user_id", value = "用户id")
    private Integer user_id;
    @ApiModelProperty(name = "role_id", value = "角色id")
    private Integer role_id;
}
