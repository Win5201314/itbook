package com.zsl.xiangqing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录访问信息统计
 */
@ApiModel(description = "用户")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class LoginInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "infoId", value = "主键")
    private Long infoId;

    @ApiModelProperty(name = "loginName", value = "用户账号")
    private String loginName;

    @ApiModelProperty(name = "status", value = "登录状态 0成功 1失败")
    private String status;

    @ApiModelProperty(name = "ipAddress", value = "登录IP地址")
    private String ipAddress;

    @ApiModelProperty(name = "loginLocation", value = "登录地点")
    private String loginLocation;

    @ApiModelProperty(name = "browser", value = "浏览器类型")
    private String browser;

    @ApiModelProperty(name = "os", value = "操作系统")
    private String os;

    @ApiModelProperty(name = "msg", value = "提示消息")
    private String msg;

    @ApiModelProperty(name = "loginTime", value = "访问时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

}
