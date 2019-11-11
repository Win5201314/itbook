package com.zsl.xiangqing.entity;

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
    private Integer id;
    @ApiModelProperty(name = "openid", value = "微信登录")
    private String openid;
    @NotNull
    @ApiModelProperty(name = "username", value = "用户名,账号,就是手机号")
    private String username;
    @ApiModelProperty(name = "password", value = "密码")
    @JsonIgnore
    @ToString.Exclude
    private String password;
    @ApiModelProperty(name = "nickName", value = "昵称")
    private String nickName;
    @ApiModelProperty(name = "dateOfBirth", value = "出生年月日")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date dateOfBirth;
    @ApiModelProperty(name = "age", value = "年龄")
    private Integer age;
    @ApiModelProperty(name = "sex", value = "性别")
    private String sex;
    @JsonIgnore
    @ApiModelProperty(name = "wxAccount", value = "微信号 别人加你微信深聊  自己主动推荐个人微信号给别人")
    private String wxAccount;
    @ApiModelProperty(name = "height", value = "身高cm")
    private float height;
    @ApiModelProperty(name = "weight", value = "体重Kg")
    private float weight;
    @ApiModelProperty(name = "constellation", value = "星座")
    private String constellation;
    @ApiModelProperty(name = "education", value = "学历")
    private Integer education;
    @ApiModelProperty(name = "job", value = "职业")
    private String job;
    @ApiModelProperty(name = "salary", value = "薪水区间")
    private Integer salary;
    @ApiModelProperty(name = "carAndHome", value = "车房情况")
    private String carAndHome;
    @ApiModelProperty(name = "interest", value = "兴趣爱好")
    private String interest;
    @ApiModelProperty(name = "mate", value = "择偶要求")
    private String mate;
    @ApiModelProperty(name = "province", value = "省份")
    private String province;
    @ApiModelProperty(name = "city", value = "城市")
    private String city;
    @ApiModelProperty(name = "country", value = "国家")
    private String country;
    @ApiModelProperty(name = "avatar", value = "头像图片地址")
    private String avatar;
    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;
    @ApiModelProperty(name = "delFlag", value = "删除标志（0代表存在 2代表删除）")
    private int delFlag;
    @ApiModelProperty(name = "loginIp", value = "最后登陆IP")
    private String loginIp;

    @ApiModelProperty(name = "longitude", value = "经度")
    private double longitude;
    @ApiModelProperty(name = "latitude", value = "纬度")
    private double latitude;

    @ApiModelProperty(name = "status", value = "用户状态")
    private Integer status;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "create_time", value = "注册时间")
    private Date create_time;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "update_time", value = "再次登录时间")
    private Date update_time;

}
