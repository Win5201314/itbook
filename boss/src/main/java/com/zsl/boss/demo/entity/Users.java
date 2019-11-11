package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "应聘方用户")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id")
    private Integer id;
    @ApiModelProperty(name = "openid", value = "微信唯一标识")
    private String openid;
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;
    @ApiModelProperty(name = "password", value = "密码  【手机号 + 密码登录】")
    private String password;
    @ApiModelProperty(name = "salt", value = "盐")
    private String salt;
    @ApiModelProperty(name = "name", value = "用户名[真实名字]")
    private String name;
    @ApiModelProperty(name = "dateOfBirth", value = "出生年月")
    private String dateOfBirth;
    @ApiModelProperty(name = "age", value = "年龄")
    private Integer age;
    @ApiModelProperty(name = "sex", value = "性别")
    private String sex;
    @ApiModelProperty(name = "joinWorkTime", value = "参加工作时间 年-月")
    private String joinWorkTime;
    @ApiModelProperty(name = "wxAccount", value = "微信号 别人加你微信深聊  自己主动推荐个人微信号给别人")
    private String wxAccount;
    @ApiModelProperty(name = "createTime", value = "注册时间")
    private Date createTime;
    @ApiModelProperty(name = "province", value = "省份")
    private String province;
    @ApiModelProperty(name = "city", value = "城市")
    private String city;
    @ApiModelProperty(name = "country", value = "国家")
    private String country;
    @ApiModelProperty(name = "headimgurl", value = "头像图片地址")
    private String headimgurl;
    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;
    @ApiModelProperty(name = "locked", value = "true = 被锁定， false = 未被锁定")
    private boolean locked;
    @ApiModelProperty(name = "jobStatus", value = "求职状态")
    private Integer jobStatus;
    @ApiModelProperty(name = "superiority", value = "我的优势")
    private String superiority;
    @ApiModelProperty(name = "isVIP", value = "普通用户 = false  VIP用户 = true")
    private boolean isVIP;

    @ApiModelProperty(name = "longitude", value = "经度")
    private float longitude;
    @ApiModelProperty(name = "latitude", value = "纬度")
    private float latitude;

    /*@ApiModelProperty(name = "role", value = "角色")
    private Role role;*/
    @ApiModelProperty(name = "greetWord", value = "打招呼用语")
    private String greetWord;
    @ApiModelProperty(name = "isHideResume", value = "是否隐藏简历")
    private Boolean isHideResume;
    @ApiModelProperty(name = "isHideResumeToHeadhunter", value = "是否对猎头隐藏简历")
    private Boolean isHideResumeToHeadhunter;
    @ApiModelProperty(name = "isLookHeadhunterJobs", value = "不看猎头职位")
    private Boolean isLookHeadhunterJobs;
    @ApiModelProperty(name = "isEncryptionTelephoneCanCallMe", value = "是否允许加密电话联系我")
    private Boolean isEncryptionTelephoneCanCallMe;

    @ApiModelProperty(name = "weakTime", value = "再次使用时间")
    private Date weakTime;

    public String getCredentialsSalt() {
        return phone + salt;
    }
}
