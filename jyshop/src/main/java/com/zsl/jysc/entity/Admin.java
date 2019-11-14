package com.zsl.jysc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description = "管理员")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id")
    private Long id;

    @NotNull
    @ApiModelProperty(name = "username", value = "微信昵称")
    private String username;
    @ApiModelProperty(name = "password", value = "密码")
    private String password;
}
