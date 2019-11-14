package com.zsl.jysc.entity;

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
    @ApiModelProperty(name = "sex", value = "性别")
    private Integer sex;
    @ApiModelProperty(name = "birthDay", value = "生日")
    private Date birthDay;
    @ApiModelProperty(name = "jobType", value = "行业")
    private Integer jobType;
    @ApiModelProperty(name = "yearMoney", value = "年收入")
    private Integer yearMoney;
    @ApiModelProperty(name = "like", value = "爱好")
    private Integer like;
}
