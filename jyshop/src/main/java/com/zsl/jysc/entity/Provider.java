package com.zsl.jysc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(name = "create_time", value = "创建时间")
    private Date create_time;

    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(name = "update_time", value = "再次更新时间")
    private Date update_time;
}
