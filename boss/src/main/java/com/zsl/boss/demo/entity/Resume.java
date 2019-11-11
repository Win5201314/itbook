package com.zsl.boss.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(description = "个人简历表")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "id", value = "关键字")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Integer userId;
    @ApiModelProperty(name = "name", value = "名字")
    private String name;
    @ApiModelProperty(name = "imageUrl", value = "头像地址")
    private String iamgeUrl;
    @ApiModelProperty(name = "workAge", value = "工作经验年数")
    private Integer workAge;
    @ApiModelProperty(name = "age", value = "年龄")
    private Integer age;
    @ApiModelProperty(name = "education", value = "学历级别")
    private Integer education;
    @ApiModelProperty(name = "superiority", value = "我的优势")
    private String superiority;
    @ApiModelProperty(name = "weakTime", value = "再次使用时间")
    private Date weakTime;
}
