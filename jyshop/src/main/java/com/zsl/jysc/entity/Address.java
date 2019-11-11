package com.zsl.jysc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "邮寄地址")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "主键id")
    private Integer id;

    @ApiModelProperty(name = "name", value = "姓名")
    private String name;
    @ApiModelProperty(name = "phoneNumber", value = "联系电话")
    private String phoneNumber;
    //省份,城市,区县
    @ApiModelProperty(name = "province", value = "省份")
    private String province;
    @ApiModelProperty(name = "city", value = "城市")
    private String city;
    @ApiModelProperty(name = "county", value = "区县")
    private String county;
    @ApiModelProperty(name = "detailAddress", value = "详细地址")
    private String detailAddress;
}
