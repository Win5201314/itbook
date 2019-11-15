package com.zsl.jysc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "会员")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class VIPMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id")
    private Long id;
    @ApiModelProperty(name = "userId", value = "用户主键")
    private Long userId;

    @ApiModelProperty(name = "name", value = "姓名")
    private String name;
    @ApiModelProperty(name = "phoneNumber", value = "联系电话")
    private String phoneNumber;
    @ApiModelProperty(name = "sex", value = "性别")// 根据常数定义具体字符串
    private Integer sex;
    @ApiModelProperty(name = "birthDay", value = "生日")
    private Date birthDay;
    @ApiModelProperty(name = "jobType", value = "行业")// 根据常数定义具体字符串
    private Integer jobType;
    @ApiModelProperty(name = "yearMoney", value = "年收入")// 根据常数定义具体字符串
    private Integer yearMoney;
    @ApiModelProperty(name = "love", value = "爱好")// 根据常数定义具体字符串
    private Integer love;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "create_time", value = "创建时间")
    private Date create_time;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "update_time", value = "更新时间")
    private Date update_time;
}
