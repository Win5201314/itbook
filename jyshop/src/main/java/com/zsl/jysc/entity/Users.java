package com.zsl.jysc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "用户")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id")
    private Long id;
    @ApiModelProperty(name = "openid", value = "微信登录")
    private String openid;
    @NotNull
    @ApiModelProperty(name = "username", value = "微信昵称")
    private String username;
    @ApiModelProperty(name = "avatar", value = "头像图片地址")
    private String avatar;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "create_time", value = "注册时间")
    private Date create_time;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "update_time", value = "再次登录时间")
    private Date update_time;

}
