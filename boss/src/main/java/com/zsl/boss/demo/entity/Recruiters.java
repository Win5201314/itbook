package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(description = "招聘方对象表")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Recruiters implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "主键")
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
    @ApiModelProperty(name = "age", value = "年龄")
    private String age;
    @ApiModelProperty(name = "sex", value = "性别")
    private String sex;
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
    @ApiModelProperty(name = "isVIP", value = "普通用户 = false  VIP用户 = true")
    private Boolean isVIP;

    @ApiModelProperty(name = "longitude", value = "经度")
    private float longitude;
    @ApiModelProperty(name = "latitude", value = "纬度")
    private float latitude;

    @ApiModelProperty(name = "duty", value = "职务")
    private String duty;
    @ApiModelProperty(name = "teamIntroduction", value = "团队介绍")
    private String teamIntroduction;
    @ApiModelProperty(name = "teamLightspot", value = "团队亮点[标签之间用空格分割]")
    private String teamLightspot;
    @ApiModelProperty(name = "companyId", value = "对应的公司")
    private String companyId;

    @ApiModelProperty(name = "greetWord", value = "打招呼用语")
    private String greetWord;

    @ApiModelProperty(name = "talkResumeIds", value = "沟通过的简历")
    private List<Integer> talkResumeIds;
    @ApiModelProperty(name = "interviewIds", value = "待面试信息")
    private List<Integer> interviewIds;
    @ApiModelProperty(name = "collectResumeIds", value = "收藏的简历信息")
    private List<Integer> collectResumeIds;
    @ApiModelProperty(name = "propertyIds", value = "我的道具")
    private List<Integer> propertyIds;
    @ApiModelProperty(name = "publicJobIds", value = "发布的职位")
    private List<Integer> publicJobIds;

    @ApiModelProperty(name = "role", value = "角色")
    private Role role;//角色

    public String getCredentialsSalt() {
        return phone + salt;
    }
}
