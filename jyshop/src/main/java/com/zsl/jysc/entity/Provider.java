package com.zsl.jysc.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel(description = "供应商")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "供应商编号，主键id")
    private Long id;
    @ApiModelProperty(name = "name", value = "供应商名称")
    private String name;
    @ApiModelProperty(name = "linkman", value = "联系人")
    private String linkman;
    @ApiModelProperty(name = "phoneNumber", value = "联系电话")
    private String phoneNumber;
    @ApiModelProperty(name = "businessNumber", value = "营业执照号")
    private String businessNumber;
    @ApiModelProperty(name = "address", value = "地址")
    private String address;
    @ApiModelProperty(name = "legal", value = "法人")
    private String legal;
}
